package CustomNewsAPI.Core.APIElements;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An article in this project represents a real news article, it is composed of
 * a generous amount of metadata and data.
 */

public class Article {

    // Article Fields
    private Source source;
    private String author;
    private String title;
    private String description;
    private URL url;
    private URL urlToImage;
    private Date publishedAt;
    private String content;

    /**
     * Constructor that creates defensive clones of the mutable passed in parameters
     * 
     * @param source
     * @param author
     * @param title
     * @param description
     * @param url
     * @param urlToImage
     * @param publishedAt
     * @param content
     */
    @JsonCreator
    public Article(
            @JsonProperty("source") Source source,
            @JsonProperty("author") String author,
            @JsonProperty("title") String title,
            @JsonProperty("description") String description,
            @JsonProperty("url") URL url,
            @JsonProperty("urlToImage") URL urlToImage,
            @JsonProperty("publishedAt") Date publishedAt,
            @JsonProperty("content") String content) {
        this.source = source;
        this.author = author;
        this.title = title;
        this.description = description;
        this.content = content;

        try {
            this.url = url.toURI().toURL();
            this.urlToImage = urlToImage;
            this.publishedAt = new Date(publishedAt.getTime());
        } catch (NullPointerException e) {

        } catch (URISyntaxException | MalformedURLException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Implementation Details:
     * An article is valid if it has a title, description, url, and a date. This
     * method
     * applies that logic to determine and returns the result.
     * 
     * @return
     */

    @JsonIgnore
    public boolean isValid() {
        return !(title == null || description == null || url == null || publishedAt == null);
    }

    /**
     * Standard Print method
     */

    @JsonIgnore
    void print() {
        System.out.println(this.toString());
    }

    /**
     * Standard toString method using a StringBuilder for efficiency
     */

    @JsonIgnore
    @Override
    public String toString() {
        return new StringBuilder()
                .append("Title: ")
                .append(title)
                .append("\n")
                .append("Description: ")
                .append(description)
                .append("\n")
                .append("Date Published: ")
                .append(publishedAt)
                .append("\n")
                .append("Source Link: ")
                .append(url)
                .append("\n")
                .toString();

    }

    /*
     * Implementation Details:
     * This equals method enforces the reflexive, transient, and symmetric
     * properties of an object
     * It uses Objects.equals to ensure that if corresponding properties are null,
     * those properties are equal
     */
    @JsonIgnore
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Article)) {
            return false;
        }
        Article other = (Article) obj;

        return Objects.equals(title, other.title) &&
                Objects.equals(description, other.description) &&
                Objects.equals(publishedAt, other.publishedAt) &&
                Objects.equals(url, other.url) &&
                Objects.equals(urlToImage, other.urlToImage) &&
                Objects.equals(source, other.source) &&
                Objects.equals(author, other.author) &&
                Objects.equals(content, other.content);

    }

    /**
     * Implementation Details:
     * Built to return a valid Article for testing, naming is parameterized for
     * testing purposes
     * 
     * @param title
     * @return
     */

    static public Article generateValidTestArticle(String title) {
        Source s = Source.generateValidTestSource("testSource");
        try {
            return new Article(s, "testAuthor", title, "testDescription",
                    new URI("https://www.example.com").toURL(),
                    new URI("https://www.example.com/image").toURL(), new Date(1234567), "testContent");

        } catch (URISyntaxException | MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Implementation Details:
     * Built to return an invalid Article for testing
     * 
     * @param title
     * @return
     */

    static public Article generateInvalidTestArticle() {
        Source s = Source.generateValidTestSource("testSource");
        try {
            return new Article(s, null, null, null, new URI("https://www.example.com").toURL(),
                    new URI("https://www.example.com/image").toURL(), null, "testContent");

        } catch (URISyntaxException | MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }
}