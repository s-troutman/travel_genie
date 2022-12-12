package learn.capstone.data;

import learn.capstone.data.mappers.WishMapper;
import learn.capstone.models.Wish;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class WishJdbcTemplateRepository implements WishFileRepository {

    private final JdbcTemplate jdbcTemplate;

    public WishJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Wish findById(int app_userId) {
        final String sql = "select wish_id, app_user_id, city_name, country_name, scenery_name, entertainment_name, activity_level, price_range, kid_friendly " +
                "from wish " +
                "where wish_id = ?";

        Wish wish = jdbcTemplate.query(sql, new WishMapper(), app_userId)
                .stream()
                .findFirst().orElse(null);

        return wish;
    }

    @Override
    public List<Wish> findByAppUserId(int app_userId) {
        final String sql = "select wish_id, app_user_id, city_name, country_name, scenery_name, entertainment_name, activity_level, price_range, kid_friendly " +
                "from wish " +
                "where app_user_id = ?";

        return jdbcTemplate.query(sql, new WishMapper(), app_userId);
    }

    @Override
    public List<Wish> findAll() {
        final String sql = "select wish_id, app_user_id, city_name, country_name, scenery_name, entertainment_name, activity_level, price_range, kid_friendly "
                + "from wish;";

        return jdbcTemplate.query(sql, new WishMapper());
    }

    @Override
    public List<Wish> findAllPotentialWish() {
        final String sql = "select 0 as wish_ID, 0 as app_user_id, c.city_name, s.scenery_name, co.country_name, e.entertainment_name, e.activity_level, e.price_range, e.kid_friendly "
                + "from city c "
                + "inner join country co on co.country_id = c.country_id "
                + "inner join scenery s on s.scenery_id = c.scenery_id "
                + "inner join city_to_entertainment ce on ce.city_id = c.city_id "
                + "inner join entertainment e on e.entertainment_id = ce.entertainment_id;";

        return jdbcTemplate.query(sql, new WishMapper());
    }

    @Override
    public Wish add(Wish wish) {
        final String sql = "insert into wish (app_user_id, city_name, country_name, scenery_name, entertainment_name, activity_level, price_range, kid_friendly) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, wish.getAppUserId());
            statement.setString(2, wish.getCityName());
            statement.setString(3, wish.getCountryName());
            statement.setString(4, wish.getScenery().toString());
            statement.setString(5, wish.getEntertainmentName());
            statement.setString(6, wish.getActivityLevel().toString());
            statement.setString(7, wish.getPriceRange().toString());
            statement.setBoolean(8, wish.isKidFriendly());
            return statement;
        }, keyHolder);

        if (rowsAffected == 0) {
            return null;
        }

        wish.setWishId(keyHolder.getKey().intValue());

        return wish;
    }

    @Override
    public boolean deleteById(int wishId) {
        return jdbcTemplate.update("delete from wish where wish_id = ?;", wishId) > 0;
    }
}