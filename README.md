apiVersion: apps/v1
kind: Deployment
metadata:
  name: hazelcast-test
spec:
  replicas: 3
  selector:
    matchLabels:
      app: hazelcast-test
  template:
    metadata:
      labels:
        app: hazelcast-test
    spec:
      containers:
        - name: hazelcast
          image: <your-registry>/hazelcast-enterprise:3.12.4
          ports:
            - containerPort: 5701
          env:
            - name: HAZELCAST_LICENSE_KEY
              valueFrom:
                secretKeyRef:
                  name: hazelcast-license
                  key: license-key
            - name: HAZELCAST_CONFIG
              value: /opt/hazelcast/hazelcast.xml
          volumeMounts:
            - name: config-volume
              mountPath: /opt/hazelcast/hazelcast.xml
              subPath: hazelcast.xml
      volumes:
        - name: config-volume
          configMap:
            name: hazelcast-config


apiVersion: v1
kind: Service
metadata:
  name: hazelcast-test
spec:
  selector:
    app: hazelcast-test
  ports:
    - name: hazelcast
      port: 5701
      targetPort: 5701
  clusterIP: None  # Headless service for DNS-based discovery



<hazelcast xmlns="http://www.hazelcast.com/schema/config"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://www.hazelcast.com/schema/config
                               http://www.hazelcast.com/schema/config/hazelcast-config-3.12.xsd">
    <network>
        <join>
            <multicast enabled="false"/>
            <tcp-ip enabled="true">
                <members>
                    <address>hazelcast-test.default.svc.cluster.local</address>
                </members>
            </tcp-ip>
        </join>
        <port auto-increment="true">5701</port>
    </network>
</hazelcast>



resources:
  - deployment.yaml
  - service.yaml

configMapGenerator:
  - name: hazelcast-config
    files:
      - hazelcast.xml

secretGenerator:
  - name: hazelcast-license
    literals:
      - license-key=<your-license-key>

generatorOptions:
  disableNameSuffixHash: true

resources:
  - ../../base



kubectl apply -k overlays/dev
