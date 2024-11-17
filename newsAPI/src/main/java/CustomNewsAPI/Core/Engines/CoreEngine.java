package CustomNewsAPI.Core.Engines;

import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import CustomNewsAPI.Core.Parsing.APIElements.Collection;
import CustomNewsAPI.Core.Parsing.APIElements.FormatSpecifier;
import CustomNewsAPI.Core.Parsing.Parsers.APIParser;
import CustomNewsAPI.Core.Parsing.Parsers.Parser;
import CustomNewsAPI.Core.Parsing.Parsers.SimpleParser;


public class CoreEngine implements Engine {
    private Parser apiParser;
    private Parser simpleParser;

    /**
     * High Level:
     *      The client is to use the read method to evaluate a FormatSpecifier and dispatch the correct
     * parser. The read method builds a collection as specified by the FormatSpecifier and adds it to the
     * corresponding parser.
     * Implementation Details:
     *      The FormatSpecifier's getType method is used to decide which parser is to visit the source. The
     * corresponding parser then visits the formatSpecifier to parse the article source. 
     * @param f
     */
    @Override
    public void read(FormatSpecifier f) {
        switch (f.getType()) {
            case SIMPLE:
                if (simpleParser == null) {
                    simpleParser = new SimpleParser();
                }
                simpleParser.visit(f);
                break;
            case API:
                if (apiParser == null) {
                    apiParser = new APIParser();
                }
                apiParser.visit(f);
                break;
            default:
                throw new InvalidParameterException("Invalid FormatType: " + f.getType());
        }
    }

    /**
     * Implementation Details:
     *      The getCollections method provides a means for viewing the parsed articles. Here a stream is created from both
     * parsers where the list of Collections that exists in both parsers is merged. This gets returned.
     */
    @Override
    public List<Collection> getCollections() {
        return Stream.of(Optional.ofNullable(apiParser), Optional.ofNullable(simpleParser))
            .filter(Optional::isPresent)
            .flatMap(optional -> optional.map(parser -> parser.getCollections()).orElseGet(Collections::emptyList)
            .stream())
            .collect(Collectors.toList());
    }

    /**
     * High Level:
     *      Creates dependency injection for the Core Engine. Helpful for testing
     * @param parser
     */
    public void setAPIParser(APIParser parser) {
        this.apiParser = parser;
    }
    
    /**
     * High Level:
     *      Creates dependency injection for the Core Engine. Helpful for testing
     * @param parser
     */
    public void setSimpleParser(SimpleParser parser) {
        this.simpleParser = parser;
    }
}
