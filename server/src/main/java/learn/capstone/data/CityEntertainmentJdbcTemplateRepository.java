package learn.capstone.data;

import learn.capstone.data.mappers.CityEntertainmentMapper;
import learn.capstone.models.CityEntertainment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CityEntertainmentJdbcTemplateRepository implements CityEntertainmentFileRepository {

    private final JdbcTemplate jdbcTemplate;

    public CityEntertainmentJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CityEntertainment> findAll() {
        final String sql = "select city_id, entertainment_id "
                + "from city_to_entertainment;";

        return jdbcTemplate.query(sql, new CityEntertainmentMapper());
    }
}
