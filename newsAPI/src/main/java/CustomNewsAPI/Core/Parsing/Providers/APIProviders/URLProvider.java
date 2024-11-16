package CustomNewsAPI.Core.Parsing.Providers.APIProviders;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class URLProvider implements APIFormatProvider {
    private List<URI> urls;
    private List<String> jsonStrings = new ArrayList<>();

    /**
     * Constructor that takes in a list of URLS to read from
     * @param urls
     */
    public URLProvider(List<URL> urls) {
        this.urls = urls.stream()
                .map(url -> {
                    try {
                        return url.toURI();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .filter(uri -> uri != null)
                .toList();
        interpretSourceAsAPIFormattedStrings();
    }

    public URLProvider(URL url) {
        try {
            this.urls = Arrays.asList(url.toURI());
            interpretSourceAsAPIFormattedStrings();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * Implementation Details:
     *      Returns a defensive clone of the built JSONStrings
     */
    @Override
    public List<String> provideJsonAsStrings() {
        return new ArrayList<>(jsonStrings);
    }

    /**
     * Implementation Details:
     *      Builds the jsonStrings member by fetching the json from the injected URL's
     */

    @Override
    public void interpretSourceAsAPIFormattedStrings() {
        HttpClient client = HttpClient.newHttpClient();
        urls.forEach(url -> {
            jsonStrings.add(fetchJson(client, url));
        });
    }

    /**
     * Implementation Details:
     *      Performs a GET request to through the passed in client to the target URL and returns the 
     * body of the response. If request fails null is returned.
     * @param client
     * @param url
     * @return
     */
    private String fetchJson(HttpClient client, URI url) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(url)
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

}
