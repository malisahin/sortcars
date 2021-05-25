package main.java.com.sahin.sortcars;

import main.java.com.sahin.sortcars.concurrency.ThreadPool;
import main.java.com.sahin.sortcars.model.Car;
import main.java.com.sahin.sortcars.sort.QuickSort;
import main.java.com.sahin.sortcars.util.FileUtil;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author malisahin
 * @since 24-May-21
 */

public class Application {

  public static final int CAR_CAPACITY = 10000;  //100000; // 80000 // 50000;
  public static final long TWELVE_DIGIT = 100000000000L;
  public static final int MAX_THREAD = Runtime.getRuntime().availableProcessors(); //200;

  public static void main(String[] args) {

    final Car[] cars = generateCarArray();

    writeToFile(cars, Car.orderByGenerated(), "cars.txt");

    quickSort(cars);

    writeToFile(cars, Car.orderByCity(), "cars_sorted.txt");
  }

  private static void quickSort(Car[] carArray) {
    long start = System.currentTimeMillis();
    final ThreadPool threadPool = new ThreadPool(MAX_THREAD);

    threadPool.submitTask(new QuickSort<>(carArray, 0, carArray.length - 1, threadPool));

    threadPool.waitUntilAllTasksFinished();

    System.out.printf("Execution Time is  : %d as ms \n", System.currentTimeMillis() - start);

    threadPool.stop();

  }

  public static Car[] generateCarArray() {
    final Set<Long> serialNumberSet = generateSerialNumbers();
    final AtomicInteger idGenerator = new AtomicInteger(1);
    return serialNumberSet.stream()
        .map(serialNumber -> Car.of(idGenerator.getAndIncrement(), serialNumber))
        .collect(Collectors.toList())
        .toArray(new Car[CAR_CAPACITY]);
  }

  public static void writeToFile(Car[] cars, Function<Car, String> toStringGenerator, String filePath) {
    final List<String> lines = Arrays.stream(cars)
        .map(toStringGenerator)
        .collect(Collectors.toList());

    FileUtil.writeToFile(filePath, lines);
  }

  private static Set<Long> generateSerialNumbers() {
    final HashSet<Long> set = new HashSet<>(CAR_CAPACITY);

    for (; ; ) {
      long serialNumber = (long) (Math.floor(Math.random() * 9 * TWELVE_DIGIT) + TWELVE_DIGIT);

      if (!set.contains(serialNumber)) {
        set.add(serialNumber);

        if (set.size() == CAR_CAPACITY) {
          return set.stream()
              .sorted()
              .collect(Collectors.toCollection(LinkedHashSet::new));
        }
      }
    }


  }


}
