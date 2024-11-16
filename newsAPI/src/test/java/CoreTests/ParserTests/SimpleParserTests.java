package CoreTests.ParserTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import CustomNewsAPI.Core.Engines.Engine;
import CustomNewsAPI.Core.Parsing.APIElements.Article;
import CustomNewsAPI.Core.Parsing.APIElements.Collection;
import CustomNewsAPI.Core.Parsing.Parsers.Parser;
import CustomNewsAPI.Core.Parsing.Parsers.SimpleParser;
import CustomNewsAPI.Core.Parsing.Providers.SimpleProviders.SimpleFileProvider;
import CustomNewsAPI.Core.Parsing.Providers.SimpleProviders.SimpleFormatProvider;

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

            Parser parser = new SimpleParser(provider, Engine.getLogger());
            assertEquals(expectedCollection, parser.getCollections().get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests: that the parser creates three Simple Articles inside of 1 collection when the provider feeds it 
     * 3 simple formatted article strings from the target file
     * TargetFile: "testInputs/simpleFormatInputs/greenSimple.json"
     * Assertions:
     *      Compares the the expected Collection(with three green simple articles) to the code generated 
     * Collection (when given the same three green simple articles as a source by the provider)
     */
    @Test
    void simpleParserTest_GreenThreeArticleCollection() {
        SimpleFormatProvider provider = Mockito.mock(SimpleFileProvider.class);
        try {
            Mockito.when(provider.provideJsonAsStrings()).thenReturn(Arrays.asList(
                    Files.readString(
                            Path.of(new File("testInputs/simpleFormatInputs/greenSimple.json").getAbsolutePath())),
                    Files.readString(
                            Path.of(new File("testInputs/simpleFormatInputs/greenSimple.json").getAbsolutePath())),
                    Files.readString(
                            Path.of(new File("testInputs/simpleFormatInputs/greenSimple.json").getAbsolutePath()))));

            Collection expectedCollection = new Collection("ok", 3, Arrays.asList(
                    new Article("Assignment #2",
                            "Extend Assignment #1 to support multiple sources and to introduce source processor.",
                            "https://canvas.calpoly.edu/courses/55411/assignments/274503",
                            "2021-04-16 09:53:23.709229"),
                    new Article("Assignment #2",
                            "Extend Assignment #1 to support multiple sources and to introduce source processor.",
                            "https://canvas.calpoly.edu/courses/55411/assignments/274503",
                            "2021-04-16 09:53:23.709229"),
                    new Article("Assignment #2",
                            "Extend Assignment #1 to support multiple sources and to introduce source processor.",
                            "https://canvas.calpoly.edu/courses/55411/assignments/274503",
                            "2021-04-16 09:53:23.709229")));

            List<Collection> expectedCollections = Arrays.asList(expectedCollection);

            Parser parser = new SimpleParser(provider, Engine.getLogger());
            assertEquals(expectedCollections, parser.getCollections());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Tests: That when given 1 good and 1 bad article, the collection is only populated with the good article.
     * TargetFiles:
     *      "testInputs/simpleFormatInputs/greenSimple.json" - contains 1 valid simple article
     *      "testInputs/simpleFormatInputs/redSimple.json" - contains 1 invalid simple article
     */
    @Test
    void simpleParserTest_MixedArticles() {
        SimpleFormatProvider provider = Mockito.mock(SimpleFileProvider.class);
        try {
            Mockito.when(provider.provideJsonAsStrings()).thenReturn(Arrays.asList(
                    Files.readString(
                            Path.of(new File("testInputs/simpleFormatInputs/greenSimple.json").getAbsolutePath())),
                    Files.readString(
                            Path.of(new File("testInputs/simpleFormatInputs/redSimple.json").getAbsolutePath()))));

            Collection expectedCollection = new Collection("ok", 1, Arrays.asList(
                    new Article("Assignment #2",
                            "Extend Assignment #1 to support multiple sources and to introduce source processor.",
                            "https://canvas.calpoly.edu/courses/55411/assignments/274503",
                            "2021-04-16 09:53:23.709229")));

            List<Collection> expectedCollections = Arrays.asList(expectedCollection);

            Parser parser = new SimpleParser(provider, Engine.getLogger());
            assertEquals(expectedCollections, parser.getCollections());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Tests: That when given a single file with nothing in it, no article is created.
     * TargetFiles:
     *      "testInputs/simpleFormatInputs/empty.json" - contains absolutely nothing
     */
    @Test
    void simpleParserTest_NoArticle() {
        SimpleFormatProvider provider = Mockito.mock(SimpleFileProvider.class);
        try {
            Mockito.when(provider.provideJsonAsStrings()).thenReturn(Arrays.asList(
                    Files.readString(
                            Path.of(new File("testInputs/simpleFormatInputs/empty.json").getAbsolutePath()))));

            Collection expectedCollection = new Collection("ok", 0, new ArrayList<>());

            List<Collection> expectedCollections = Arrays.asList(expectedCollection);

            Parser parser = new SimpleParser(provider, Engine.getLogger());
            assertEquals(expectedCollections, parser.getCollections());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Tests: That when given multiple empty json files, the collection contains no articles.
     * TargetFiles:
     *      "testInputs/simpleFormatInputs/empty.json" - contains nothing
     */
    @Test
    void simpleParserTest_MultipleEmptyFiles() {
        SimpleFormatProvider provider = Mockito.mock(SimpleFileProvider.class);
        try {
            Mockito.when(provider.provideJsonAsStrings()).thenReturn(Arrays.asList(
                Files.readString(Path.of(new File("testInputs/simpleFormatInputs/empty.json").getAbsolutePath())),
                Files.readString(Path.of(new File("testInputs/simpleFormatInputs/empty.json").getAbsolutePath())),
                Files.readString(Path.of(new File("testInputs/simpleFormatInputs/empty.json").getAbsolutePath())),
                Files.readString(Path.of(new File("testInputs/simpleFormatInputs/empty.json").getAbsolutePath())),
                Files.readString(Path.of(new File("testInputs/simpleFormatInputs/empty.json").getAbsolutePath()))
                ));

            Collection expectedCollection = new Collection("ok", 0, new ArrayList<>());

            List<Collection> expectedCollections = Arrays.asList(expectedCollection);
            
            Parser parser = new SimpleParser(provider, Engine.getLogger());
            assertEquals(expectedCollections, parser.getCollections());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
