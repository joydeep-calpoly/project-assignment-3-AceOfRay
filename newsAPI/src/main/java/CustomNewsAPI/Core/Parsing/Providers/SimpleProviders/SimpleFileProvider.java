package CustomNewsAPI.Core.Parsing.Providers.SimpleProviders;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleFileProvider implements SimpleFormatProvider {
    private final List<File> files;
    private List<String> jsonStrings = new ArrayList<>();

    /**
     * Constructor for multiple files
     * @param files
     */
    public SimpleFileProvider(List<File> files) {
        this.files = files;
        interpretSourceAsSimpleFormattedStrings();
    }

    /**
     * Constructor for a single path
     * @param path
     */
    public SimpleFileProvider(String path) {
        this.files = Arrays.asList(new File(path));
        interpretSourceAsSimpleFormattedStrings();
    }

    /**
     * Returns a defensive clone of the built jsonStrings
     */
    @Override
    public List<String> provideJsonAsStrings() {
        return new ArrayList<>(jsonStrings);
    }

    /**
     * Implementation Details:
     *      Builds the jsonStrings list with the contents read from each file in the files list
     */
    @Override
    public void interpretSourceAsSimpleFormattedStrings() {
        files.forEach(file -> {
            try {
                jsonStrings.add(Files.readString(Path.of(file.getAbsolutePath())));
            } catch (IOException e) {
                System.out.println("Invalid File: " + file.getName());
            }
        });
    }

    
}
