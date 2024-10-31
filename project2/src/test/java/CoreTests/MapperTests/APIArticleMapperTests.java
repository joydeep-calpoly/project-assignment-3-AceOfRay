package CoreTests.MapperTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.util.Date;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import CustomNewsAPI.Core.APIElements.Article;
import CustomNewsAPI.Core.Mappers.APIArticleMapper;
import CustomNewsAPI.Core.Mappers.Mapper;
import CustomNewsAPI.Core.APIElements.Source;

public class APIArticleMapperTests {

    /**
     * Tests:
     *      That a single green Article gets mapped correctly
     */
    @Test
    void testAPIArticleMapper_SingleGreen() {
        Article expectedArticle = new Article(
                new Source("nbc-news", "NBC News"), "David K. Li, The Associated Press",
                "NHL fires referee Tim Peel after hot mic captures him saying he 'wanted to' call penalty - NBC News",
                "NHL ices referee Tim Peel after hot mic captures odd, f-bomb comment",
                "https://www.nbcnews.com/news/us-news/nhl-fires-referee-tim-peel-after-hot-mic-captures-him-n1261954",
                "https://media4.s-nbcnews.com/j/newscms/2021_12/3459282/210324-tim-peel-jm-1401_53a25ae71f2d6a577dbd8be6bf880c4e.nbcnews-fp-1200-630.jpg",
                Date.from(Instant.parse("2021-03-24T19:06:32Z")),
                "The NHL fired referee Tim Peel on Wednesday after a hot mic captured him boasting about whistling a penalty because he \"wanted to.\"\r\nThe league's action came less than 24 hours after Peel officiated … [+1920 chars]"
                );
        
        Mapper<Article> m = new APIArticleMapper();
        JSONObject testObj = new JSONObject(MapperTestHelpers.getGreenAPIArticle());
        Article actualArticle = m.map(testObj);
        
        assertEquals(expectedArticle, actualArticle);

    }

    /**
     * Tests:
     *      That a single red Article gets mapped correctly
     * Notes:
     *      This article is red because it is missing the title attribute
     */
    @Test
    void testAPIArticleMapper_SingleRed() {
        Article expectedArticle = new Article(
                new Source("nbc-news", "NBC News"), "David K. Li, The Associated Press",
                null,
                "NHL ices referee Tim Peel after hot mic captures odd, f-bomb comment",
                "https://www.nbcnews.com/news/us-news/nhl-fires-referee-tim-peel-after-hot-mic-captures-him-n1261954",
                "https://media4.s-nbcnews.com/j/newscms/2021_12/3459282/210324-tim-peel-jm-1401_53a25ae71f2d6a577dbd8be6bf880c4e.nbcnews-fp-1200-630.jpg",
                Date.from(Instant.parse("2021-03-24T19:06:32Z")),
                "The NHL fired referee Tim Peel on Wednesday after a hot mic captured him boasting about whistling a penalty because he \"wanted to.\"\r\nThe league's action came less than 24 hours after Peel officiated … [+1920 chars]"
                );
        
        Mapper<Article> m = new APIArticleMapper();
        JSONObject testObj = new JSONObject(MapperTestHelpers.getRedAPIArticle());
        Article actualArticle = m.map(testObj);
        
        assertEquals(expectedArticle, actualArticle);

    }
}
