package CustomNewsAPI.Core.APIElements;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

/**
 * High Level:
 *      A Source in this project is the source of any given article. Sources are defined by
 * two properties, name and id.
 */

public class Source {
    // Source Fields
    private String id;
    private String name;

    /**
     * Basic constructor for the Source object
     * @param id
     * @param name
     */
    @JsonCreator
    public Source(
            @JsonProperty("id") String id,
            @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * gets the immutable original id 
     * @return
     */
    
    @JsonIgnore
    public String getId() {
        return id;
    }

    /**
     * gets the immutable original name
     * @return
     */
    
    @JsonIgnore
    public String getName() {
        return name;
    }

    /**
     * Standard Equals Method
     * Tested to hold each property of an equals method
     */
    
    @JsonIgnore
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Source)) {
            return false;
        }
        Source other = (Source) obj;
        
        return Objects.equals(name, other.name) &&
        Objects.equals(id, other.id);
        
    }

    /**
     * Returns a valid Source object to be used in testing
     * @param name
     * @return
     */

    @JsonIgnore
    static public Source generateValidTestSource(String name) {
        return new Source("testId", name);
    }
}
