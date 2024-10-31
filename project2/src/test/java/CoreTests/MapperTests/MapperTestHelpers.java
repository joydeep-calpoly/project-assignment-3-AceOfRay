package CoreTests.MapperTests;

import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.File;
import java.io.IOException;
public class MapperTestHelpers {

    static String getGreenAPIArticleJsonArray() {
        return "\"articles\": [\r\n" + //
                "    {\r\n" + //
                "      \"source\": {\r\n" + //
                "        \"id\": \"nbc-news\",\r\n" + //
                "        \"name\": \"NBC News\"\r\n" + //
                "      },\r\n" + //
                "      \"author\": \"David K. Li, The Associated Press\",\r\n" + //
                "      \"title\": \"NHL fires referee Tim Peel after hot mic captures him saying he 'wanted to' call penalty - NBC News\",\r\n"
                + //
                "      \"description\": \"NHL ices referee Tim Peel after hot mic captures odd, f-bomb comment\",\r\n" + //
                "      \"url\": \"https://www.nbcnews.com/news/us-news/nhl-fires-referee-tim-peel-after-hot-mic-captures-him-n1261954\",\r\n"
                + //
                "      \"urlToImage\": \"https://media4.s-nbcnews.com/j/newscms/2021_12/3459282/210324-tim-peel-jm-1401_53a25ae71f2d6a577dbd8be6bf880c4e.nbcnews-fp-1200-630.jpg\",\r\n"
                + //
                "      \"publishedAt\": \"2021-03-24T19:06:32Z\",\r\n" + //
                "      \"content\": \"The NHL fired referee Tim Peel on Wednesday after a hot mic captured him boasting about whistling a penalty because he \\\"wanted to.\\\"\\r\\n"
                + //
                "The league's action came less than 24 hours after Peel officiated … [+1920 chars]\"\r\n" + //
                "    },\r\n" + //
                "    {\r\n" + //
                "      \"source\": {\r\n" + //
                "        \"id\": \"the-hill\",\r\n" + //
                "        \"name\": \"The Hill\"\r\n" + //
                "      },\r\n" + //
                "      \"author\": \"Mychael Schnell\",\r\n" + //
                "      \"title\": \"New coronavirus strain detected in India | TheHill - The Hill\",\r\n" + //
                "      \"description\": \"A new coronavirus strain has been detected in India, the ...\",\r\n" + //
                "      \"url\": \"https://thehill.com/homenews/news/544754-new-coronavirus-strain-detected-in-india\",\r\n"
                + //
                "      \"urlToImage\": \"https://thehill.com/sites/default/files/indiacoronavirus_032421getty.jpg\",\r\n"
                + //
                "      \"publishedAt\": \"2021-03-24T18:59:09Z\",\r\n" + //
                "      \"content\": \"A new coronavirus strain has been detected in India, the nation's health ministry announced on Wednesday, but officials have not yet determined if the variant is contributing to a surge of cases in t… [+1764 chars]\"\r\n"
                + //
                "    },\r\n" + //
                "    {\r\n" + //
                "      \"source\": {\r\n" + //
                "        \"id\": null,\r\n" + //
                "        \"name\": \"NPR\"\r\n" + //
                "      },\r\n" + //
                "      \"author\": \"Rachel Treisman\",\r\n" + //
                "      \"title\": \"Historic Image Of Black Hole In Polarized Light From Event Horizon Telescope - NPR\",\r\n"
                + //
                "      \"description\": \"The Event Horizon Telescope project, which produced the world's first image of a black hole in 2019 in the M87 galaxy, unveiled a new view of its magnetic fields as captured by polarized light.\",\r\n"
                + //
                "      \"url\": \"https://www.npr.org/2021/03/24/980896706/stunning-new-image-of-black-hole-reveals-surrounding-magnetic-fields\",\r\n"
                + //
                "      \"urlToImage\": \"https://media.npr.org/assets/img/2021/03/24/black-hole_wide-9e71068489f46bf5fad165a35cfa0be937b0e968.jpg?s=1400\",\r\n"
                + //
                "      \"publishedAt\": \"2021-03-24T21:05:34Z\",\r\n" + //
                "      \"content\": \"The Event Horizon Telescope collaboration, which released the world's first image of a black hole in 2019, unveiled a new view on Wednesday showing how the object at the center of the M87 galaxy look… [+4196 chars]\"\r\n"
                + //
                "    }\r\n" + //
                "  ]";
    }

    static String getGreenAPIArticle() {
        return "{\r\n" + //
                "      \"source\": {\r\n" + //
                "        \"id\": \"nbc-news\",\r\n" + //
                "        \"name\": \"NBC News\"\r\n" + //
                "      },\r\n" + //
                "      \"author\": \"David K. Li, The Associated Press\",\r\n" + //
                "      \"title\": \"NHL fires referee Tim Peel after hot mic captures him saying he 'wanted to' call penalty - NBC News\",\r\n"
                + //
                "      \"description\": \"NHL ices referee Tim Peel after hot mic captures odd, f-bomb comment\",\r\n" + //
                "      \"url\": \"https://www.nbcnews.com/news/us-news/nhl-fires-referee-tim-peel-after-hot-mic-captures-him-n1261954\",\r\n"
                + //
                "      \"urlToImage\": \"https://media4.s-nbcnews.com/j/newscms/2021_12/3459282/210324-tim-peel-jm-1401_53a25ae71f2d6a577dbd8be6bf880c4e.nbcnews-fp-1200-630.jpg\",\r\n"
                + //
                "      \"publishedAt\": \"2021-03-24T19:06:32Z\",\r\n" + //
                "      \"content\": \"The NHL fired referee Tim Peel on Wednesday after a hot mic captured him boasting about whistling a penalty because he \\\"wanted to.\\\"\\r\\n"
                + //
                "The league's action came less than 24 hours after Peel officiated … [+1920 chars]\"\r\n" + //
                "    }";
    }

    static String getRedAPIArticle() {
        return "{\r\n" + //
                "      \"source\": {\r\n" + //
                "        \"id\": \"nbc-news\",\r\n" + //
                "        \"name\": \"NBC News\"\r\n" + //
                "      },\r\n" + //
                "      \"author\": \"David K. Li, The Associated Press\",\r\n" + //
                "      \r\n" + //
                "      \"description\": \"NHL ices referee Tim Peel after hot mic captures odd, f-bomb comment\",\r\n" + //
                "      \"url\": \"https://www.nbcnews.com/news/us-news/nhl-fires-referee-tim-peel-after-hot-mic-captures-him-n1261954\",\r\n"
                + //
                "      \"urlToImage\": \"https://media4.s-nbcnews.com/j/newscms/2021_12/3459282/210324-tim-peel-jm-1401_53a25ae71f2d6a577dbd8be6bf880c4e.nbcnews-fp-1200-630.jpg\",\r\n"
                + //
                "      \"publishedAt\": \"2021-03-24T19:06:32Z\",\r\n" + //
                "      \"content\": \"The NHL fired referee Tim Peel on Wednesday after a hot mic captured him boasting about whistling a penalty because he \\\"wanted to.\\\"\\r\\n"
                + //
                "The league's action came less than 24 hours after Peel officiated … [+1920 chars]\"\r\n" + //
                "    }";
    }


    static JSONObject getGreenAPICollection() {
        try {
            return new JSONObject(
            Files.readString(Path.of(new File("testInputs/smallGreenSet.json").getAbsolutePath()))
        );
        } catch (IOException e) {
            System.out.println("Invalid File: testInputs/smallGreenSet.json");
            return null;
        }
        
    }

    static JSONObject getRedAPICollection() {
        try {
            return new JSONObject(
            Files.readString(Path.of(new File("testInputs/smallRedSet.json").getAbsolutePath()))
        );
        } catch (IOException e) {
            System.out.println("Invalid File: testInputs/smallGreenSet.json");
            return null;
        }
        
    }

}
