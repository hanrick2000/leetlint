package Leetcode.misc;

/**
 * https://leetcode.com/discuss/64344/theory-matters-from-backtracking-128ms-to-dp-0ms
 * Created at 9:30 AM on 11/30/15.
 */
public class FlipGameII {
  public static void main(String[] args) {
    String[] testcase = {"+","-","++","--","+++","++++","+--+","-+-+","+-+-"};
    for (String test : testcase) {
      System.out.println(new FlipGameII().canWin(test));
    }
  }

  /**
   * http://buttercola.blogspot.com/2015/10/leetcode-flip-game-ii.html
   * @param s
   * @return
   */
  public boolean canWin(String s) {
    if (s == null || s.length() == 0) {
      return false;
    }

    char[] arr = s.toCharArray();

    return canWinHelper(arr);
  }

  private boolean canWinHelper(char[] arr) {
    int i = 0;

    for (i = 0; i < arr.length - 1; i++) {
      if (arr[i] == '+' && arr[i + 1] == '+') {
        arr[i] = '-';
        arr[i + 1] = '-';

        boolean win = !canWinHelper(arr);

        arr[i] = '+';
        arr[i + 1] = '+';

        if (win) {
          return true;
        }
      }
    }

    return false;
  }
}
