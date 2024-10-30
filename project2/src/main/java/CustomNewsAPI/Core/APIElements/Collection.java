package CustomNewsAPI.Core.APIElements;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/** 
 * High Level:
 *      A collection in this project is a grouping of articles that come from one source.
 * This object gets mapped by the Jacksonbind ObjectMapper when parsing Strings of Json. 
 * A collection consists of the status, total number of articles and lists of all, valid,
 * and invalid articles
 */

public class Collection {

    // Collection Properties
    private String status;
    private Integer totalResults;
    private List<Article> articles;
    private List<Article> validArticles;
    private List<Article> invalidArticles;

    /**
     * Constructs a Collection and creates defensive clones of mutable typed parameters
     * @param status
     * @param totalResults
     * @param articles
     */
    public Collection(
            String status,
            Integer totalResults,
            List<Article> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = new ArrayList<>(articles);
        this.validArticles = populateArticles(true);
        this.invalidArticles = populateArticles(false);
    }

    /**
     * Standard Printing Method printing any list of Articles
     */

    public void printArticles(List<Article> articles) {
         articles.forEach(Article::print);
     }     

    /**
     * Implementation Details:
     *      This method will populate both valid and invalid article lists depending on the
     * passed in valid parameter.
     * 
     * @param
     * valid == true: Returns a list of all the valid articles in the Collection
     * valid == false: Returns a list of all the invalid articles in the Collection
     * @return
     */

     private List<Article> populateArticles(boolean valid) {
       return this.articles.stream()
           .filter(article -> article.isValid() == valid)
           .toList();
     }

    /**
     * The next three methods return defensive copies of valid, invalid, 
     * and all articles in the collection respectively
     * @return
     */

    public List<Article> getValidArticles() {
        return new ArrayList<>(this.validArticles);
    }

    public List<Article> getInvalidArticles() {
        return new ArrayList<>(this.invalidArticles);
    }

    public List<Article> getAllArticles() {
        return new ArrayList<>(this.articles);
    }

    /**
     * Standard Equals Method
     */

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Collection other = (Collection) obj;

        // Check basic properties
        if (!Objects.equals(status, other.status) || !Objects.equals(totalResults, other.totalResults)) {
            return false;
        }

        // Check article lists using size and content equality
        return Objects.equals(getAllArticles(), other.getAllArticles());
    }

    /**
     * Returns the immutable original totalResults field
     * @return
     */

    public Integer getTotalResults() {
        return totalResults;
    }

    /**
     * Returns the immutable original status field
     * @return
     */

    public String getStatus() {
        return status;
    }

    /**
     * Returns a valid collection for testing purposes
     * Status is parameterized to enable control equality/inequality
     * @param status
     * @return
     */

    static public Collection generateValidCollection(String status) {
        return new Collection(status, 1, Arrays.asList(Article.generateValidTestArticle("firstArticle"),
                Article.generateValidTestArticle("secondArticle")));
    }

    /**
     * Standard toString method using a StringBuilder
     */

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Collection {")
                .append("status= ")
                .append(status)
                .append("\n")
                .append("totalResults= ")
                .append(totalResults)
                .append("\n")
                .append("articles= ")
                .append(articles)
                .append("\n")
                .append("validArticles= ")
                .append(validArticles)
                .append("\n")
                .append("invalidArticles= ")
                .append(invalidArticles)
                .append("}")
                .toString();
    }

}
