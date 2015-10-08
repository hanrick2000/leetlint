package regex;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountVar {

  private static class errorNum implements Comparable<errorNum> {
    int errorId;
    int errorCnt;
    errorNum(int id, int cnt) {
      errorId = id;
      errorCnt = cnt;
    }

    @Override
    public int compareTo(errorNum other) {
      return this.errorId - other.errorId;
    }

  }
  // http://stackoverflow.com/questions/13796451/how-to-extract-a-string-between-two-delimiters
  public static void test() {

//    String mydata = "36 Error some string with 'the data i want' inside";
    String mydata = "830 Info        218 [1]:        Location cited in prior message";
    
    Pattern pattern2 = Pattern.compile("(^.?\\d+\\s+)(Error)"); // Pattern.compile("'(.*?)'");
    Matcher matcher = pattern2.matcher(mydata);

    if (matcher.find()) {
      System.out.println(matcher.group(1));
    }
  }

  public static void readFile(final String path) throws FileNotFoundException,
      IOException {

    Pattern pattern2 = Pattern.compile("'(.*?)'");
    Map<String, Integer> wordCnt;
    // final String path = "src/regex/demo.txt";
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
      String line;
      Matcher flag;
      wordCnt = new HashMap<>();
      while ((line = br.readLine()) != null) {
        flag = pattern2.matcher(line);
        if (flag.find()) {
          String f = flag.group(1);
          if (!(wordCnt.containsKey(f))) {
            wordCnt.put(f, 1);
          } else {
            wordCnt.put(f, wordCnt.get(f) + 1);
          }
        }
      }
    }

    // so the package is the root path
    // http://stackoverflow.com/questions/2885173/how-to-create-a-file-and-write-to-a-file-in-java
    PrintWriter writer = new PrintWriter("outRsult.txt", "UTF-8");

    int size = wordCnt.size();
    System.out.println("Total distinct 553 different warnings: " + size);
    writer.write("Total distinct 553 different warnings: " + size + "\n");
    // Don't use new for loop, it failed in concurrent case
    // for (Map.Entry<String, Integer> entry : wordCnt.entrySet()) {
    // System.out.println(entry.getKey() + ": " + entry.getValue());
    // }
    
    Iterator mapIt = wordCnt.entrySet().iterator();
    int total = 0;
    while (mapIt.hasNext()) {
      Map.Entry pair = (Map.Entry) mapIt.next();
      System.out.println(pair.getKey() + " : " + pair.getValue());
      total += (int) pair.getValue();
      writer.write(pair.getKey() + " : " + pair.getValue() + "\n");
      mapIt.remove(); // avoids a ConcurrentModificationException
                      // http://stackoverflow.com/a/1066603/3984911
    }
    writer.write("Total occurence : " + total);
    writer.close();
  }

  public static void regexCntError(final String path)
      throws FileNotFoundException, IOException {

    Pattern pattern2 = Pattern.compile("(^.?\\d+\\s+)(Error)");
    Map<Integer, Integer> wordCnt;
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
      String line;
      Matcher flag;
      wordCnt = new HashMap<>();
      while ((line = br.readLine()) != null) {
        flag = pattern2.matcher(line);
        if (flag.find()) {
          String f = flag.group(1).trim();
          int fNum = Integer.parseInt(f);
          if (!(wordCnt.containsKey(fNum))) {
            wordCnt.put(fNum, 1);
          } else {
            wordCnt.put(fNum, wordCnt.get(fNum) + 1);
          }
        }
      }
    }

    PrintWriter writer = new PrintWriter("outDiffRsult.txt", "UTF-8");

    int size = wordCnt.size();
    System.out.println("Total distinct error: " + size);
    writer.write("Total distinct warnings: " + size + "\n");

    Iterator mapIt = wordCnt.entrySet().iterator();
    int total = 0;

    List<errorNum> errorList = new ArrayList<>();
    while (mapIt.hasNext()) {
      Map.Entry pair = (Map.Entry) mapIt.next();
      int id = (int) pair.getKey();
      int cnt = (int) pair.getValue();
      errorList.add(new errorNum(id, cnt));
      System.out.println(pair.getKey() + " : " + pair.getValue());
      total += (int) pair.getValue();
      writer.write(pair.getKey() + " : " + pair.getValue() + "\n");
      mapIt.remove();
    }
    Collections.sort(errorList);
    for (errorNum en : errorList) {
      System.out.println("I got " + en.errorId + " + " + en.errorCnt);
      
    }
    writer.write("Total occurence : " + total);
    writer.close();
  }

  public static void main(String[] args) {
    test();

    try {
      final String path = "src/regex/cntErrorDiff.txt";
      CountVar.regexCntError(path);
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
