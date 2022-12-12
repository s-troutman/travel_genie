package learn.capstone.data.mappers;

import learn.capstone.models.ActivityLevel;
import learn.capstone.models.PriceRange;
import learn.capstone.models.Scenery;
import learn.capstone.models.Wish;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WishMapper implements RowMapper<Wish> {
    @Override
    public Wish mapRow(ResultSet rs, int rowNum) throws SQLException {
        Wish wish = new Wish();
        wish.setWishId(rs.getInt("wish_id"));
        wish.setAppUserId(rs.getInt("app_user_id"));
        wish.setCityName(rs.getString("city_name"));
        wish.setCountryName(rs.getString("country_name"));

        Scenery scenery = Scenery.valueOf(rs.getString("scenery_name"));
        wish.setScenery(scenery);

        wish.setEntertainmentName(rs.getString("entertainment_name"));

        ActivityLevel activityLevel = ActivityLevel.valueOf(rs.getString("activity_level"));
        wish.setActivityLevel(activityLevel);

        PriceRange priceRange = PriceRange.valueOf(rs.getString("price_range"));
        wish.setPriceRange(priceRange);

        wish.setKidFriendly(rs.getBoolean("kid_friendly"));

        return wish;
    }
}
