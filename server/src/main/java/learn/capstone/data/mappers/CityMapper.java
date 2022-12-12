package learn.capstone.data.mappers;

import learn.capstone.models.City;
import learn.capstone.models.Scenery;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityMapper implements RowMapper<City> {
    @Override
    public City mapRow(ResultSet rs, int rowNum) throws SQLException {
        City city = new City();
        city.setCityId(rs.getInt("city_id"));
        city.setCityName(rs.getString("city_name"));
        city.setCountryName(rs.getString("country_name"));

        Scenery scenery = Scenery.valueOf(rs.getString("scenery_name"));
        city.setScenery(scenery);

        return city;
    }
}