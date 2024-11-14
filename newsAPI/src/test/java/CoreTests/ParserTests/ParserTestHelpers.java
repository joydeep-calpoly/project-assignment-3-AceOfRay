package CoreTests.ParserTests;

import java.util.List;
import java.util.Arrays;
import CustomNewsAPI.Core.APIElements.Article;
import CustomNewsAPI.Core.APIElements.Collection;
import CustomNewsAPI.Core.APIElements.Source;

import java.util.Date;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.stream.Stream;
import java.util.function.Supplier;

public class ParserTestHelpers {

    /*
     * The following 3 methods return a list of articles representing the articles in each testInput file respectively
     * They are verified to be accurate 
     */

    public static List<Article> getRedSetArticles() {

        try {
            return Arrays.asList(

                    new Article(new Source("nbc-news", "NBC News"), "David K. Li, The Associated Press",
                            null, "NHL ices referee Tim Peel after hot mic captures odd, f-bomb comment",
                            new URI("https://www.nbcnews.com/news/us-news/nhl-fires-referee-tim-peel-after-hot-mic-captures-him-n1261954")
                                    .toURL(),
                            new URI("https://media4.s-nbcnews.com/j/newscms/2021_12/3459282/210324-tim-peel-jm-1401_53a25ae71f2d6a577dbd8be6bf880c4e.nbcnews-fp-1200-630.jpg")
                                    .toURL(),
                            Date.from(Instant.parse("2021-03-24T19:06:32Z")),
                            "The NHL fired referee Tim Peel on Wednesday after a hot mic captured him boasting about whistling a penalty because he \"wanted to.\"\r\nThe league's action came less than 24 hours after Peel officiated … [+1920 chars]"),

                    new Article(new Source("the-hill", "The Hill"), "Mychael Schnell",
                            null, "A new coronavirus strain has been detected in India, the ...",
                            new URI("https://thehill.com/homenews/news/544754-new-coronavirus-strain-detected-in-india")
                                    .toURL(),
                            new URI("https://thehill.com/sites/default/files/indiacoronavirus_032421getty.jpg")
                                    .toURL(),
                            Date.from(Instant.parse("2021-03-24T18:59:09Z")),
                            "A new coronavirus strain has been detected in India, the nation's health ministry announced on Wednesday, but officials have not yet determined if the variant is contributing to a surge of cases in t… [+1764 chars]"),

                    new Article(new Source(null, "NPR"), "Rachel Treisman",
                            null,
                            "The Event Horizon Telescope project, which produced the world's first image of a black hole in 2019 in the M87 galaxy, unveiled a new view of its magnetic fields as captured by polarized light.",
                            new URI("https://www.npr.org/2021/03/24/980896706/stunning-new-image-of-black-hole-reveals-surrounding-magnetic-fields")
                                    .toURL(),
                            new URI("https://media.npr.org/assets/img/2021/03/24/black-hole_wide-9e71068489f46bf5fad165a35cfa0be937b0e968.jpg?s=1400")
                                    .toURL(),
                            Date.from(Instant.parse("2021-03-24T21:05:34Z")),
                            "The Event Horizon Telescope collaboration, which released the world's first image of a black hole in 2019, unveiled a new view on Wednesday showing how the object at the center of the M87 galaxy look… [+4196 chars]"));
        } catch (MalformedURLException | URISyntaxException e) {
            return null;
        }
    }

    public static List<Article> getGreenSetArticles() {
        try {
            return Arrays.asList(
                    new Article(new Source("nbc-news", "NBC News"), "David K. Li, The Associated Press",
                            "NHL fires referee Tim Peel after hot mic captures him saying he 'wanted to' call penalty - NBC News",
                            "NHL ices referee Tim Peel after hot mic captures odd, f-bomb comment",
                            new URI("https://www.nbcnews.com/news/us-news/nhl-fires-referee-tim-peel-after-hot-mic-captures-him-n1261954")
                                    .toURL(),
                            new URI("https://media4.s-nbcnews.com/j/newscms/2021_12/3459282/210324-tim-peel-jm-1401_53a25ae71f2d6a577dbd8be6bf880c4e.nbcnews-fp-1200-630.jpg")
                                    .toURL(),
                            Date.from(Instant.parse("2021-03-24T19:06:32Z")),
                            "The NHL fired referee Tim Peel on Wednesday after a hot mic captured him boasting about whistling a penalty because he \"wanted to.\"\r\nThe league's action came less than 24 hours after Peel officiated … [+1920 chars]"),

                    new Article(new Source("the-hill", "The Hill"), "Mychael Schnell",
                            "New coronavirus strain detected in India | TheHill - The Hill",
                            "A new coronavirus strain has been detected in India, the ...",
                            new URI("https://thehill.com/homenews/news/544754-new-coronavirus-strain-detected-in-india")
                                    .toURL(),
                            new URI("https://thehill.com/sites/default/files/indiacoronavirus_032421getty.jpg")
                                    .toURL(),
                            Date.from(Instant.parse("2021-03-24T18:59:09Z")),
                            "A new coronavirus strain has been detected in India, the nation's health ministry announced on Wednesday, but officials have not yet determined if the variant is contributing to a surge of cases in t… [+1764 chars]"),

                    new Article(new Source(null, "NPR"), "Rachel Treisman",
                            "Historic Image Of Black Hole In Polarized Light From Event Horizon Telescope - NPR",
                            "The Event Horizon Telescope project, which produced the world's first image of a black hole in 2019 in the M87 galaxy, unveiled a new view of its magnetic fields as captured by polarized light.",
                            new URI("https://www.npr.org/2021/03/24/980896706/stunning-new-image-of-black-hole-reveals-surrounding-magnetic-fields")
                                    .toURL(),
                            new URI("https://media.npr.org/assets/img/2021/03/24/black-hole_wide-9e71068489f46bf5fad165a35cfa0be937b0e968.jpg?s=1400")
                                    .toURL(),
                            Date.from(Instant.parse("2021-03-24T21:05:34Z")),
                            "The Event Horizon Telescope collaboration, which released the world's first image of a black hole in 2019, unveiled a new view on Wednesday showing how the object at the center of the M87 galaxy look… [+4196 chars]"));
        } catch (MalformedURLException | URISyntaxException e) {
            return null;
        }

    }

    static List<Article> getMixedSetArticles() {
        
        try {
            return Arrays.asList(
                new Article(new Source("nbc-news", "NBC News"), "David K. Li, The Associated Press",
                        "NHL fires referee Tim Peel after hot mic captures him saying he 'wanted to' call penalty - NBC News",
                        "NHL ices referee Tim Peel after hot mic captures odd, f-bomb comment",
                        new URI("https://www.nbcnews.com/news/us-news/nhl-fires-referee-tim-peel-after-hot-mic-captures-him-n1261954")
                                .toURL(),
                        new URI("https://media4.s-nbcnews.com/j/newscms/2021_12/3459282/210324-tim-peel-jm-1401_53a25ae71f2d6a577dbd8be6bf880c4e.nbcnews-fp-1200-630.jpg")
                                .toURL(),
                        Date.from(Instant.parse("2021-03-24T19:06:32Z")),
                        "The NHL fired referee Tim Peel on Wednesday after a hot mic captured him boasting about whistling a penalty because he \"wanted to.\"\r\nThe league's action came less than 24 hours after Peel officiated … [+1920 chars]"),

        
                new Article(new Source("the-hill", "The Hill"), "Mychael Schnell",
                        "New coronavirus strain detected in India | TheHill - The Hill",
                        "A new coronavirus strain has been detected in India, the ...",
                        new URI("https://thehill.com/homenews/news/544754-new-coronavirus-strain-detected-in-india")
                                .toURL(),
                        new URI("https://thehill.com/sites/default/files/indiacoronavirus_032421getty.jpg")
                                .toURL(),
                        Date.from(Instant.parse("2021-03-24T18:59:09Z")),
                        "A new coronavirus strain has been detected in India, the nation's health ministry announced on Wednesday, but officials have not yet determined if the variant is contributing to a surge of cases in t… [+1764 chars]"),
        
                new Article(new Source(null, "NPR"), "Rachel Treisman",
                        null,
                        "The Event Horizon Telescope project, which produced the world's first image of a black hole in 2019 in the M87 galaxy, unveiled a new view of its magnetic fields as captured by polarized light.",
                        new URI("https://www.npr.org/2021/03/24/980896706/stunning-new-image-of-black-hole-reveals-surrounding-magnetic-fields")
                                .toURL(),
                        new URI("https://media.npr.org/assets/img/2021/03/24/black-hole_wide-9e71068489f46bf5fad165a35cfa0be937b0e968.jpg?s=1400")
                                .toURL(),
                        Date.from(Instant.parse("2021-03-24T21:05:34Z")),
                        "The Event Horizon Telescope collaboration, which released the world's first image of a black hole in 2019, unveiled a new view on Wednesday showing how the object at the center of the M87 galaxy look… [+4196 chars]")
            );
        } catch (MalformedURLException | URISyntaxException e) {
            return null;
        }
    }

    /*
     * This method returns a n Collection long list of the supplier's article type
     */
    static List<Collection> generateNCollections(Integer n, Supplier<List<Article>> articleSupplier) {
        return Stream.generate(() -> new Collection("ok", 3, articleSupplier.get()))
                   .limit(n)
                   .toList();
    }

    /*
     * This method returns a Collection of the 
     */
    static Collection generateCollection(Supplier<List<Article>> supplier) {
        return new Collection ("ok", 3, supplier.get());
    }


}
