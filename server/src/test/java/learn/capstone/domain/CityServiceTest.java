package learn.capstone.domain;

import learn.capstone.data.CityFileRepository;
import learn.capstone.models.City;
import learn.capstone.models.Scenery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CityServiceTest {

    @Autowired
    CityService cityService;

    @MockBean
    CityFileRepository repository;

    @Test
    void shouldFindById() {
        City expected = new City (1, "United State", Scenery.METROPOLITAN, "Washington DC");
        when(repository.findByName("Washington DC")).thenReturn(expected);
        Result<City> actual = cityService.findByName("Washington DC");
        assertEquals(expected.getCityName(), actual.getPayload().getCityName());
    }

    @Test
    void shouldNotFindByInvalidCityName() {
        Result<City> actual = cityService.findByName(null);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldNotFindByNoneExistingName() {
        when(repository.findByName("Fake City")).thenReturn(null);
        Result<City> actual = cityService.findByName("Fake City");
        assertEquals(ResultType.NOT_FOUND, actual.getType());
    }

}