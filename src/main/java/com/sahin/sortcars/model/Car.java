package main.java.com.sahin.sortcars.model;

import java.util.HashMap;
import java.util.function.Function;

/**
 * @author malisahin
 * @since 24-May-21
 */

public class Car implements Comparable<Car> {

  private int id;
  private long serialNumber;
  private Color color;
  private City city;

  private Car() {
  }

  public static Car of(int id, long serialNumber) {
    final Car car = new Car();
    car.setId(id);
    car.setSerialNumber(serialNumber);
    car.setCity(City.getRandom());
    car.setColor(Color.getRandom());
    return car;
  }

  public static Function<Car, String> orderByGenerated() {
    return car -> car.getId() + ":" + car.getSerialNumber() + ":" + car.getColor().toString() + ":" + car.getCity().toString();
  }

  public static Function<Car, String> orderByCity() {
    return car -> car.getCity().toString() + ":" + car.getColor().toString() + ":" + car.getSerialNumber() + ":" + car.getId();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public long getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(long serialNumber) {
    this.serialNumber = serialNumber;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  @Override
  public int compareTo(Car o) {
    if (this.city.getSortOrder() > o.city.getSortOrder()) {
      return -1;
    } else if (this.city.getSortOrder() < o.city.getSortOrder()) {
      return 1;
    } else if (this.color.getSortOrder() > o.color.getSortOrder()) {
      return -1;
    } else if (this.color.getSortOrder() < o.color.getSortOrder()) {
      return 1;
    } else if (this.serialNumber > o.serialNumber)
      return -1;

    return 1;
  }


  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }
}
