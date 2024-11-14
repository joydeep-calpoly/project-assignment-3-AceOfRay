package CoreTests.APIElementsTests;

import org.junit.jupiter.api.Test;

import CustomNewsAPI.Core.Parsing.APIElements.Article;
import CustomNewsAPI.Core.Parsing.APIElements.Collection;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class CollectionTest {

    @Test
    void collectionTestEquals_Valid() {
        Collection c1 = Collection.generateValidCollection("valid");
        Collection c2 = Collection.generateValidCollection("valid");
        assertTrue(c1.equals(c2));
    }

    @Test
    void collectionTestEquals_Invalid() {
        Collection c1 = Collection.generateValidCollection("valid");
        Collection c2 = Collection.generateValidCollection("invalid");
        assertFalse(c1.equals(c2));
    }

    /*
     * test that getAllArticles creates and returns defensive clone of the given
     * list of Articles
     */

    @Test
    void collectionTest_GetAllArticles() {
        List<Article> l = new ArrayList<>();
        l.add(Article.generateValidTestArticle("one"));
        l.add(Article.generateValidTestArticle("two"));

        Collection c = new Collection("testStatus", 0, l);

        l.add(Article.generateValidTestArticle("three"));

        assertFalse(c.getAllArticles().equals(l));

    }

    /*
     * the following two tests test that the target method creates and return a defensive clone
     * of the given list of Articles
     * and that the private population methods are functioning correctly
     */
    @Test
    void collectionTest_GetInvalidArticles() {
        List<Article> l = new ArrayList<>();
        l.add(Article.generateInvalidTestArticle());
        l.add(Article.generateValidTestArticle("two"));

        Collection c = new Collection("testStatus", 0, l);

        l.add(Article.generateInvalidTestArticle()); // check the defensive clone creation

        List<Article> expected = new ArrayList<>();
        expected.add(Article.generateInvalidTestArticle());

        assertTrue(c.getInvalidArticles().size() == (expected.size()));
    }

    @Test
    void collectionTest_GetValidArticles() {
        List<Article> l = new ArrayList<>();
        l.add(Article.generateInvalidTestArticle());
        l.add(Article.generateValidTestArticle("two"));

        Collection c = new Collection("testStatus", 0, l);

        l.add(Article.generateInvalidTestArticle()); // check the defensive clone creation

        List<Article> expected = new ArrayList<>();
        expected.add(Article.generateValidTestArticle("two"));

        assertTrue(c.getValidArticles().equals(expected));
    }


}
