package learn.capstone.data;

import static org.junit.jupiter.api.Assertions.*;

import learn.capstone.models.CityEntertainment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CityEntertainmentJdbcTemplateRepositoryTest {

    @Autowired
    CityEntertainmentJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<CityEntertainment> cityEnt = repository.findAll();
        assertNotNull(cityEnt);

        assertTrue(cityEnt.size() >= 1);
    }
}