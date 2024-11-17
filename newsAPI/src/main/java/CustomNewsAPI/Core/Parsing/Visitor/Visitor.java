package CustomNewsAPI.Core.Parsing.Visitor;

import CustomNewsAPI.Core.Parsing.APIElements.FormatSpecifier;

public interface Visitor {
    
    /**
     * The visit method of the Visitor interface is used to dispatch a parser to a FormatSpecifier. The parser 
     * is to create a provider from the data extracted from the FormatSpecifier and use that provider to parse 
     * the articles.
     * @param f
     */
    public void visit(FormatSpecifier f);
}
