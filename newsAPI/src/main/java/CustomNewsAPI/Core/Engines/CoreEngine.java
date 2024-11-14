package CustomNewsAPI.Core.Engines;

import java.io.File;
import java.util.List;

public class CoreEngine implements Engine {

    @Override
    public void start(File f) {
        // read contents of file
        // maybe create a format object out of the contents
        // have the parser visit the format class and parse the article 
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }

    @Override
    public void start(List<File> files) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }

    @Override
    public void rev(File f) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'rev'");
    }
    
}
