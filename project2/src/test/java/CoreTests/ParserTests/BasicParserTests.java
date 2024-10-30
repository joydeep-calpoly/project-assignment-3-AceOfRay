package CoreTests.ParserTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Arrays;

import CustomNewsAPI.Core.APIElements.Article;
import CustomNewsAPI.Core.APIElements.Collection;
import CustomNewsAPI.Core.Parsers.APIParser;
import CustomNewsAPI.Core.Parsers.Parser;
import CustomNewsAPI.Core.Providers.Provider;
import CustomNewsAPI.Core.Providers.APIProviders.APIFileProvider;

/*
 * Context
 *      This file tests the functionality of the parse() method of the Basic Parser
 *      No mocking was implemented as I am not sure if we are supposed/allowed to or not
 */

class BasicParserTests {
    /**
     * Tests: Whether the BasicParser has valid articles that correspond to the articles within the target file
     * Target File: "testInputs/smallGreenSet.json"
     * Assertions:
     *  Verifies the equality of the generated Collection and the Expected Collection
     *  Verifies the equlity of the generated Articles List and the expected Articles
     *  Ensures that every article in the Collection is Valid
     *
     * @see BasicParser.parse()
     */
    @Test
    void testParse_AllValidArticles() {
        // create our expected list
        List<Article> expectedArticles = ParserTestHelpers.getGreenSetArticles();
        Collection expectedCollection = new Collection("ok", 3, expectedArticles);

        // create parser
        String filePath = "testInputs/smallGreenSet.json";
        Provider provider = new APIFileProvider(filePath);
        Parser parser = new APIParser(provider);

        assertEquals(expectedCollection, parser.getCollections().get(0));
        assertEquals(expectedArticles, parser.getCollections().get(0).getAllArticles());
        assertEquals(expectedArticles, parser.getCollections().get(0).getValidArticles());

    }

    /**
     * Tests: Whether the BasicParser populates invalid articles that correspond to the articles within the target file
     * Target File: "testInputs/smallRedSet.json"
     * Assertions:
     *  Verifies the equality of the generated Collection and the Expected Collection
     *  Ensures that every article in the Collection is Invalid
     *
     * @see BasicParser.parse()
     */
    @Test
    void testParse_AllInvalidArticles() {
        // create our expected list
        List<Article> expectedArticles = ParserTestHelpers.getRedSetArticles();

        Collection expectedCollection = new Collection("ok", 3, expectedArticles);

        // create parser
        String filePath = "testInputs/smallRedSet.json";
        Provider provider = new APIFileProvider(filePath);
        Parser parser = new APIParser(provider);

        assertEquals(expectedCollection, parser.getCollections().get(0));
        assertEquals(expectedArticles, parser.getCollections().get(0).getInvalidArticles());
    }

    /**
     * Tests: Whether the BasicParser correctly populates valid and invalid articles that correspond to the articles within the target file
     * Target File: "testInputs/mixedGreenSet.json"
     * Assertions:
     *  Verifies the equality of the generated Collection and the Expected Collection
     *  Verifies the equlity of the generated Articles List and the expected Articles
     *  Ensures that every article in the Collection is Valid
     *
     * @see BasicParser.parse()
     */
    @Test
    void testParse_MixedArticles() {
        List<Article> expectedArticles = ParserTestHelpers.getMixedSetArticles();

        Collection expectedCollection = new Collection("ok", 3, expectedArticles);

        // create parser
        String filePath = "testInputs/smallMixedSet.json";
        Provider provider = new APIFileProvider(filePath);
        Parser parser = new APIParser(provider);

        List<Article> expectedInvalidArticles = Arrays.asList(expectedArticles.get(2));
        List<Article> expectedValidArticles = Arrays.asList(expectedArticles.get(0), expectedArticles.get(1));

        assertEquals(expectedCollection, parser.getCollections().get(0));
        assertEquals(expectedArticles, parser.getCollections().get(0).getAllArticles());
        assertEquals(expectedValidArticles, parser.getCollections().get(0).getValidArticles());
        assertEquals(expectedInvalidArticles, parser.getCollections().get(0).getInvalidArticles());
    }

    /**
     * Tests: The functionality of parsing multiple files and creating different collections for each file
     * Assertions:
     *  Compares the equality of the expected 3 greenSets and the result of parsing multiple files
     * 
     */
    @Test
    void testParse_MultipleAllValidCollections() {

        List<String> filePaths = Arrays.asList("testInputs/smallGreenSet.json", "testInputs/smallGreenSet.json",
                "testInputs/smallGreenSet.json");
        Provider provider = APIFileProvider.fromPaths(filePaths);
        Parser parser = new APIParser(provider);

        List<Collection> expectedCollections = ParserTestHelpers.generateNCollections(3,
                () -> ParserTestHelpers.getGreenSetArticles());

        assertEquals(parser.getCollections(), expectedCollections);
    }

    /* method
     * Tests: The functionality of parsing multiple files and creating different collections for each file
     * Assertions:
     *  Compares the equality of the expected 3 redSets and the result of parsing multiple files
     */
    @Test
    void testParse_MultipleInvalidCollections() {
        List<String> filePaths = Arrays.asList("testInputs/smallRedSet.json", "testInputs/smallRedSet.json",
                "testInputs/smallRedSet.json");
        Provider provider = APIFileProvider.fromPaths(filePaths);
        Parser parser = new APIParser(provider);

        List<Collection> expectedCollections = ParserTestHelpers.generateNCollections(3,
                () -> ParserTestHelpers.getRedSetArticles());

        assertEquals(parser.getCollections(), expectedCollections);
    }

    /* method
     * Tests: The functionality of parsing multiple files and creating different collections for each file
     * Assertions:
     *  Compares the equality of the expected 3 mixedSets and the result of parsing multiple files
     */
    @Test
    void testParse_MultipleMixedCollections() {
        List<String> filePaths = Arrays.asList("testInputs/smallMixedSet.json", "testInputs/smallMixedSet.json",
                "testInputs/smallMixedSet.json");
        Provider provider = APIFileProvider.fromPaths(filePaths);
        Parser parser = new APIParser(provider);

        List<Collection> expectedCollections = ParserTestHelpers.generateNCollections(3,
                () -> ParserTestHelpers.getMixedSetArticles());

        assertEquals(parser.getCollections(), expectedCollections);
    }

    /* method
     * Tests: The functionality of parsing multiple files and creating different collections for each file
     * Assertions:
     *  Compares the equality of the expected 5 input sets and the result of parsing each of the 5 files
     * Note:
     *  This ensures that we can load the articles from different sources and of different sets into the parser
     *  and expect it to perform correctly
     */

    @Test
    void testParse_MixedGreenRedCollections() {
        List<String> filePaths = Arrays.asList(
        "testInputs/smallGreenSet.json", 
            "testInputs/smallMixedSet.json",
            "testInputs/smallRedSet.json",
            "testInputs/smallMixedSet.json",
            "testInputs/smallRedSet.json"
            );
        Provider provider = APIFileProvider.fromPaths(filePaths);
        Parser parser = new APIParser(provider);

        List<Collection> expectedCollections = Arrays.asList(
            ParserTestHelpers.generateCollection(() -> ParserTestHelpers.getGreenSetArticles()),
            ParserTestHelpers.generateCollection(() -> ParserTestHelpers.getMixedSetArticles()),
            ParserTestHelpers.generateCollection(() -> ParserTestHelpers.getRedSetArticles()),
            ParserTestHelpers.generateCollection(() -> ParserTestHelpers.getMixedSetArticles()),
            ParserTestHelpers.generateCollection(() -> ParserTestHelpers.getRedSetArticles())
        );

        assertEquals(expectedCollections, parser.getCollections());
    }

}
