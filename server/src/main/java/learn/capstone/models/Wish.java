package learn.capstone.models;

import java.util.Objects;

public class Wish {
    private int wishId;
    private int appUserId;
    private String cityName;
    private String countryName;
    private Scenery scenery;
    private String entertainmentName;
    private ActivityLevel activityLevel;
    private PriceRange priceRange;
    private boolean kidFriendly;

    public Wish(int wishId, int appUserId, String cityName, String countryName, Scenery scenery, String entertainmentName, ActivityLevel activityLevel, PriceRange priceRange, boolean kidFriendly) {
        this.wishId = wishId;
        this.appUserId = appUserId;
        this.cityName = cityName;
        this.countryName = countryName;
        this.scenery = scenery;
        this.entertainmentName = entertainmentName;
        this.activityLevel = activityLevel;
        this.priceRange = priceRange;
        this.kidFriendly = kidFriendly;
    }

    public Wish() {
    }


    public int getWishId() {
        return wishId;
    }

    public void setWishId(int wishId) {
        this.wishId = wishId;
    }

    public int getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Scenery getScenery() {
        return scenery;
    }

    public void setScenery(Scenery scenery) {
        this.scenery = scenery;
    }

    public String getEntertainmentName() {
        return entertainmentName;
    }

    public void setEntertainmentName(String entertainmentName) {
        this.entertainmentName = entertainmentName;
    }

    public ActivityLevel getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(ActivityLevel activityLevel) {
        this.activityLevel = activityLevel;
    }

    public PriceRange getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(PriceRange priceRange) {
        this.priceRange = priceRange;
    }

    public boolean isKidFriendly() {
        return kidFriendly;
    }

    public void setKidFriendly(boolean kidFriendly) {
        this.kidFriendly = kidFriendly;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wish that = (Wish) o;
        return scenery == that.scenery && activityLevel == that.activityLevel && priceRange == that.priceRange && kidFriendly == that.kidFriendly;
    }

    @Override
    public int hashCode() {
        return Objects.hash(wishId, appUserId, cityName, countryName, scenery, entertainmentName, activityLevel, priceRange, kidFriendly);
    }
}