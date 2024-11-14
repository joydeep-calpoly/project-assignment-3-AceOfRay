package CoreTests.MapperTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import CoreTests.ParserTests.ParserTestHelpers;
import CustomNewsAPI.Core.Parsing.APIElements.Collection;
import CustomNewsAPI.Core.Parsing.Mappers.Mapper;
import CustomNewsAPI.Core.Parsing.Mappers.APIMappers.CollectionMapper;

public class APICollectionMapperTests {
    
    /**
     * Tests: That an valid article is mapped to the expected valid article
     */
    @Test
    void testAPICollectionMapper_Green() {

        Collection expected = new Collection("ok", 3, ParserTestHelpers.getGreenSetArticles());

        Mapper<Collection> m = new CollectionMapper();
        Collection actual = m.map(MapperTestHelpers.getGreenAPICollection());

        assertEquals(expected, actual);
    }

    /**
     * Tests: That an invalid article is mapped ot the expected invalid article
     */
    @Test
    void testAPICollectionMapper_Red() {
        Collection expected = new Collection("ok", 3, ParserTestHelpers.getRedSetArticles());

        Mapper<Collection> m = new CollectionMapper();
        Collection actual = m.map(MapperTestHelpers.getRedAPICollection());

        assertEquals(expected, actual);
    }
}
