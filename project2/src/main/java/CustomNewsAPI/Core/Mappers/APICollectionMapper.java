package CustomNewsAPI.Core.Mappers;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import CustomNewsAPI.Core.APIElements.Article;
import CustomNewsAPI.Core.APIElements.Collection;

public class APICollectionMapper implements Mapper<Collection> {

    /**
     * Implementation Details:
     *      Maps JSONObjects into Collections of API Format Articles
     * @param json
     */
    @Override
    public Collection map(JSONObject json) throws JSONException {
        return new Collection(json.optString("status", null), Integer.parseInt(json.optString("totalResults", null)), parseArticles(json));
    }

    /**
     * HighLevel:
     *      This is a private method used by the map function to separate the mapping of a collection and the mapping of its articles.
     * The json parameter is expected to hold articles inside of the "articles" key
     * Implementation Details:
     *      Walk through each article in the json parameter and create an Article out of it.
     * @param json
     * @return
     */
    private List<Article> parseArticles(JSONObject json) {
        Mapper<Article> mapper = new APIArticleMapper();
        List<Article> result = new ArrayList<>();
        json.getJSONArray("articles").forEach(article -> result.add(mapper.map((JSONObject) article)));
        return result;
    }
    
}
