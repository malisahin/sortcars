package main.java.com.sahin.sortcars.util;

import java.io.*;
import java.util.List;

/**
 * @author malisahin
 * @since 24-May-21
 */

public class FileUtil {

  public static void writeToFile(String filePath, List<String> lines) {

    final File fout = new File(filePath);
    final FileOutputStream fos;

    BufferedWriter bw = null;
    try {
      fos = new FileOutputStream(fout);
      bw = new BufferedWriter(new OutputStreamWriter(fos));
      for (String line : lines) {
        bw.write(line);
        bw.newLine();
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    } finally {
      if (bw != null) {
        try {
          bw.close();
        } catch (IOException e) {
          System.out.println(e.getMessage());
        }
      }
    }


  }
}
