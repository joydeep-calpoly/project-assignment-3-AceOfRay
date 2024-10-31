package CoreTests.ParserTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import CustomNewsAPI.Client.Entry;
import CustomNewsAPI.Core.APIElements.Article;
import CustomNewsAPI.Core.APIElements.Collection;
import CustomNewsAPI.Core.Parsers.Parser;
import CustomNewsAPI.Core.Parsers.SimpleParser;
import CustomNewsAPI.Core.Providers.SimpleProviders.SimpleFileProvider;
import CustomNewsAPI.Core.Providers.SimpleProviders.SimpleFormatProvider;

public class SimpleParserTests {

    /**
     * Tests: That the SimpleParser will create a collection with 1 green simple
     *      extracting from the target file
     * TargetFile: testInputs/simpleFormatInputs/greenSimple.json
     * Mocking Details:
     *      The SimpleFileProvider is depended on by the SimpleParser so it is mocked
     * here and returns a list of the contents of a green simple format article.
     * 
     */
    @Test
    void simpleParserTest_GreenSingleArticleCollection() {
        SimpleFormatProvider provider = Mockito.mock(SimpleFileProvider.class);
        try {
            Mockito.when(provider.provideJsonAsStrings()).thenReturn(Arrays.asList(Files.readString(
                    Path.of(new File("testInputs/simpleFormatInputs/greenSimple.json").getAbsolutePath()))));
            Collection expectedCollection = new Collection("ok", 1, Arrays.asList(new Article("Assignment #2",
                    "Extend Assignment #1 to support multiple sources and to introduce source processor.",
                    "https://canvas.calpoly.edu/courses/55411/assignments/274503", "2021-04-16 09:53:23.709229")));

            Parser parser = new SimpleParser(provider, Entry.getLogger());
            assertEquals(expectedCollection, parser.getCollections().get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
