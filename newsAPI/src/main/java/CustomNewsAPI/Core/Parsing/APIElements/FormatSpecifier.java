package CustomNewsAPI.Core.Parsing.APIElements;

import java.net.URL;
import java.security.InvalidParameterException;

/**
 * High Level:
 *      A Format in this API is an API Element that describes the properties of incoming json. It accepts a parse
 */

public class FormatSpecifier {

    public static enum FormatType {
        SIMPLE, API
    }

    private Object source;
    private final FormatType type;
    
    public FormatSpecifier(Object source, FormatType type) {
        if (sourceValidation(source)) {
            this.source = source;
            this.type = type;
        } else {
            throw new InvalidParameterException("Invalid Source Parameter: " + source);
        }
    }

    public Object getSource() {
        return source;
    }

    public FormatType getType() {
        return type;
    }

    public boolean isFileSource() {
        return source instanceof String;
    }

    public boolean isUrlSource() {
        return source instanceof URL;
    }

    private boolean sourceValidation(Object o) {
        return o instanceof URL || o instanceof String;
    }
}
