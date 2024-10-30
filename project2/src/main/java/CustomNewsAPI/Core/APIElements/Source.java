package CustomNewsAPI.Core.APIElements;


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
    
    public Source(
            String id,
            String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * gets the immutable original id 
     * @return
     */
    
    public String getId() {
        return id;
    }

    /**
     * gets the immutable original name
     * @return
     */
    
    public String getName() {
        return name;
    }

    /**
     * Standard Equals Method
     * Tested to hold each property of an equals method
     */
    
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

    static public Source generateValidTestSource(String name) {
        return new Source("testId", name);
    }
}
