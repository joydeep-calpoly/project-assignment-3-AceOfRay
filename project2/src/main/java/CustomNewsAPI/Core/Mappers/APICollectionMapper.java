package CustomNewsAPI.Core.Mappers;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import CustomNewsAPI.Core.APIElements.Article;
import CustomNewsAPI.Core.APIElements.Collection;

public class APICollectionMapper implements Mapper<Collection> {

    @Override
    public Collection map(JSONObject json) throws JSONException {
        return new Collection(json.getString("status"), Integer.parseInt(json.getString("totalResults")), parseArticles(json));
    }

    private List<Article> parseArticles(JSONObject json) {
        Mapper<Article> mapper = new APIArticleMapper();
        List<Article> result = new ArrayList<>();
        json.getJSONArray("articles").forEach(article -> result.add(mapper.map((JSONObject) article)));
        return result;
    }
    
}
