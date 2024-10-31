package CoreTests.MapperTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import CoreTests.ParserTests.ParserTestHelpers;
import CustomNewsAPI.Core.APIElements.Collection;
import CustomNewsAPI.Core.Mappers.APICollectionMapper;
import CustomNewsAPI.Core.Mappers.Mapper;

public class APICollectionMapperTests {
    
    @Test
    void testAPICollectionMapper_Green() {

        Collection expected = new Collection("ok", 3, ParserTestHelpers.getGreenSetArticles());

        Mapper<Collection> m = new APICollectionMapper();
        Collection actual = m.map(MapperTestHelpers.getGreenAPICollection());

        assertEquals(expected, actual);
    }

    @Test
    void testAPICollectionMapper_Red() {
        Collection expected = new Collection("ok", 3, ParserTestHelpers.getRedSetArticles());

        Mapper<Collection> m = new APICollectionMapper();
        Collection actual = m.map(MapperTestHelpers.getRedAPICollection());

        assertEquals(expected, actual);
    }
}
