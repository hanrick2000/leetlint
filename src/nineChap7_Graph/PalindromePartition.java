package nineChap7_Graph;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartition {

  public List<List<String>> partition(String s) {
    // write your code here
    List<List<String>> result = new ArrayList<List<String>>();
    if (s == null || s.length() <= 0) {
      return result;
    }

    List<String> list = new ArrayList<>();
    partitionHelper(result, list, s, 0);
    return result;
  }

  private static boolean isPalindrome(String s) {
    if (s == null || s.length() <= 1) {
      return true;
    }

    // lhs <= rhs is same result as lhs < rhs, since when lhs == rhs, they point to the same so
    // always true
    for (int lhs = 0, rhs = s.length() - 1; lhs < rhs; lhs++, rhs--) {
      if (s.charAt(lhs) != s.charAt(rhs)) {
        return false;
      }
    }

    return true;
  }

  /*-
   * classical DFS method with designing the trace tree first so as to know how to code,
   * also, fully understand the recursion flow is a must to put substr and update in
   * the right place.
   * 
   * @param result
   * @param list
   * @param s
   * @param pos : the cut point
   */
  
  private static void partitionHelper(List<List<String>> result,
      List<String> list, String s, int pos) {

    // add qualified list into result
    if (pos == s.length()) {
      result.add(new ArrayList<String>(list));
      // return; // is it necessary?
    }

    // DFS to build up the dfs tree
    String substr = ""; // What a beauty! Need to fully understand the recursion flow to understand
                        // this, the idea comes from the trace tree before coding!

    for (int i = pos; i < s.length(); ++i) {
      substr += s.charAt(i); // what a beauty! Need to fully understand the recursion flow to
                             // understand this
      System.out.println("Substr: " + substr);
      if (isPalindrome(substr)) {
        list.add(substr);
        // partitionHelper(result, list, s, pos+1); // DAMN! why I use pos+1 again???
        partitionHelper(result, list, s, i + 1);
        list.remove(list.size() - 1);
      }
    }
  }

  public PalindromePartition() {
    String s = "abc";
    List<List<String>> ans = partition(s);
    for (List<String> row : ans) {
      for (String palindrome : row) {
        System.out.print(palindrome + " ");
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    PalindromePartition pp = new PalindromePartition();
  }

}
