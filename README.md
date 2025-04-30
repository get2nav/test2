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