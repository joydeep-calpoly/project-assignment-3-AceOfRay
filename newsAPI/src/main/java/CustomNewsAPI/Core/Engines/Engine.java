package CustomNewsAPI.Core.Engines;

import java.io.File;
import java.util.List;

import CustomNewsAPI.Core.Parsing.APIElements.Format;

/**
 * High Level:
 *      The Engine in this case is what interacts with the user defined inputs in order to parse the the given 
 * articles appropriately. User Defined Inputs are stored in [name]_engine_config.json files. These files will 
 * contain article sources as well as their formats. The Engine will read the content of the given file and 
 * parse it. 
 */

public interface Engine {

    /**
     * High Level: 
     *      The Start method of engines require Formats to read from to obtain sources.
     * The implementing class will create parsers to visit build collections from the input sources.
     * @param file
     */

    public void start(Format f);

    /**
     * High Level:
     *      The Rev method of engines feed new sources and formats to the existing parsers which then add new
     * collections to the parsers.
     * @param file
     */
    public void rev(Format f);
}
