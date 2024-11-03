package CustomNewsAPI.Core.Providers.APIProviders;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class URLProvider implements APIFormatProvider {
    private List<URI> urls;
    private List<String> jsonStrings = new ArrayList<>();

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

    @Override
    public List<String> provideJsonAsStrings() {
        return new ArrayList<>(jsonStrings);
    }

    @Override
    public void interpretSourceAsAPIFormattedStrings() {
        HttpClient client = HttpClient.newHttpClient();
        urls.forEach(url -> {
            jsonStrings.add(fetchJson(client, url));
        });
    }

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
