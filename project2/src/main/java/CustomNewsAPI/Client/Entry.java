package CustomNewsAPI.Client;

import CustomNewsAPI.Core.APIElements.Collection;
import CustomNewsAPI.Core.Parsers.APIParser;
import CustomNewsAPI.Core.Parsers.Parser;
import CustomNewsAPI.Core.Providers.Provider;
import CustomNewsAPI.Core.Providers.APIProviders.APIFileProvider;

import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.io.IOException;

public class Entry {

    /**
     * This is the entry point of the program, it creates a logger, provider, and parser then prints the parsed articles 
     * 
     * @param args
     */
    public static void main(String[] args) {

        Logger l = getLogger();
        String filePath = "inputs/jsonInputs/bad.json";
        // String filePath = "jsonInputs/bad.json";

        Provider provider = new APIFileProvider(filePath);
        Parser parser = new APIParser(provider, l);
        parser.printValidArticles();

    }

    /**
     * This is a helper function that handles all the verbose code that is involved with creating a logger
     */

    private static Logger getLogger() {
        try {
            FileHandler handler = new FileHandler("logs/error.log", false);
            Logger l = Logger.getLogger(Collection.class.getName());
            l.addHandler(handler);
            return l;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } 
        return null;
    }
}
