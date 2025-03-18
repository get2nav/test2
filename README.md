import java.util.Map;

rule "Check Latest Version"
when
    $newAttributes : Map()
    $existingAttributes : Map()
    eval($newAttributes.containsKey("filterIdentifier") && $existingAttributes.containsKey("filterIdentifier"))
    $newVersion : Comparable() from eval($newAttributes.get("filterIdentifier"))
    $existingVersion : Comparable() from eval($existingAttributes.get("filterIdentifier"))
    eval($newVersion.compareTo($existingVersion) > 0)
then
    System.out.println("New version is the latest.");
end

rule "Invalid Version"
when
    $newAttributes : Map()
    $existingAttributes : Map()
    eval($newAttributes.containsKey("filterIdentifier") && $existingAttributes.containsKey("filterIdentifier"))
    $newVersion : Object() from eval($newAttributes.get("filterIdentifier"))
    $existingVersion : Object() from eval($existingAttributes.get("filterIdentifier"))
    eval(!($newVersion instanceof Comparable) || !($existingVersion instanceof Comparable))
then
    throw new IllegalArgumentException("Versions must be either Integer or Date and implement Comparable.");
end
