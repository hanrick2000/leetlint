package Easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * http://www.lintcode.com/en/problem/largest-number/
 * Created at 6:22 PM on 11/17/15.
 */
public class LargestNum {
  public static void main(String[] args) {
    int[] num = new int[]{1, 20, 23, 4, 8};
    LargestNum ln = new LargestNum();
    String ans = ln.largestNumber(num);
    System.out.println(ans);
  }
  /**
   *@param num: A list of non negative integers
   *@return: A string
   */
  public String largestNumber(int[] num) {
    // write your code here
    if (num == null || num.length == 0) {
      return null;
    }
    List<Integer> list = new ArrayList<>();
    for (int i : num) {
      list.add(i);
    }

    Collections.sort(list, new NumComparator());
    StringBuilder sb = new StringBuilder();
    for (int n : list) {
      sb.append(n);
    }

    if (sb.charAt(0) == '0') {
      return "0";
    }
    return sb.toString();
  }

  //public String largestNumber(int[] num) {
  //  // write your code here
  //  if (num == null || num.length == 0) {
  //    return "";
  //  }
  //  List<Integer> list = new ArrayList<>();
  //  for (int n : num) {
  //    list.add(n);
  //  }
  //  Collections.sort(list, new NumComparator());
  //  StringBuilder sb = new StringBuilder();
  //  for (int n : num) {
  //    sb.append(n);
  //  }
  //  if (sb.charAt(0) == '0') {
  //    return "0";
  //  }
  //  return sb.toString();
  //}

  class NumComparator implements Comparator<Integer> {
    @Override public int compare(Integer a, Integer b) {
      return ("" + b + a).compareTo(""+a+b); // so big first
    }
  }
}
