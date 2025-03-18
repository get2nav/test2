import com.example.model.NodeDto;
import java.util.Map;

rule "Compare Node Versions"
when
    // Extract attributes and filter identifier
    $newNode : NodeDto($filterId : filterIdentifier, $newAttributes : getAttributes())
    $existingNode : NodeDto(filterIdentifier == $newNode.filterIdentifier, $existingAttributes : getAttributes())

    // Ensure attributes contain the filterIdentifier key
    eval($newAttributes.containsKey($filterId) && $existingAttributes.containsKey($filterId))

    // Extract versions
    $newVersion : Map( this[$filterId] != null ) from $newAttributes
    $existingVersion : Map( this[$filterId] != null ) from $existingAttributes

    // Ensure versions are comparable
    eval($newVersion instanceof Comparable && $existingVersion instanceof Comparable)

    // Compare versions
    eval(((Comparable) $newVersion).compareTo($existingVersion) > 0)
then
    retract($existingNode);  // Remove older version
    System.out.println("Current Node is the latest version");
end
