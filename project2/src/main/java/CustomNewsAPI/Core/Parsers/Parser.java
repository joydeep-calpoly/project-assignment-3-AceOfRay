package CustomNewsAPI.Core.Parsers;

import java.util.List;
import java.util.logging.Logger;

import CustomNewsAPI.Core.APIElements.Collection;

/*
 * The parser Interface provides flexibility when designing different types of parsers, 
 * Some example possible Parsers are:
 *      Queryable Parser:
 *          If we wanted to make a parser that allows for us to query articles from certain
 *          sources; this interface allows for such an implementation
 *      Mutable Parser:
 *          If we wanted to load articles from another source into our current collection
 *          ; this interface allows for that
 */ 

public interface Parser {
    /**
     * HighLevel:
     *      The parse method should read json strings and parse the articles into whatever type of data
     * structure is useful to the implementing class. Every parser should have a parse method that populates
     * its data structure.
     * @param jsonStrings
     */

    public void parse(List<String> jsonStrings);

    /**
     * HighLevel:
     *      Each parser must return the parsed json in the form List<Collection>
     * @return
     */

    public List<Collection> getCollections();

    /**
     * HighLevel:
     *      Each parser must be able to print the valid articles it has obtained from it's provider
     */

    default void printValidArticles() {
        getCollections().forEach(collection -> {
            collection.printArticles(collection.getValidArticles());
        });
    }

    /**
     * High Level:
     *      Every parser must have the ability to log invalid articles
     * @param logger
     */

    public void logInvalidArticles(Logger logger);

}
