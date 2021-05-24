package main.java.com.sahin.sortcars.model;

/**
 * @author malisahin
 * @since 24-May-21
 */

public enum Color {
  RED(5),
  BLUE(4),
  BLACK(3),
  WHITE(2),
  GREEN(1);

  private final int sortOrder;

  Color(int sortOrder) {
    this.sortOrder = sortOrder;
  }

  public static Color getRandom() {
    final Color[] colorList = Color.values();
    int index = Constants.RANDOM.nextInt(colorList.length);
    return colorList[index];
  }

  public int getSortOrder() {
    return sortOrder;
  }
}
