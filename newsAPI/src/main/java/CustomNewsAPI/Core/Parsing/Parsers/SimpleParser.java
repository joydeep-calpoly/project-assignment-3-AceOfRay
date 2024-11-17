package CustomNewsAPI.Core.Parsing.Parsers;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.logging.Logger;
import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

import CustomNewsAPI.Core.Engines.Engine;
import CustomNewsAPI.Core.Parsing.APIElements.Article;
import CustomNewsAPI.Core.Parsing.APIElements.Collection;
import CustomNewsAPI.Core.Parsing.APIElements.FormatSpecifier;
import CustomNewsAPI.Core.Parsing.Mappers.Mapper;
import CustomNewsAPI.Core.Parsing.Mappers.SimpleMappers.ArticleMapper;
import CustomNewsAPI.Core.Parsing.Providers.SimpleProviders.SimpleFileProvider;
import CustomNewsAPI.Core.Parsing.Providers.SimpleProviders.SimpleFormatProvider;

public class SimpleParser implements Parser {
    private List<Collection> articleCollections = new ArrayList<>();
    private final Logger logger;

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
     * High Level:
     *      Implements the parse method as defined in the Parser Interface
     * Implementation Details:
     *      Iterates through each simple article provided by the jsonStrings parameter, and uses a 
     * SimpleArticleMapper to map the json to the Article. All articles will be collected into 1 collection
     * and added to the parsers articleCollection List.
     * Dependencies:
     *      This function depends on a parseHelper function that maps the json to the Article.
     * I seperated it from this function to declutter the code and improve readability.
     * @param jsonStrings
     */
    @Override
    public void parse(List<String> jsonStrings) {
        Mapper<Article> mapper = new ArticleMapper();

        List<Article> incomingSimpleArticles = jsonStrings.stream()
                .map(jsonString -> parseHelper(mapper, jsonString))
                .filter(Objects::nonNull)
                .toList();

        articleCollections.add(
                new Collection("ok", incomingSimpleArticles.size(), incomingSimpleArticles));
    }

    /**
     * High Level:
     *      This function maps json to Articles. It is a helper function to decrease code clutter
     * 
     * @param mapper
     * @param jsonString
     * @return
     */
    private Article parseHelper(Mapper<Article> mapper, String jsonString) {
        try {
            return mapper.map(new JSONObject(jsonString));
        } catch (JSONException e) {
            logger.severe("Caught a JSONException while parsing.\n" + jsonString + "\n" + e.getMessage());
            return null;
        }
    }

    /**
     * Implementation Details:
     *      Returns a defensive copy of the built collections in the parser
     */
    @Override
    public List<Collection> getCollections() {
        return new ArrayList<>(articleCollections);
    }

    /**
     * Constructor:
     *      This constructor is to be used by the Engine to create a SimpleParsers internally. It should not be used by client code
     * Acknowledgements:
     *      This constructor introduces stateful issues to the parser but it is only for the integration of 
     * the visitor pattern inside this codebase.
     */
    public SimpleParser() {
        this.logger = Engine.getLogger();
    }

    /**
     * Implementation Details:
     *      A provider is created and the parser uses it to parse the given json.
     * @param f
     */
    @Override
    public void visit(FormatSpecifier f) {
        SimpleFormatProvider sfp = new SimpleFileProvider((String) f.getSource());
        parse(sfp.provideJsonAsStrings());
    }

}
