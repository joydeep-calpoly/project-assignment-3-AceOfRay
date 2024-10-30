package CustomNewsAPI.Core.Parsers;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.logging.Logger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.List;
import java.util.ArrayList;

import CustomNewsAPI.Core.APIElements.Article;
import CustomNewsAPI.Core.APIElements.Collection;
import CustomNewsAPI.Core.Mappers.SimpleArticleMapper;
import CustomNewsAPI.Core.Providers.SimpleProviders.SimpleFormatProvider;



public class SimpleParser implements Parser {
    List<Collection> articleCollections = new ArrayList<>();
    Logger logger;

    /**
     * This constructor parses and logs
     * Ideal for client use
     * 
     * @param provider
     * @param logger
     */

    public SimpleParser(SimpleFormatProvider provider, Logger logger) {
        this.logger = logger;
        parse(provider.provideJsonAsStrings());
    }

    /**
     * This constructor parses without logging
     * This is ideal for testing this class and if we don't want to log invalid
     * articles
     * 
     * @param provider
     */
    public SimpleParser(SimpleFormatProvider provider) {
        parse(provider.provideJsonAsStrings());
    }

    @Override
    public void parse(List<String> jsonStrings) {

        SimpleArticleMapper mapper = new SimpleArticleMapper();
        AtomicInteger totalResults = new AtomicInteger(0);
        List<Article> incomingSimpleArticles = new ArrayList<>();

        jsonStrings.forEach(jsonString -> {
            try {
                incomingSimpleArticles.add(mapper.map(new JSONObject(jsonString)));
                totalResults.getAndIncrement();
            } catch (JSONException e) {
                logger.severe("Caught a JSONException while parsing.\n" + jsonString + "\n" + e.getMessage());
            }
        });

        articleCollections.add(
            new Collection(
                "ok",
                totalResults.get(),
                incomingSimpleArticles)
            );
    }

    @Override
    public List<Collection> getCollections() {
        return new ArrayList<>(articleCollections);
    }

}
