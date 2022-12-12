package learn.capstone.models;

import java.util.Objects;

public class Entertainment {

  private int entertainmentId;
  private String entertainmentName;
  private ActivityLevel activityLevel;
  private PriceRange priceRange;
  private boolean kidFriendly;

  public Entertainment(){

  }
  public Entertainment(int entertainmentId, String entertainmentName, ActivityLevel activityLevel, PriceRange priceRange, boolean kidFriendly) {
    this.entertainmentId = entertainmentId;
    this.entertainmentName = entertainmentName;
    this.activityLevel = activityLevel;
    this.priceRange = priceRange;
    this.kidFriendly = kidFriendly;
  }

  public int getEntertainmentId() {
    return entertainmentId;
  }

  public void setEntertainmentId(int entertainmentId) {
    this.entertainmentId = entertainmentId;
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
    Entertainment that = (Entertainment) o;
    return entertainmentId == that.entertainmentId && entertainmentName.equals(that.entertainmentName) && activityLevel == that.activityLevel && priceRange == that.priceRange && kidFriendly == that.kidFriendly;
  }

  @Override
  public int hashCode() {
    return Objects.hash(entertainmentId, entertainmentName, activityLevel, priceRange, kidFriendly);
  }

  @Override
  public String toString () {
    return String.format("Entertainment Id: %s Entertainment Name: %s Activity Level: %s Price Range: %s Kid Friendly: %s", this.entertainmentId, this.entertainmentName, this.activityLevel, this.priceRange, this.kidFriendly);
  }
}

