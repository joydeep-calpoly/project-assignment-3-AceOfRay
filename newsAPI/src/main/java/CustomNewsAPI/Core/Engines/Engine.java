package CustomNewsAPI.Core.Engines;


import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import CustomNewsAPI.Core.Parsing.APIElements.Collection;
import CustomNewsAPI.Core.Parsing.APIElements.FormatSpecifier;

/**
 * High Level:
 *      The Engine in this API is what interacts with the user defined inputs in order to parse the the given 
 * articles appropriately. User Defined Inputs are encapsulated in the FormatSpecifier class which is injected 
 * into the engine. These FormatSpecifiers will enable the engine to parse the article source defined in the
 * FormatSpecifier.
 */

public interface Engine {

    /**
     * High Level: 
     *      The read method of engines require Formats to read from to obtain sources. The implementing class
     * will create parsers to visit build collections from the input sources.
     * @param file
     */

    public void read(FormatSpecifier f);

    public List<Collection> getCollections();
    
    /**
     * Implementation Details:
     *      This is a helper function that handles all the verbose code that is involved with creating a logger
     */

    public static Logger getLogger() {
        Logger l = Logger.getLogger(Collection.class.getName());
        try {
            l.addHandler(new FileHandler("logs/error.log", false));
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
        return l;
    }
    
    /**
     * High Level:
     *      Prints every article in every parser in the engine
     */
    default void printCollections() {
        getCollections().forEach(collection -> {
            collection.printArticles(collection.getAllArticles());
        });
    }
}
