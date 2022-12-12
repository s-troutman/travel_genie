package learn.capstone.data;

import learn.capstone.models.City;

import java.util.List;

public interface CityFileRepository {
    City findByName(String cityName);

    List<City> findByScenery(String sceneryName);

    List<City> findAll();
}
