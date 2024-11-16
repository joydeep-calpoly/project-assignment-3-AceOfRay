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

    @Override
    public void read(FormatSpecifier f) {
        switch (f.getType()) {
            case SIMPLE:
                simpleParser = new SimpleParser();
                simpleParser.visit(f);
                break;
            case API:
                apiParser = new APIParser();
                apiParser.visit(f);
                break;
            default:
                throw new InvalidParameterException("Invalid FormatType: " + f.getType());
        }
    }

    @Override
    public List<Collection> getCollections() {
        return Stream.of(Optional.ofNullable(apiParser), Optional.ofNullable(simpleParser))
                .filter(Optional::isPresent)
                .flatMap(optional -> optional.map(parser -> parser.getCollections()).orElseGet(Collections::emptyList)
                        .stream())
                .collect(Collectors.toList());
    }

}
