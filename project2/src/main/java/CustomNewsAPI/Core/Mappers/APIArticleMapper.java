package CustomNewsAPI.Core.Mappers;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.time.Instant;

import org.json.JSONException;
import org.json.JSONObject;

import CustomNewsAPI.Core.APIElements.Article;
import CustomNewsAPI.Core.APIElements.Source;

public class APIArticleMapper implements Mapper<Article> {

    @Override
    public Article map(JSONObject json) throws JSONException {
        try {
            JSONObject s = json.getJSONObject("source");
            return new Article(
                    new Source(s.getString("id"), s.getString("name")),
                    json.getString("author"),
                    json.getString("title"),
                    json.getString("description"),
                    json.getString("url"),
                    json.getString("urlToImage"),
                    Date.from(Instant.from(DateTimeFormatter.ISO_INSTANT.parse(json.getString("publishedAt")))),
                    json.getString("content"));
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
