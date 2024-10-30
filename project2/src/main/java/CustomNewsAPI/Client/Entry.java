package CustomNewsAPI.Client;

import CustomNewsAPI.Core.APIElements.Collection;
import CustomNewsAPI.Core.Parsers.*;
//import CustomNewsAPI.Core.Providers.APIProviders.*;
import CustomNewsAPI.Core.Providers.SimpleProviders.*;


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
        //String filePath = "inputs/jsonInputs/bad.json";
        //String filePath = "inputs/jsonInputs/example.json";
        String filePath = "inputs/base/simple.txt";

        //APIFormatProvider provider = new APIFileProvider(filePath);
        SimpleFormatProvider provider = new SimpleFileProvider(filePath);
        Parser parser = new SimpleParser(provider, l);
        parser.printValidArticles();

    }

    /**
     * This is a helper function that handles all the verbose code that is involved with creating a logger
     */

    public static Logger getLogger() {
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
