package CoreTests.MapperTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.json.JSONException;
import org.junit.jupiter.api.Test;

import CustomNewsAPI.Core.APIElements.Article;
import CustomNewsAPI.Core.Mappers.Mapper;
import CustomNewsAPI.Core.Mappers.SimpleArticleMapper;

public class SimpleArticleMapperTests {

    /**
     * Tests: That a valid article is mapped to the expected article
     */
    @Test
    void testSimpleArticleMapper_Green() {

        Article expected = new Article("Assignment #2",
                "Extend Assignment #1 to support multiple sources and to introduce source processor.",
                "https://canvas.calpoly.edu/courses/55411/assignments/274503", "2021-04-16 09:53:23.709229");

        Mapper<Article> m = new SimpleArticleMapper();
        Article actual = m.map(MapperTestHelpers.getGreenSimpleArticle());

        assertEquals(expected, actual);

    }

    /**
     * Tests: That when the mapper tries to map an invalid article, an exception is thrown
     */
    @Test
    void testSimpleArticleMapper_RedTitle() {

        Mapper<Article> m = new SimpleArticleMapper();
        assertThrows(JSONException.class, () -> {
            m.map(MapperTestHelpers.getRedSimpleArticle());
        });

        
    }

    
}
