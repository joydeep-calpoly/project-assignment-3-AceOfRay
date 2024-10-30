package CoreTests.APIElementsTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import CustomNewsAPI.Core.APIElements.Article;
import CustomNewsAPI.Core.APIElements.Source;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URISyntaxException;
import java.util.Date;

public class ArticleTest {

    @Test
    void articleTestEquals_BothValidAndEqual() {
        Article a = Article.generateValidTestArticle("identical");
        Article b = Article.generateValidTestArticle("identical");

        assertTrue(a.equals(b));
    }

    @Test
    void articleTestEquals_MixedAndUnequal() {
        Article a = Article.generateValidTestArticle("identical");
        Article b = Article.generateValidTestArticle("different");

        assertFalse(a.equals(b));
    }

    @Test
    void articleTestEquals_BothInvalidAndEqual() {
        Article a = Article.generateInvalidTestArticle();
        Article b = Article.generateInvalidTestArticle();

        // fails but shouldn't
        // need to reqrite article equals to account for equal invalid articles
        assertTrue(a.equals(b));
    }

    /*
    * this tests the mutability of the Article Class
    * The three mutable instance variables of the Article class are url, urlToImage, and publishedAt
    * these three are tested here by changing the values of the each and expecting the custom equals 
    * method i wrote to catch it
    */
    @Test
    void articleTest_Immutability() {
        
        try {

            URL aLink = new URI("https://example.com").toURL();
            URL aImageLink = new URI("https://example.com/image").toURL();
            Date aDate = new Date(0);

            URL bLink = aLink;
            URL bImageLink = aImageLink;
            Date bDate = aDate;

            URL cLink = new URI("https://otherExample.com").toURL();
            URL cImageLink = new URI("https://otherEexample.com/image").toURL();
            Date cDate = new Date(1);
            
            Article a = new Article(
                    new Source("id", "name"),
                    "author",
                    "title",
                    "description", aLink, aImageLink,
                    aDate, "content");

            Article b = new Article(
                        new Source("id", "name"),
                        "author",
                        "title",
                        "description", bLink, bImageLink,
                    bDate, "content");

            assertTrue(a.equals(b));

            Article c = new Article(
                        new Source("id", "name"),
                        "author",
                        "title",
                        "description", cLink, cImageLink,
                    cDate, "content");
                        
            bLink = cLink;
            bImageLink = cImageLink;
            bDate = cDate;

            assertTrue(a.equals(b));
            assertFalse(a.equals(c));

        } catch (MalformedURLException | URISyntaxException e) {
            assertFalse(true);
        }
    }

}
