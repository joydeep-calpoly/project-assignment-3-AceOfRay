package CustomNewsAPI.Core.Parsing.Mappers.APIMappers;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.time.Instant;

import org.json.JSONException;
import org.json.JSONObject;

import CustomNewsAPI.Core.Parsing.APIElements.Article;
import CustomNewsAPI.Core.Parsing.APIElements.ArticleSource;
import CustomNewsAPI.Core.Parsing.Mappers.Mapper;

public class ArticleMapper implements Mapper<Article> {

    /**
     * HighLevel:
     *      The APIArticleMapper maps JsonObjects into Articles
     * Implementation Details:
     *      This function uses the optString method of a JSONObject to get the 
     * value associated with an article and null if it isn't present.
     * @param json
     */

    @Override
    public Article map(JSONObject json) throws JSONException {
        try {
            JSONObject s = json.getJSONObject("source");
            return new Article(
                    new ArticleSource(s.optString("id", null), s.optString("name", null)),
                    json.optString("author", null),
                    json.optString("title", null),
                    json.optString("description", null),
                    json.optString("url", null),
                    json.optString("urlToImage", null),
                    Date.from(Instant.from(DateTimeFormatter.ISO_INSTANT.parse(json.optString("publishedAt", null)))),
                    json.optString("content", null));
        } catch (JSONException e) {
            // Handle missing fields gracefully
            return new Article(
                    null, // Source
                    json.optString("author", null),
                    json.optString("title", null),
                    json.optString("description", null),
                    json.optString("url", null),
                    json.optString("urlToImage", null),
                    null, // PublishedAt
                    json.optString("content", null));
        }

    }
}
