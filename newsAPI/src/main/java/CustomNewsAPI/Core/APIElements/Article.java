package CustomNewsAPI.Core.APIElements;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Objects;

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
     * @param source
     * @param author
     * @param title
     * @param description
     * @param url
     * @param urlToImage
     * @param publishedAt
     * @param content
     */
    public Article(
        Source source,
        String author,
        String title,
        String description,
        String url,
        String urlToImage,
        Date publishedAt,
        String content
        ) {
    this.source = source;
    this.author = author;
    this.title = title;
    this.description = description;
    this.content = content;

    try {
        this.url = new URI(url).toURL();
        this.urlToImage = new URI(urlToImage).toURL();
        this.publishedAt = new Date(publishedAt.getTime());
    } catch (NullPointerException | URISyntaxException | MalformedURLException e) {
        System.err.println(e.getMessage());
        System.exit(1);
    }
}

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
    public Article(
            Source source,
            String author,
            String title,
            String description,
            URL url,
            URL urlToImage,
            Date publishedAt,
            String content) {
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
     * A SimpleFormat constructor that makes unidentified parameters null and creates defensive copies of mutable parameters
     * @param title
     * @param description
     * @param url
     * @param publishedAt
     */

    public Article(
        String title,
        String description,
        String url,
        String publishedAt
    ) {
        this.title = title;
        this.description = description;


        this.source = null;
        this.author = null;
        this.content = null;
        this.urlToImage = null;

        // special logic for parsing the new simple format date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.nnnnnn");
        LocalDateTime ldt = LocalDateTime.parse(publishedAt, dtf);
        Instant i = ldt.atZone(ZoneOffset.UTC).toInstant();
        this.publishedAt = Date.from(i);

        try {
            this.url = new URI(url).toURL();
        } catch (NullPointerException | URISyntaxException | MalformedURLException e) {
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

    public boolean isValid() {
        return !(title == null || description == null || url == null || publishedAt == null);
    }

    /**
     * Standard Print method
     */

    void print() {
        System.out.println(this.toString());
    }

    /**
     * Standard toString method using a StringBuilder for efficiency
     */

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