package CoreTests.ProviderTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import CustomNewsAPI.Core.Providers.Provider;
import CustomNewsAPI.Core.Providers.SimpleProviders.SimpleFileProvider;

/**
 * High Level:
 *      The SimpleFileProvider depends on the functionality of a standard java library 
 * reading method, and doesn't need to be tested extensively.
 */

public class SimpleFileProviderTests {

    /**
     * Tests: That the provider provides all valid articles from multiple valid green file
     */
    @Test
    void providerTest_greenArticles() {

        List<String> expectedStrings = Arrays.asList(
            "{\r\n" + //
            "    \"description\": \"Extend Assignment #1 to support multiple sources and to introduce source processor.\",\r\n" + //
            "    \"publishedAt\": \"2021-04-16 09:53:23.709229\",\r\n" + //
            "    \"title\": \"Assignment #2\",\r\n" + //
            "    \"url\": \"https://canvas.calpoly.edu/courses/55411/assignments/274503\"\r\n" + //
            "  }", "{\r\n" + //
            "    \"description\": \"Extend Assignment #1 to support multiple sources and to introduce source processor.\",\r\n" + //
            "    \"publishedAt\": \"2021-04-16 09:53:23.709229\",\r\n" + //
            "    \"title\": \"Assignment #2\",\r\n" + //
            "    \"url\": \"https://canvas.calpoly.edu/courses/55411/assignments/274503\"\r\n" + //
            "  }", "{\r\n" + //
            "    \"description\": \"Extend Assignment #1 to support multiple sources and to introduce source processor.\",\r\n" + //
            "    \"publishedAt\": \"2021-04-16 09:53:23.709229\",\r\n" + //
            "    \"title\": \"Assignment #2\",\r\n" + //
            "    \"url\": \"https://canvas.calpoly.edu/courses/55411/assignments/274503\"\r\n" + //
            "  }"
        );

        List<File> files = Arrays.asList(
            new File("testInputs/simpleFormatInputs/greenSimple.json"),
            new File("testInputs/simpleFormatInputs/greenSimple.json"),
            new File("testInputs/simpleFormatInputs/greenSimple.json")
        );

        Provider provider = new SimpleFileProvider(files);
        assertEquals(expectedStrings, provider.provideJsonAsStrings());

    }

    /**
     * Tests: That the provider provides all articles from multiple red files
     */
    @Test
    void providerTest_redArticles() {
        List<String> expectedStrings = Arrays.asList(
            "{\r\n" + //
            "    \"description\": \"Extend Assignment #1 to support multiple sources and to introduce source processor.\",\r\n" + //
            "    \"publishedAt\": \"2021-04-16 09:53:23.709229\",\r\n" + //
            "    \"url\": \"https://canvas.calpoly.edu/courses/55411/assignments/274503\"\r\n" + //
            "  }", "{\r\n" + //
            "    \"description\": \"Extend Assignment #1 to support multiple sources and to introduce source processor.\",\r\n" + //
            "    \"publishedAt\": \"2021-04-16 09:53:23.709229\",\r\n" + //
            "    \"url\": \"https://canvas.calpoly.edu/courses/55411/assignments/274503\"\r\n" + //
            "  }", "{\r\n" + //
            "    \"description\": \"Extend Assignment #1 to support multiple sources and to introduce source processor.\",\r\n" + //
            "    \"publishedAt\": \"2021-04-16 09:53:23.709229\",\r\n" + //
            "    \"url\": \"https://canvas.calpoly.edu/courses/55411/assignments/274503\"\r\n" + //
            "  }"
        );

        List<File> files = Arrays.asList(
            new File("testInputs/simpleFormatInputs/redSimple.json"),
            new File("testInputs/simpleFormatInputs/redSimple.json"),
            new File("testInputs/simpleFormatInputs/redSimple.json")
        );

        Provider provider = new SimpleFileProvider(files);
        assertEquals(expectedStrings, provider.provideJsonAsStrings());
    }

    /**
     * Tests: That the provider provides all articles from both red and green files
     */
    @Test
    void providerTest_mixedArticles() {
        List<String> expectedStrings = Arrays.asList(
            "{\r\n" + //
            "    \"description\": \"Extend Assignment #1 to support multiple sources and to introduce source processor.\",\r\n" + //
            "    \"publishedAt\": \"2021-04-16 09:53:23.709229\",\r\n" + //
            "    \"title\": \"Assignment #2\",\r\n" + //
            "    \"url\": \"https://canvas.calpoly.edu/courses/55411/assignments/274503\"\r\n" + //
            "  }", "{\r\n" + //
            "    \"description\": \"Extend Assignment #1 to support multiple sources and to introduce source processor.\",\r\n" + //
            "    \"publishedAt\": \"2021-04-16 09:53:23.709229\",\r\n" + //
            "    \"url\": \"https://canvas.calpoly.edu/courses/55411/assignments/274503\"\r\n" + //
            "  }", "{\r\n" + //
            "    \"description\": \"Extend Assignment #1 to support multiple sources and to introduce source processor.\",\r\n" + //
            "    \"publishedAt\": \"2021-04-16 09:53:23.709229\",\r\n" + //
            "    \"url\": \"https://canvas.calpoly.edu/courses/55411/assignments/274503\"\r\n" + //
            "  }"
        );

        List<File> files = Arrays.asList(
            new File("testInputs/simpleFormatInputs/greenSimple.json"),
            new File("testInputs/simpleFormatInputs/redSimple.json"),
            new File("testInputs/simpleFormatInputs/redSimple.json")
        );

        Provider provider = new SimpleFileProvider(files);
        assertEquals(expectedStrings, provider.provideJsonAsStrings());
    }

    /**
     * Tests: that the provider provides an empty string from an empty file
     */
    @Test
    void providerTest_noArticles() {
        List<File> files = Arrays.asList(
            new File("testInputs/simpleFormatInputs/empty.json"),
            new File("testInputs/simpleFormatInputs/empty.json"),
            new File("testInputs/simpleFormatInputs/empty.json")
        );

        Provider provider = new SimpleFileProvider(files);
        assertEquals(Arrays.asList("", "", ""), provider.provideJsonAsStrings());
    }
}
