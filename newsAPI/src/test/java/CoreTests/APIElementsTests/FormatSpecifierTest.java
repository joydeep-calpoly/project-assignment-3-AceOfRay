package CoreTests.APIElementsTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;
import java.net.URL;
import java.security.InvalidParameterException;

import CustomNewsAPI.Core.Parsing.APIElements.FormatSpecifier;


public class FormatSpecifierTest {

    @Test
    public void formatSpecifierTest_ValidFileSource() {
        String validSource = "path/to/file.json";
        FormatSpecifier specifier = new FormatSpecifier(validSource, FormatSpecifier.FormatType.SIMPLE);

        assertEquals(specifier.getSource(), validSource);
        assertEquals(specifier.getType(), FormatSpecifier.FormatType.SIMPLE);
        assertTrue(specifier.isFileSource());
        assertFalse(specifier.isUrlSource());
    }

    @Test
    public void formatSpecifierTest_ValidUrlSource() throws Exception {
        URL validSource = new URI("https://example.com/api/data.json").toURL();
        FormatSpecifier specifier = new FormatSpecifier(validSource, FormatSpecifier.FormatType.API);

        assertEquals(specifier.getSource(), validSource);
        assertEquals(specifier.getType(), FormatSpecifier.FormatType.API);
        assertFalse(specifier.isFileSource());
        assertTrue(specifier.isUrlSource());
    }

    @Test
    public void formatSpecifierTest_InvalidSource() {
        assertThrows(InvalidParameterException.class, () -> new FormatSpecifier(123, FormatSpecifier.FormatType.API));
    }

    @Test
    public void formatSpecifierTest_NullSource() {
        assertThrows(InvalidParameterException.class, () -> new FormatSpecifier(null, FormatSpecifier.FormatType.SIMPLE));
    }
}