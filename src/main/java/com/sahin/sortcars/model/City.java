package main.java.com.sahin.sortcars.model;

/**
 * @author malisahin
 * @since 24-May-21
 */

public enum City {
  NEW_YORK(0),
  MIAMI(1),
  HOUSTON(2),
  LOS_ANGELES(3);

  private final int sortOrder;

  City(int sortOrder) {
    this.sortOrder = sortOrder;
  }

  public static City getRandom() {
    final City[] cityList = City.values();
    int index = Constants.RANDOM.nextInt(cityList.length );
    return cityList[index];
  }

  public int getSortOrder() {
    return sortOrder;
  }

}
