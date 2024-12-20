package CustomNewsAPI.Core.Parsing.Providers.SimpleProviders;

import CustomNewsAPI.Core.Parsing.Providers.Provider;

public interface SimpleFormatProvider extends Provider{

    /**
     * High Level:
     *      This method populates the implementing class with the Strings of Json it needs for the Parser
     * it is providing for
     */
     public void interpretSourceAsSimpleFormattedStrings();
}
