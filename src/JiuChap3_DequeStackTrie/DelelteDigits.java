package JiuChap3_DequeStackTrie;

import java.util.Arrays;
import java.util.Stack;

/**
 * http://www.lintcode.com/en/problem/delete-digits/
 * Created at 12:54 PM on 11/25/15.
 */
public class DelelteDigits {
  public static void main(String[] args) {
    String A = "10009876091"; //"134523"; //"30517";
    int k = 4;
    DelelteDigits dd = new DelelteDigits();
    String ans = dd.DeleteDigits(A, k);
    System.out.println(ans);
  }

  /**
   * @param A: A positive integer which has N digits, A is a string.
   * @param k: Remove k digits.
   * @return: A string
   */
  public String DeleteDigits(String A, int k) {
    // write your code here
    String ans = MethodDP(A, k);
    return ans;
  }

  /**
   * wankunde's DP solution
   * http://blog.csdn.net/wankunde/article/details/43792369
   * @param A
   * @param k
   * @return
   */
  public String MethodDP(String A, int k) {
    String dp[][] = new String[A.length() - k][A.length()];
    for (String[] dp1 : dp)
      Arrays.fill(dp1, "");

    for (int i = 0; i < A.length() - k; i++) {
      for (int j = i; j < A.length(); j++) {
        if (i == 0) {
          String ss = A.substring(j, j + 1);
          if (j == 0 || (j > 0 && ss.compareTo(dp[i][j - 1]) < 0))
            // if (j == 0 || (j > 0 && !ss.equals("0") && ss.compareTo(dp[i][j - 1]) < 0))
            dp[i][j] = ss;
          else
            dp[i][j] = dp[i][j - 1];
        } else {
          String x1 = dp[i - 1][j - 1] + A.substring(j, j + 1);
          if (i == j || (j > i && dp[i][j - 1].compareTo(x1) > 0))
            dp[i][j] = x1;
          else
            dp[i][j] = dp[i][j - 1];

        }
      }
    }

    String res = dp[A.length() - k - 1][A.length() - 1];
    while (res.startsWith("0")) {
      res = res.substring(1);
    }
    return res;
  }

  /**
   * Using stack to solve nearest smallest number type of problem
   * @param A
   * @param k
   * @return
   */
  public String MethodOn_k(String A, int k) {
    if (A == null || A.length() == 0 || k >= A.length()) {
      return "";
    }
    StringBuilder sb = new StringBuilder(A);
    Stack<Character> stk = new Stack<>();
    int count = 0;
    for (int i = 0; i <= sb.length(); ++i) {
      //if (i == sb.length()) {
      //  Character other = '0';
      //}
      //Character other = sb.charAt(i);
      //while (sb.charAt(sb.length()-1) )
      Character right = i == sb.length() ? '0' : sb.charAt(i);
      while (!stk.isEmpty() && count != k) {
        if (stk.peek() <= right) {
          break;
        }
        else {
          Character cur = stk.pop();
          count++;
        }
      }
      stk.push(right);
    }
    StringBuilder ans = new StringBuilder();
    for (Character ch : stk) {
      //ans.insert(0, ch);
      ans.append(ch);
    }
    while (ans.charAt(0) == '0')  {
      ans.deleteCharAt(0);
    }
    return ans.deleteCharAt(ans.length()-1).toString();
  }

  /**
   * O(NK)
   * http://www.jiuzhang.com/solutions/delete-digits/ https://dev4future.wordpress.com/2015/06/23/delete-digits-lintcode/
   */
  public String MethodOnk(String A, int k) {
    if (A == null || A.length() == 0 || k >= A.length()) {
      return "";
    }
    StringBuilder sb = new StringBuilder(A);
    while (k != 0) {
      for (int i = 0; i < sb.length(); ++i) {
        if (i == sb.length() - 1 || sb.charAt(i) > sb.charAt(i + 1)) {
          sb.deleteCharAt(i);
          k--;
          //continue;
          break;  // need to re-loop again to find violation
        }
      }
    }

    int j = 0;
    while (j < sb.length() - 1 && sb.charAt(j) == '0') {
      j++;
    }
    sb.delete(0, j);
    return sb.toString();
  }

  /**
   * http://stackoverflow.com/questions/13386107/java-how-to-remove-single-character-from-a-string
   */
  private String remove(String A, int pos) {
    //String ans = A.substring(0, pos) + A.substring(pos+1, A.length());
    StringBuilder sb = new StringBuilder(A);
    String ans = sb.deleteCharAt(pos).toString();
    return ans;
  }
}
