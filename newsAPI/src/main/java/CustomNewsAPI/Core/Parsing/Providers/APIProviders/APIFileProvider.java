package CustomNewsAPI.Core.Parsing.Providers.APIProviders;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * HighLevel:
 *      The JSONFileProvider populates a list of File objects to process and turn
 * into Json.
 * Upon instantiation of this class it automatically populates the jsonStrings
 * property.
 * 
 */

public class APIFileProvider implements APIFormatProvider {
    private final List<File> files;
    private List<String> jsonStrings = new ArrayList<>();

    /**
     * Implementation Details:
     *      This provider reads the content from each of the given json file paths
     * and populates the jsonStrings field with the string representation of the file.
     * 
     *      Upholds the contractual agreement with the JsonProvider Interface
     */

    @Override
    public void interpretSourceAsAPIFormattedStrings() {
        files.forEach(file -> {
            try {
                jsonStrings.add(Files.readString(Path.of(file.getAbsolutePath())));
            } catch (IOException e) {
                System.out.println("Invalid File: " + file.getName());
            }
        });

    }

    /**
     * Implementation Details:
     *      Returns a defensive copy of the jsonStrings field
     * @return
     */

    @Override
    public List<String> provideJsonAsStrings() {
        return new ArrayList<>(jsonStrings);
    }

    /**
     * Constructor from a List of File objects, automatically reads files into strings
     * Creates defensive clones of given files
     * @param files
     */

    public APIFileProvider(List<File> files) {
        this.files = new ArrayList<>(files);
        interpretSourceAsAPIFormattedStrings();
    }

    /**
     * Constructor from an absolute path, automatically reads file into strings
     * @param files
     */

    public APIFileProvider(String path) {
        this.files = Arrays.asList(new File(path));
        interpretSourceAsAPIFormattedStrings();
    }

    /**
     * JsonFileProvider factory method to create a new JsonFileProvider when given a list of Paths
     * @param paths
     * @return
     */

    public static APIFileProvider fromPaths(List<String> paths) {
        List<File> files = paths.stream().map(File::new).toList();
        return new APIFileProvider(files);
    }

    /**
     * Standard Equals Method that compares the files and jsonStrings fields
     */

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof APIFileProvider)) {
            return false;
        }

        APIFileProvider other = (APIFileProvider) obj;

        return files.equals(other.files) && jsonStrings.equals(other.jsonStrings);
    }
}
