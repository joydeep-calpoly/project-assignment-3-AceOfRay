package CustomNewsAPI.Core.Mappers;

import org.json.JSONException;
import org.json.JSONObject;

public interface Mapper<T> {
    
    public T map(JSONObject json) throws JSONException;
}
