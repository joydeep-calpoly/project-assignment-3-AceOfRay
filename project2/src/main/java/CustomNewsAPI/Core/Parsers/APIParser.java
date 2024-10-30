package CustomNewsAPI.Core.Parsers;

import java.util.List;

import CustomNewsAPI.Core.APIElements.Collection;
import CustomNewsAPI.Core.Providers.Provider;

import java.util.logging.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

/**
 * HighLevel:
 *      The BasicParser depends on constructor dependency injection to perform the parsing tasks
 * Parsing of the given files is done automatically and it's generated collection(s) are retrievable 
 * by the getCollections() method. This parser expects at least 1 up to n json strings to be provided 
 * by the provider. This parser is meant to be a simple when compared to possible implementations in 
 * the Parser Interface.
 * 
 * Logging:
 *      Logging is performed by request and enabled by passing a logger into the constructor
 * 
 */

public class APIParser implements Parser {
    private final List<Collection> articleCollections = new ArrayList<>();

    /**
     * This constructor parses and logs
     * Ideal for client use
     * @param provider
     * @param logger
     */

    public APIParser(Provider provider, Logger logger) {
        parse(provider.provideJsonAsStrings());
        logInvalidArticles(logger);
    }

    /**
     * This constructor parses without logging
     * This is ideal for testing this class and if we don't want to log invalid articles
     * @param provider
     */
    public APIParser(Provider provider) {
        parse(provider.provideJsonAsStrings());
    }

    /**
     * Implementation Details:
     *      getCollections() returns a defensive copy of the generated collections from parsing
     */

    @Override
    public List<Collection> getCollections() {
        return new ArrayList<>(this.articleCollections);
    }

    /**
     * Implementation Details:
     *      Walks through each article in each collection and logs the invalid ones to "logs/error.log"
     * 
     * Developer Thoughts:
     *      A trade off of using jacksonbind object mapper is that I haven't found a way to immediately log an invalid
     * article. We instead have to wait for all of them to be processed, and then go through and log each one. 
     * I think it's not as efficient as it could be but that is the drawback of using that library
     * @param logger
     */

    public void logInvalidArticles(Logger logger) {
        articleCollections.forEach(collection -> {
            collection.getInvalidArticles().forEach(article -> {
                logger.severe("Invalid Article Detected: " + article.toString());
            });
        });
        
    }

    /**
     * Implementation Details:
     *      This method walks through the jsonStrings provided by the provider and uses the
     * jacksonbind ObjectMapper to read the values and map them into the corresponding collection.
     * 
     * @param jsonStrings
     */
    @Override
    public void parse(List<String> jsonStrings) {
        ObjectMapper mapper = new ObjectMapper();
        jsonStrings.forEach(jsonString -> {
            try {
                articleCollections.add(mapper.readValue(jsonString, Collection.class));
            } catch (JsonProcessingException e) {
                System.out.println("Caught a JsonProcessingException while parsing.\n " + e.getMessage());
            }
        });
    }

}
