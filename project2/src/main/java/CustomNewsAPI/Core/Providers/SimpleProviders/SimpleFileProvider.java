package CustomNewsAPI.Core.Providers.SimpleProviders;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SimpleFileProvider implements SimpleFormatProvider {
    private final List<File> files;
    private List<String> jsonStrings = new ArrayList<>();

    public SimpleFileProvider(List<File> files) {
        this.files = files;
        interpretSourceAsSimpleFormattedStrings();
    }

    @Override
    public List<String> provideJsonAsStrings() {
        return new ArrayList<>(jsonStrings);
    }

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
