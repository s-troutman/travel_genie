package learn.capstone.data.mappers;

import learn.capstone.models.ActivityLevel;
import learn.capstone.models.Entertainment;
import learn.capstone.models.PriceRange;
import learn.capstone.models.Scenery;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EntertainmentMapper implements RowMapper<Entertainment> {

    @Override
    public Entertainment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Entertainment entertainment = new Entertainment();
        entertainment.setEntertainmentId(rs.getInt("entertainment_id"));
        entertainment.setEntertainmentName(rs.getString("entertainment_name"));

        ActivityLevel activityLevel = ActivityLevel.valueOf(rs.getString("activity_level"));
        entertainment.setActivityLevel(activityLevel);

        PriceRange priceRange = PriceRange.valueOf(rs.getString("price_range"));
        entertainment.setPriceRange(priceRange);

        entertainment.setKidFriendly(rs.getBoolean("kid_friendly"));

        return entertainment;
    }
}