package CustomNewsAPI.Core.Parsing.Providers;

import java.util.List;

/**
 * High Level:
 *      The Provider Interface was created to allow multiple different implementations take json from
 * many different sources as long as they boil them down to Strings of Json. Interpretation from the source type 
 * to the string type should be done automatically by the implementing class.
 * 
 * Possible Implementations:
 *      Network Provider - A Provider that reads json from the internet
 *      Database Provider - A Provider that jsonifies data from a database
 *      User Input Provider - A Provider that requests the user to manually create Json
 */

public interface Provider {
    
    /**
     * HighLevel:
     *      This method is is to return a defensive copy of the Json as strings that were built by the 
     * interpretSourceAsStrings() method
     * @return
     */
    public List<String> provideJsonAsStrings();

}
