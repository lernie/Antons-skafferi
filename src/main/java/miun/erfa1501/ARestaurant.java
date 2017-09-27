package miun.erfa1501;

public class ARestaurant { private int id;
  private String restaurantName;
  private String cityName;
  
  public ARestaurant() {}
  
  public int getId() { return id; }
  
  public void setId(int id)
  {
    this.id = id;
  }
  
  public String getRestaurantName() {
    return restaurantName;
  }
  
  public void setRestaurantName(String restaurantName) {
    this.restaurantName = restaurantName;
  }
  
  public String getCityName() {
    return cityName;
  }
  
  public void setCityName(String cityName) {
    this.cityName = cityName;
  }
}