package nineChap7_Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * http://www.lintcode.com/en/problem/letter-combinations-of-a-phone-number/
 * Created at 9:12 AM on 11/18/15.
 */
public class LetterCombPhoneNum {
  public static void main(String[] args) {
    String digits = "26678837";
    LetterCombPhoneNum lcpn = new LetterCombPhoneNum();
    ArrayList<String> result = lcpn.letterCombinations(digits);
    System.out.println(result);
  }

  Digi[] DIGITS = new Digi[]{
      new Digi(0, ""),
      new Digi(1, ""),
      new Digi(2, "abc"),
      new Digi(3, "def"),
      new Digi(4, "ghi"),
      new Digi(5, "jkl"),
      new Digi(6, "mno"),
      new Digi(7, "pqrs"),
      new Digi(8, "tuv"),
      new Digi(9, "wxyz")
  };
  /**
   * @param digits A digital string
   * @return all posible letter combinations
   */
  public ArrayList<String> letterCombinations(String digits) {
    // Write your code here
    ArrayList<String> ans = new ArrayList<>();
    if (digits.equals("")) {
      return ans;
    }

    Map<String, Integer> dict = new HashMap<>();
    dict.put("computer", 1);
    dict.put("jtmwtpa", 1);
    dfs(ans, digits, dict, 0, new StringBuilder());
    return ans;
  }


  public void dfs(ArrayList<String> path, String digits, Map<String, Integer> dict, int step, StringBuilder cur) {
    if (step == digits.length()) {
      //path.add(cur);
      if (dict.containsKey(cur.toString())) {
        path.add(cur.toString());
      }
      return;
    }

    int curDigit = digits.charAt(step) - '0';
    if (curDigit == 0 || curDigit == 1) {
      //
      dfs(path, digits, dict, step+1, cur);
    }
    else {
      String curLetters = DIGITS[curDigit].letters;
      char[] choices = curLetters.toCharArray();
      for (char choice : choices) {
        cur.append(choice);
        dfs(path, digits, dict, step + 1, cur);
        cur.deleteCharAt(cur.length()-1);
      }
    }
  }

  class Digi {
    int digit;
    String letters;
    Digi(int digit, String letters) {
      this.digit = digit;
      this.letters = letters;
    }
  }
}
