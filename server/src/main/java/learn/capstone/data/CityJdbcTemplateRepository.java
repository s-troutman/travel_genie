package learn.capstone.data;

import learn.capstone.data.mappers.CityMapper;
import learn.capstone.models.City;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CityJdbcTemplateRepository implements CityFileRepository {
    private final JdbcTemplate jdbcTemplate;

    public CityJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public City findByName(String cityName) {
        final String sql = "select c.city_id, c.city_name, co.country_name, s.scenery_name "
                + "from city c "
                + "inner join country co on c.country_id = co.country_id "
                + "inner join scenery s on c.scenery_id = s.scenery_id "
                + "where c.city_name = ?";

        return jdbcTemplate.query(sql, new CityMapper(), cityName).stream().findFirst().orElse(null);
    }

    @Override
    public List<City> findByScenery(String sceneryName) {
        final String sql = "select c.city_id, c.city_name, co.country_name, s.scenery_name "
                + "from city c "
                + "inner join country co on c.country_id = co.country_id "
                + "inner join scenery s on c.scenery_id = s.scenery_id "
                + "where s.scenery_name = ?";

        return jdbcTemplate.query(sql, new CityMapper(), sceneryName);
    }

    @Override
    public List<City> findAll() {
        final String sql = "select c.city_id, c.city_name, co.country_name, s.scenery_name "
                + "from city c "
                + "inner join country co on c.country_id = co.country_id "
                + "inner join scenery s on c.scenery_id = s.scenery_id;";

        return jdbcTemplate.query(sql, new CityMapper());
    }
}
