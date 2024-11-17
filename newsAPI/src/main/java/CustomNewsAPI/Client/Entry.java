package CustomNewsAPI.Client;

import CustomNewsAPI.Core.Engines.CoreEngine;
import CustomNewsAPI.Core.Engines.Engine;
import CustomNewsAPI.Core.Parsing.APIElements.FormatSpecifier;
import CustomNewsAPI.Core.Parsing.APIElements.FormatSpecifier.FormatType;

public class Entry {

    /**
     * This is the entry point of the program, it creates the API's Core Engine and uses it to parse articles
     * @param args
     */
    public static void main(String[] args) {

        FormatSpecifier f = new FormatSpecifier("inputs/jsonInputs/example.json", FormatType.API);
        //FormatSpecifier f = new FormatSpecifier("inputs/base/simple.json", FormatType.SIMPLE);
        Engine e = new CoreEngine();
        e.read(f);
        e.printCollections();

    }
}
