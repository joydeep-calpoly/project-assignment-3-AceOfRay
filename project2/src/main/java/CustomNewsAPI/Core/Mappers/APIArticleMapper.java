package CustomNewsAPI.Core.Mappers;

import org.json.JSONException;
import org.json.JSONObject;

import CustomNewsAPI.Core.APIElements.Article;

public class APIArticleMapper implements Mapper<Article> {

    @Override
    public Article map(JSONObject json) throws JSONException {
        try {
            return new Article(
                    json.getString("title"),
                    json.getString("description"),
                    json.getString("url"),
                    json.getString("publishedAt"));
        } catch (Exception e) {
            throw new JSONException(
                    "An invalid SimpleFormat jsonString was used to create a JSONObject.\n Invalid String: "
                            + json.toString());
        }
    }

}
