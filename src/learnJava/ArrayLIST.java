package learnJava;
import java.util.*;
public class ArrayLIST {
  public static void main(String[] args) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    System.out.println(res.size() + " " + res);
    List<Integer> ele = new ArrayList<Integer>();
    System.out.println(ele.size());
    res.add(ele);
    System.out.println(res.size() + " " + res);
  }
}
