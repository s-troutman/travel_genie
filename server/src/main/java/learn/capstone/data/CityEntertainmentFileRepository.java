package learn.capstone.data;

import learn.capstone.models.CityEntertainment;

import java.util.List;

public interface CityEntertainmentFileRepository {
    List<CityEntertainment> findAll();
}
