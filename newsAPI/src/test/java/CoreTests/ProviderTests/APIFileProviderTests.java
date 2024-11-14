package CoreTests.ProviderTests;

import org.junit.jupiter.api.Test;

import CustomNewsAPI.Core.Providers.Provider;
import CustomNewsAPI.Core.Providers.APIProviders.APIFileProvider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.File;
import java.util.Arrays;
import java.util.List;

class APIFileProviderTests {

    /**
     * Tests: The transient and Symmetric properties of the JsonFileProviders Equals
*          method when the providers are given the same valid paths
     * Assertions:
     *     Assertions 1 and 2 test the symmetric property
     *     Assertions 3 and 4 test the transient property
     * 
     * @see JsonFileProviders.equals()
     */
    @Test
    void ProviderTest_Equals_Equal_ValidPaths() {
        String path1 = "testInputs/smallGreenSet.json";
        String path2 = "testInputs/smallGreenSet.json";
        String path3 = "testInputs/smallGreenSet.json";

        Provider provider1 = new APIFileProvider(path1);
        Provider provider2 = new APIFileProvider(path2);
        Provider provider3 = new APIFileProvider(path3);

        // symmetric
        assertEquals(provider1, provider2);
        assertEquals(provider2, provider1);

        // transient
        assertEquals(provider1, provider3);
        assertEquals(provider2, provider3);

    }

    /**
     * Tests: The transient and Symmetric properties of the JsonFileProviders Equals method
     *      when given the same invalid paths
     * Assertions:
     *      Assertions 1 and 2 test the symmetric property
     *      Assertions 3 and 4 test the transient property
     * 
     * @see JsonFileProviders.equals()
     */

    @Test
    void ProviderTest_Equals_Equal_InvalidPaths() {
        String path1 = "testInputs/undefined.json";
        String path2 = "testInputs/undefined.json";
        String path3 = "testInputs/undefined.json";

        Provider provider1 = new APIFileProvider(path1);
        Provider provider2 = new APIFileProvider(path2);
        Provider provider3 = new APIFileProvider(path3);

        // symmetric
        assertEquals(provider1, provider2);
        assertEquals(provider2, provider1);

        // transient
        assertEquals(provider1, provider3);
        assertEquals(provider2, provider3);

    }

    /**
     * Tests: That providers parse and create different file when given different valid paths 
     * Assertions:
     *      Assertions 1 and 2 test the symmetric property of the failed equals methods
     * 
     * @see JsonFileProviders.equals()
     */
    @Test
    void ProviderTest_Equals_NotEqual_ValidPaths() {
        String path1 = "testInputs/smallGreenSet.json";
        String path2 = "testInputs/smallRedSet.json";

        Provider provider1 = new APIFileProvider(path1);
        Provider provider2 = new APIFileProvider(path2);

        assertNotEquals(provider1, provider2);
        assertNotEquals(provider2, provider1);
    }

     /**
     * Tests: That providers parse and create different file when given different invalid paths 
     * Assertions:
     *      Assertions 1 and 2 test the symmetric property of the failed equals methods
     * 
     * @see JsonFileProviders.equals()
     */

    @Test
    void ProviderTest_Equals_NotEqual_InvalidPaths() {
        String path1 = "testInputs/undefined1.json";
        String path2 = "testInputs/undefined2.json";

        Provider provider1 = new APIFileProvider(path1);
        Provider provider2 = new APIFileProvider(path2);

        assertNotEquals(provider1, provider2);
        assertNotEquals(provider2, provider1);
    }

    /**
     * Tests: That the parser returns the expected jsonString from the Json File
     * Tests: The file path version of the constructor
     * Assertions:
     *      Compares the equality of the expected jsonString and the actual jsonString
     * @see JsonFileProvider.interpretSourceAsStrings()
     * @see JsonFileProvider(String filePath)
     * @see JsonFileProvider.provideJsonAsStrings()
     */
    @Test
    void jsonFileProviderTest_ProvideJsonAsStrings_FromSinglePath() {
        String filePath = "testInputs/smallGreenSet.json";
        Provider provider = new APIFileProvider(filePath);

        String expectedJsonString = ProviderTestHelpers.getGreenSetAsString();

        assertEquals(expectedJsonString, provider.provideJsonAsStrings().get(0));
    }

    /**
     * Tests: That the parser returns the expected jsonString from the Json File
     * Tests: The fromPaths factory method inside the JsonFileProvider
     * Assertions:
     *      Compares the equality of the expected jsonString and the actual jsonString
     * @see JsonFileProvider.interpretSourceAsStrings()
     * @see JsonFileProvider.fromPaths()
     * @see JsonFileProvider.provideJsonAsStrings()
     */
    @Test
    void jsonFileProviderTest_ProvideJsonAsStrings_FromMultiplePaths() {
        List<String> filePaths = Arrays.asList("testInputs/smallGreenSet.json", "testInputs/smallRedSet.json", "testInputs/smallMixedSet.json");
        Provider provider = APIFileProvider.fromPaths(filePaths);

        List<String> expectedJsonStrings = Arrays.asList(
            ProviderTestHelpers.getGreenSetAsString(),
            ProviderTestHelpers.getRedSetAsString(),
            ProviderTestHelpers.getMixedSetAsString()
        );

        assertEquals(expectedJsonStrings, provider.provideJsonAsStrings());
    }

    /**
     * Tests: That the parser returns the expected jsonString from the Json File
     * Tests: The fromPaths factory method inside the JsonFileProvider
     * Assertions:
     *      Compares the equality of the expected jsonString and the actual jsonString
     * @see JsonFileProvider.interpretSourceAsStrings()
     * @see JsonFileProvider.fromFiles()
     * @see JsonFileProvider.provideJsonAsStrings()
     */
    @Test
    void jsonFileProviderTest_ProvideJsonAsStrings_FromMultipleFiles() {
        List<File> files = Arrays.asList(
            new File ("testInputs/smallGreenSet.json"), 
            new File("testInputs/smallRedSet.json"), 
            new File("testInputs/smallMixedSet.json")
            );
        Provider provider = new APIFileProvider(files);

        List<String> expectedJsonStrings = Arrays.asList(
            ProviderTestHelpers.getGreenSetAsString(),
            ProviderTestHelpers.getRedSetAsString(),
            ProviderTestHelpers.getMixedSetAsString()
        );

        assertEquals(expectedJsonStrings, provider.provideJsonAsStrings());
    }

    /**
     * Tests: That the parser returns the expected jsonStrings from only the valid files
     *      in a group of valid and invalid files
     * Tests: The fromPaths factory method inside the JsonFileProvider
     * Assertions:
     *      Compares the equality of the expected jsonString and the actual jsonString
     * @see JsonFileProvider.interpretSourceAsStrings()
     * @see JsonFileProvider.fromFiles()
     * @see JsonFileProvider.provideJsonAsStrings()
     * 
     * Note: The parser was given some valid and some invalid files and returned only the valid json from
     *      the valid files
     */

    @Test
    void jsonFileProviderTest_ProvideJsonAsStrings_FromMultipleFiles_SomeValid() {
        List<File> files = Arrays.asList(
            new File("testInputs/smallGreenSet.json"), 
            new File("testInputs/undefined1.json"), 
            new File("testInputs/smallRedSet.json"), 
            new File("testInputs/undefined2.json"), 
            new File("testInputs/smallMixedSet.json"),
            new File("testInputs/undefined3.json")
            );
        Provider provider = new APIFileProvider(files);

        List<String> expectedJsonStrings = Arrays.asList(
            ProviderTestHelpers.getGreenSetAsString(),
            ProviderTestHelpers.getRedSetAsString(),
            ProviderTestHelpers.getMixedSetAsString()
        );

        assertEquals(expectedJsonStrings, provider.provideJsonAsStrings());
    }




}