import java.util.Map;

rule "Check Latest Version"
when
    $newAttributes : Map(this["filterIdentifier"] != null)
    $existingAttributes : Map(this["filterIdentifier"] != null)
    $newVersion : Comparable() from $newAttributes["filterIdentifier"]
    $existingVersion : Comparable() from $existingAttributes["filterIdentifier"]
    eval($newVersion.compareTo($existingVersion) > 0)
then
    System.out.println("New version is the latest.");
end

rule "Invalid Version"
when
    $newAttributes : Map(this["filterIdentifier"] != null)
    $existingAttributes : Map(this["filterIdentifier"] != null)
    $newVersion : Object() from $newAttributes["filterIdentifier"]
    $existingVersion : Object() from $existingAttributes["filterIdentifier"]
    not(eval($newVersion instanceof Comparable && $existingVersion instanceof Comparable))
then
    throw new IllegalArgumentException("Versions must be either Integer or Date and implement Comparable.");
end
