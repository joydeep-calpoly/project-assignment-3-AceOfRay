package CustomNewsAPI.Core.Parsing.Visitor;

import CustomNewsAPI.Core.Parsing.APIElements.Format;

public interface Visitor {
    
    public void visit(Format f);
}
