package CustomNewsAPI.Core.Parsing.Mappers.SimpleMappers;

import org.json.JSONException;
import org.json.JSONObject;

import CustomNewsAPI.Core.Parsing.APIElements.Article;
import CustomNewsAPI.Core.Parsing.Mappers.Mapper;

public class ArticleMapper implements Mapper<Article> {

    /**
     * Implementation Details:
     *      Maps json into a simple Article
     */
    @Override
    public Article map(JSONObject json){
        try {
            return new Article(
                    json.getString("title"),
                    json.getString("description"),
                    json.getString("url"),
                    json.getString("publishedAt"));
        } catch (Exception e) {
            throw new JSONException("Simple Articles must have non null 'title', 'description', 'url', and 'publishedAt' elements. An invalid SimpleFormat jsonString was used to create a JSONObject.\n Invalid String: " + json.toString());
        }
    }

}
