package learn.capstone.domain;

import learn.capstone.data.WishFileRepository;
import learn.capstone.models.ActivityLevel;
import learn.capstone.models.PriceRange;
import learn.capstone.models.Scenery;
import learn.capstone.models.Wish;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@SpringBootTest
public class WishServiceTest {
    @Autowired
    WishService wishService;

    @MockBean
    WishFileRepository repository;

    @Test
    void shouldFindById() {
        Wish expected = new Wish(6, 3, "Washington DC", "United States", Scenery.METROPOLITAN, "Sight Seeing", ActivityLevel.MEDIUM, PriceRange.$, true);
        when(repository.findById(6)).thenReturn(expected);
        Result<Wish> actual = wishService.findById(6);
        assertEquals(expected.getAppUserId(), actual.getPayload().getAppUserId());
    }

    @Test
    void shouldNotFindByInvalidId() {
        Result<Wish> actual = wishService.findById(0);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldNotFindByNonExistingId() {
        when(repository.findById(100)).thenReturn(null);
        Result<Wish> actual = wishService.findById(100);
        assertEquals(ResultType.NOT_FOUND, actual.getType());
    }

    @Test
    void shouldFindAllMatching(){
        List <Wish> wishList = List.of(new Wish(0, 0, "Las Vegas", "United States", Scenery.DESERT, "Casino", ActivityLevel.LOW, PriceRange.$$$, false),
                                       new Wish(0, 0, "Portland", "United States", Scenery.SNOW, "Ski", ActivityLevel.MEDIUM, PriceRange.$$, true),
                                       new Wish(0, 0, "Santa Monica", "United States", Scenery.BEACH, "Sun Bathing", ActivityLevel.LOW, PriceRange.$, true),
                                       new Wish(0, 0, "Asheville", "United States", Scenery.MOUNTAIN, "Hiking", ActivityLevel.HIGH, PriceRange.$, false),
                                       new Wish(0, 0, "Washington DC", "United States", Scenery.METROPOLITAN, "Museum", ActivityLevel.LOW, PriceRange.$, true),
                                       new Wish(0, 0, "Washington DC", "United States", Scenery.METROPOLITAN, "Sight Seeing", ActivityLevel.MEDIUM, PriceRange.$, true));

        Wish match = new Wish(0, 0, "", "", Scenery.METROPOLITAN, "", ActivityLevel.MEDIUM, PriceRange.$, true);

        when(repository.findAllPotentialWish()).thenReturn(wishList);
        List <Wish> actual = wishService.findAllMatching(match);

        assertEquals(1, actual.size());

    }

    @Test
    void shouldFindNoMatching(){
        List <Wish> wishList = List.of(new Wish(0, 0, "Las Vegas", "United States", Scenery.DESERT, "Casino", ActivityLevel.LOW, PriceRange.$$$, false),
                new Wish(0, 0, "Portland", "United States", Scenery.SNOW, "Ski", ActivityLevel.MEDIUM, PriceRange.$$, true),
                new Wish(0, 0, "Santa Monica", "United States", Scenery.BEACH, "Sun Bathing", ActivityLevel.LOW, PriceRange.$, true),
                new Wish(0, 0, "Asheville", "United States", Scenery.MOUNTAIN, "Hiking", ActivityLevel.HIGH, PriceRange.$, false),
                new Wish(0, 0, "Washington DC", "United States", Scenery.METROPOLITAN, "Museum", ActivityLevel.LOW, PriceRange.$, true),
                new Wish(0, 0, "Washington DC", "United States", Scenery.METROPOLITAN, "Sight Seeing", ActivityLevel.MEDIUM, PriceRange.$, true));

        Wish match = new Wish(0, 0, "", "", Scenery.METROPOLITAN, "", ActivityLevel.MEDIUM, PriceRange.$$, true);

        when(repository.findAllPotentialWish()).thenReturn(wishList);
        List <Wish> actual = wishService.findAllMatching(match);

        assertEquals(0, actual.size());

    }

    @Test
    void shouldAdd() {
        Wish add = new Wish(0, 3, "Santa Monica", "United States", Scenery.BEACH, "Sight Seeing", ActivityLevel.MEDIUM, PriceRange.$$, true);
        Wish expected = new Wish(7, 3, "Santa Monica", "United States", Scenery.BEACH, "Sight Seeing", ActivityLevel.MEDIUM, PriceRange.$$, true);
        when(repository.add(add)).thenReturn(expected);
        Result<Wish> actual = wishService.add(add);
        assertEquals(expected.getAppUserId(), actual.getPayload().getAppUserId());
    }

    @Test
    void shouldNotAddNull() {
        Result<Wish> actual = wishService.add(null);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldNotAddNonZeroWishId() {
        Wish wish = new Wish(7, 3, "Washington DC", "United States", Scenery.METROPOLITAN, "Sight Seeing", ActivityLevel.MEDIUM, PriceRange.$, true);
        Result<Wish> actual = wishService.add(wish);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldNotAddNonExistingAppUserId() {
        Wish wish = new Wish(0, 0, "Washington DC", "United States", Scenery.METROPOLITAN, "Sight Seeing", ActivityLevel.MEDIUM, PriceRange.$, true);
        Result<Wish> actual = wishService.add(wish);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldNotAddNullCityName() {
        Wish wish = new Wish(0, 3, null, "United States", Scenery.METROPOLITAN, "Sight Seeing", ActivityLevel.MEDIUM, PriceRange.$, true);
        Result<Wish> actual = wishService.add(wish);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldNotAddEmptyCityName() {
        Wish wish = new Wish(0, 3, "    ", "United States", Scenery.METROPOLITAN, "Sight Seeing", ActivityLevel.MEDIUM, PriceRange.$, true);
        Result<Wish> actual = wishService.add(wish);
        assertEquals(ResultType.INVALID, actual.getType());
    }


    @Test
    void shouldNotAddNullCountryName() {
        Wish wish = new Wish(0, 3, "Washington DC", null, Scenery.METROPOLITAN, "Sight Seeing", ActivityLevel.MEDIUM, PriceRange.$, true);
        Result<Wish> actual = wishService.add(wish);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldNotAddEmptyCountryName() {
        Wish wish = new Wish(0, 3, "Washington DC", "     ", Scenery.METROPOLITAN, "Sight Seeing", ActivityLevel.MEDIUM, PriceRange.$, true);
        Result<Wish> actual = wishService.add(wish);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldNotAddNullScenery() {
        Wish wish = new Wish(0, 3, "Washington DC", "United States", null, "Sight Seeing", ActivityLevel.MEDIUM, PriceRange.$, true);
        Result<Wish> actual = wishService.add(wish);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldNotAddNullEntertainmentName() {
        Wish wish = new Wish(0, 3, "Washington DC", "United States", Scenery.METROPOLITAN, null, ActivityLevel.MEDIUM, PriceRange.$, true);
        Result<Wish> actual = wishService.add(wish);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldNotAddEmptyEntertainmentName() {
        Wish wish = new Wish(0, 3, "Washington DC", "United States", Scenery.METROPOLITAN, "     ", ActivityLevel.MEDIUM, PriceRange.$, true);
        Result<Wish> actual = wishService.add(wish);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldNotAddNullActivityLevel() {
        Wish wish = new Wish(0, 3, "Washington DC", "United States", Scenery.METROPOLITAN, "Sight Seeing", null, PriceRange.$, true);
        Result<Wish> actual = wishService.add(wish);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldNotAddNullPriceRange() {
        Wish wish = new Wish(0, 3, "Washington DC", "United States", Scenery.METROPOLITAN, "Sight Seeing", ActivityLevel.MEDIUM, null, true);
        Result<Wish> actual = wishService.add(wish);
        assertEquals(ResultType.INVALID, actual.getType());
    }
    @Test
    void shouldNotDeleteByInvalidId() {
        boolean actual = wishService.deleteById(999);
        assertFalse(actual);
    }

    @Test
    void shouldNotDeleteByNonExistingId() {
        when(repository.deleteById(999)).thenReturn(false);
        boolean actual = wishService.deleteById(999);
        assertFalse(actual);
    }
}
