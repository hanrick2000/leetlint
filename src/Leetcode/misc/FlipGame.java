package Leetcode.misc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created at 8:59 AM on 11/30/15.
 */
public class FlipGame {
  public static void main(String[] args) {
    String[] testcase = {"+","-","++","--","+++","++++","+--+","-+-+","+-+-"};
    for (String test : testcase) {
      System.out.println(new FlipGame().winResult(test));
    }
  }

  public List<String> winResult(String s) {
    List<String> res = new ArrayList<>();
    if (s.length() < 2)  return res;
    //dfs(s, res, 0);
    res = generatePossibleNextState(s);
    return res;
  }

  /**
   * http://blog.csdn.net/sbitswc/article/details/49225315
   * @param s
   * @param res
   * @param step
   */
  private void dfs(String s, List<String> res, int step) {
    for (int i = step; i < s.length() - 1; ++i) {
      if (s.charAt(i) == '+' && s.charAt(i+1) == '+') {
        String nextS = s.substring(0, i) + "--" + s.substring(i+2);
        //dfs(s, res, step+2);
        res.add(nextS);
      }
      else {
        dfs(s, res, step+1);
      }
    }
  }

  /**
   * http://blog.csdn.net/u012290414/article/details/49271613
   * @param s
   * @return
   */
  public List<String> generatePossibleNextState(String s) {
    List<String> res = new ArrayList<>();
    if (s.length() < 2) {
      return res;
    }
    for (int i = 0; i < s.length()-1; ++i) {
      if (s.charAt(i) == s.charAt(i + 1) && s.charAt(i) == '+') {
        String nexts = s.substring(0,i) + "--" + s.substring(i+2);
        res.add(nexts);
      }
    }
    return res;
  }
}
