package nineChap7_Graph;

import java.util.ArrayList;
import java.util.List;

public class QueenI {
  /**
   * Get all distinct N-Queen solutions
   * 
   * @param n: The number of queens
   * @return: All distinct solutions
   *          For example, A string '...Q' shows a queen on forth position
   */
  public static ArrayList<ArrayList<String>> solveNQueens(int n) {
    // write your code here
    ArrayList<ArrayList<String>> path = new ArrayList<ArrayList<String>>();
    if (n == 0) {
      return path;
    }
    helper(path, new ArrayList<Integer>(), n);
    return path;
  }

  private static void helper(ArrayList<ArrayList<String>> result,
      List<Integer> list, int n) {
    // add list into result
    if (list.size() == n) {
      ArrayList<String> res = drawList(list, n);
//      System.out.print(res);
      result.add(res);
      // result.add(new ArrayList<String>(list));
      return;
    }

    // DFS and prune
    for (int i = 0; i < n; ++i) {
      if (!list.contains(i)) {
        if (isValid(list, i)) {
          list.add(i);
          helper(result, list, n);
          list.remove(list.size() - 1);
        }
      }
    }
  }

  private static boolean isValid(List<Integer> list, int cur) {
    if (list == null || list.size() == 0) {
      return true;
    }

    // row Not needed

    // col Not needed

    // diagnal
    for (int i = 0; i < list.size(); i++) {
      if (Math.abs(cur - list.get(i)) == Math.abs(list.size() - i)) {
        return false;
      }
    }

    return true;
  }

  private static ArrayList<String> drawList(List<Integer> list, int n) {
    ArrayList<String> result = new ArrayList<>();
    if (list == null || list.size() == 0) {
      return result;
    }
    for (Integer li : list) {
      String board = "";
      for (int pos = 0; pos < n; ++pos) {
        if (pos != li) {
          board += ".";
        } else {
          board += "Q";
        }
      }
//      System.out.println(board);
      result.add(board);
    }

    return result;
  }

  public static void testDrawList() {
    List<Integer> list = new ArrayList<>();
    list.add(2);
    list.add(0);
    list.add(1);
    // list.add(4);
    List<String> res = drawList(list, 3);
    System.out.print(res);
  }
  
  public static void testNQueenI() {
    ArrayList<ArrayList<String>> ans = solveNQueens(1);;
    for (List<String> list : ans) {
      for (String str : list) {
        System.out.println(str);
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    QueenI.testNQueenI();
  }
}
