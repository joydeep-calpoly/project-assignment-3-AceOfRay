package CustomNewsAPI.Core.Parsing.Parsers;


import java.util.List;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.json.JSONObject;
import org.json.JSONException;

import CustomNewsAPI.Core.Engines.Engine;
import CustomNewsAPI.Core.Parsing.APIElements.Collection;
import CustomNewsAPI.Core.Parsing.APIElements.FormatSpecifier;
import CustomNewsAPI.Core.Parsing.Mappers.Mapper;
import CustomNewsAPI.Core.Parsing.Mappers.APIMappers.CollectionMapper;
import CustomNewsAPI.Core.Parsing.Providers.APIProviders.APIFileProvider;
import CustomNewsAPI.Core.Parsing.Providers.APIProviders.APIFormatProvider;
import CustomNewsAPI.Core.Parsing.Providers.APIProviders.URLProvider;

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
    private final Logger logger;

    /**
     * Constructor:
     *      This constructor parses and logs. Ideal for client use
     * @param provider
     * @param logger
     */

    public APIParser(APIFormatProvider provider, Logger logger) {
        this.logger = logger;
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
     *      This method walks through the jsonStrings provided by the provider and uses the
     * jacksonbind ObjectMapper to read the values and map them into the corresponding collection.
     * 
     * @param jsonStrings
     */
    @Override
    public void parse(List<String> jsonStrings) {
        Mapper<Collection> mapper = new CollectionMapper();
        jsonStrings.forEach(jsonString -> {
            try {
                articleCollections.add(mapper.map(new JSONObject(jsonString)));
            } catch (JSONException e) {
                logger.severe("Caught a JSONException while parsing.\n" + jsonString + "\n" + e.getMessage());
            }
        });
    }

    /**
     * Constructor:
     *      This constructor is to be used by the Engines of this API and not for clients. 
     * Acknowledgements:
     *      This constructor introduces stateful issues to the parser but it is only for the integration of 
     * the visitor pattern inside this codebase.
     */
    public APIParser() {
        this.logger = Engine.getLogger();
    }

    /**
     * Implementation Details:
     *      A provider is created and the parser uses it to parse the given json.
     * @param f
     */
    @Override
    public void visit(FormatSpecifier f) {
        APIFormatProvider ap = f.isFileSource()
                ? new APIFileProvider((String) f.getSource())
                : new URLProvider((URL) f.getSource());
        parse(ap.provideJsonAsStrings());
    }

}
