package CustomNewsAPI.Core.Parsing.APIElements;

/**
 * High Level:
 *      A Format in this API is an API Element that describes the properties of incoming json. It accepts a parse
 */

public class Format {

    public static enum Type {
        Simple, API
    }

    private final String source;
    private final Type type;
    
    public Format(String source, Type type) {
        this.source = source;
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public Type getType() {
        return type;
    }
}
