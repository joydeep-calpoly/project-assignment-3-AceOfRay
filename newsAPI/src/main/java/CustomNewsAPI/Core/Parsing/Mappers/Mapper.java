package CustomNewsAPI.Core.Parsing.Mappers;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * High Level:
 *      After using Jacksonbind for the first project assignment, I discovered some drawbacks to the library.
 * 
 *      The first being that I could not log invalid articles on the incoming stream because jacksonbind abstracted 
 * the error catching away from me. This meant that I had to log errors after the target Element had been built instead
 * of during the process. I did not like that
 * 
 *      The second drawback is that Jacksonbind did not support the use of multiple constructors to create different "types" 
 * of articles. When the "simple" form of the article was introduced all I had to do to enable my system to handle it was
 * add a new constructor. I then tried to use jacksonbind to map to that constructor specifically and I discovered that 
 * jacksonbind does not allow for multiple JsonCreator decorators. Thus, I made my own system of Mapping. Note that I also
 * considered creating a type hierarchy for different types of articles that share a class, however in doing so I would have
 * to refactor everything just for that benefit.
 * 
 *      The drawback of using my own is that I had to refactor a lot of old code and test code for the old parser to move
 *  away from jacksonbind to my version. 
 */

public interface Mapper<T> {
    
    /**
     * High Level: 
     *      The implementing class should map the json object into the desired type, specified by the client
     * @param json
     * @return
     * @throws JSONException
     */
    public T map(JSONObject json) throws JSONException;
}
