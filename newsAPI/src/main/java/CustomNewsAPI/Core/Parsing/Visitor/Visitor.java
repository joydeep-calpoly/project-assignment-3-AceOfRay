package CustomNewsAPI.Core.Parsing.Visitor;

import CustomNewsAPI.Core.Parsing.APIElements.FormatSpecifier;

public interface Visitor {
    
    public void visit(FormatSpecifier f);
}
