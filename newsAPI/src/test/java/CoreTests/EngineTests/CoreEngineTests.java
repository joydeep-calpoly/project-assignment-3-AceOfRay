package CoreTests.EngineTests;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import CustomNewsAPI.Core.Engines.CoreEngine;
import CustomNewsAPI.Core.Parsing.APIElements.FormatSpecifier;
import CustomNewsAPI.Core.Parsing.Parsers.APIParser;
import CustomNewsAPI.Core.Parsing.Parsers.SimpleParser;
import com.sun.tools.javac.util.List;

public class CoreEngineTests {

    private APIParser mockApiParser = Mockito.mock(APIParser.class);

    private SimpleParser mockSimpleParser = Mockito.mock(SimpleParser.class);
    
    /**
     * Tests: that a URLProvider is created and the APIParser is used to parse
     */
    @Test
    void coreEngineTest_CreatesURLProvider_APIParser() {
        CoreEngine engine = new CoreEngine();
        FormatSpecifier format = new FormatSpecifier("https://someexamplesite.com/news/api", FormatSpecifier.FormatType.API);

        engine.setAPIParser(mockApiParser);
        engine.read(format); // Call the read method

        // Verify that simpleParser.visit was called with the format
        Mockito.verify(mockApiParser).visit(format);
    }

    /**
     * Tests: That a FileProvider is created and the APIParser is used to Parse
     */
    @Test
    void coreEngineTest_CreatesFileProvider_APIParser() {
        CoreEngine engine = new CoreEngine();
        FormatSpecifier format = new FormatSpecifier("testInputs/smallGreenSet.json", FormatSpecifier.FormatType.API);

        engine.setAPIParser(mockApiParser);
        engine.read(format); // Call the read method

        // Verify that simpleParser.visit was called with the format
        Mockito.verify(mockApiParser).visit(format);
    }

    /**
     * Tests: That a FileProvider is created and the SimpleParser is used to parse
     */
    @Test
    void coreEngineTest_CreatesFileProvider_SimpleParser() {
        CoreEngine engine = new CoreEngine();
        FormatSpecifier format = new FormatSpecifier("testInputs/simpleFormatInputs/greenSimple.json", FormatSpecifier.FormatType.SIMPLE);

        engine.setSimpleParser(mockSimpleParser);
        engine.read(format); // Call the read method

        // Verify that simpleParser.visit was called with the format
        Mockito.verify(mockSimpleParser).visit(format);
    }
}
