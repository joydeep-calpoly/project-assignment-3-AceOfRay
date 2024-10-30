package CustomNewsAPI.Core.Parsers;

import java.util.List;
import java.util.ArrayList;

import CustomNewsAPI.Core.APIElements.Article;
import CustomNewsAPI.Core.APIElements.Collection;
import CustomNewsAPI.Core.Providers.Provider;
import CustomNewsAPI.Core.Providers.SimpleProviders.SimpleFormatProvider;

import java.util.logging.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.concurrent.atomic.AtomicInteger;


public class SimpleParser implements Parser {
    List<Collection> articleCollections = new ArrayList<>();

    /**
     * This constructor parses and logs
     * Ideal for client use
     * @param provider
     * @param logger
     */

    public SimpleParser(SimpleFormatProvider provider, Logger logger) {
        parse(provider.provideJsonAsStrings());
        logInvalidArticles(logger);
    }

    /**
     * This constructor parses without logging
     * This is ideal for testing this class and if we don't want to log invalid articles
     * @param provider
     */
    public SimpleParser(SimpleFormatProvider provider) {
        parse(provider.provideJsonAsStrings());
    }

    @Override
    public void parse(List<String> jsonStrings) {
        ObjectMapper mapper = new ObjectMapper();
        AtomicInteger totalResults = new AtomicInteger(0);
        List<Article> incomingSimpleArticles = new ArrayList<>();
        jsonStrings.forEach(jsonString -> {
            try {
                Article a = mapper.readValue(jsonString, Article.class);
                incomingSimpleArticles.add(a);
                totalResults.getAndIncrement();

            } catch (JsonProcessingException e) {
                System.out.println("Caught a JsonProcessingException while parsing.\n " + e.getMessage());
            }
        });
    }

    @Override
    public List<Collection> getCollections() {
        return new ArrayList<>(articleCollections);
    }

    @Override
    public void logInvalidArticles(Logger logger) {
        articleCollections.forEach(collection -> {
            collection.getInvalidArticles().forEach(article -> {
                logger.severe("Invalid Article Detected: " + article.toString());
            });
        });
    }
    
}
