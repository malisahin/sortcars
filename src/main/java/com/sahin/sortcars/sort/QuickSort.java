package main.java.com.sahin.sortcars.sort;

import main.java.com.sahin.sortcars.concurrency.ThreadPool;

/**
 * @author malisahin
 * @since 24-May-21
 */

public class QuickSort<T extends Comparable<T>> implements Runnable {

  final T[] unSortedArray;
  final int start;
  final int end;
  final ThreadPool threadPool;

  public QuickSort(T[] unSortedArray, int start, int end, ThreadPool threadPool) {
    this.unSortedArray = unSortedArray;
    this.start = start;
    this.end = end;
    this.threadPool = threadPool;
  }

  void swap(T[] arr, int i, int j) {
    final T temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }


  @Override
  public void run() {
    sort(unSortedArray, start, end);
  }

  public void sort(T[] array, int start, int end) {
    int length = end - start + 1;

    if (length <= 1) {
      return;
    }

    int pivot = partition(array, start, end);

    threadPool.submitTask(new QuickSort<>(array, start, pivot - 1, threadPool));
    threadPool.submitTask(new QuickSort<>(array, pivot + 1, end, threadPool));

  }

  private int partition(T[] arr, int start, int end) {
    T pivot = arr[end];

    int i = (start - 1);

    for (int j = start; j <= end - 1; j++) {

      if (arr[j].compareTo(pivot) < 0) {
        i++;
        swap(arr, i, j);
      }
    }
    swap(arr, i + 1, end);
    return (i + 1);
  }
}
