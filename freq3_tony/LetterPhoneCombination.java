package freq3_tony;

import java.util.*;

/**
 * 有好几个做法, 最简单的是DFS, 考虑使用StringBuilder因为要求输出是String. 或者用List<Character>. 这里参考了N00t和Simple&Stupid的做法.
 * http://simpleandstupid.com/2014/10/16/letter-combinations-of-a-phone-number-leetcode-%E8%A7%A3%E9%A2%98%E7%AC%94%E8%AE%B0/
 * 需要注意一下: 7,9对应的string是4位的.
 * @author tzhang
 *
 */
public class LetterPhoneCombination {
  //-------------------------------------------------> 方法1
  /**
   * client for the DFS recursion
   * 
   * @param digits
   * @return
   */
  private List<List<Character>> letterPhoneCombinationTTT(String digits) {
    String[] dic =
        {"FFFF", ",", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    List<List<Character>> result = new ArrayList<List<Character>>();
    combineHelperTTT(digits, dic, 0, new ArrayList<Character>(), result);
    return result;
  }

  /**
   * 结合N00t的combination和SImple&Stupid的做法.
   * 
   * @param digits : 输入
   * @param dict : 字典集合, 这样可以避免使用太多global var. 所以这里作为input
   * @param deep : 关键点: 控制recursion的深度. 
   * @param path : 暂存每一次dfs的path
   * @param res  : 保存所有结果.
   */
  private void combineHelperTTT(String digits, String[] dict, int deep,
      List<Character> path, List<List<Character>> res) {
    if (deep == digits.length()) {
      List<Character> pathCpy = new ArrayList<Character>(path); // 必须要复制一个. 
      res.add(pathCpy);
      return;
    }
    for (int i = 0; i < dict[digits.charAt(deep) - '0'].length(); ++i) {
      path.add(dict[digits.charAt(deep) - '0'].charAt(i));
      combineHelperTTT(digits, dict, deep + 1, path, res);
      path.remove(path.size() - 1);
    }
  }

  //-------------------------------------------------> 方法2
  /**
   * StringBuilder和List<String>的区别是什么? 为什么这里可以不用担心像List那样在后面被修改的情况? 因为俄sb.toString()是返回一个新的String obj.
   * @param digits
   * @return
   */
  private List<String> letterCombinations(String digits) {
    String[] dic = {"FFFF", ",", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    List<String> res = new ArrayList<String>();
    StringBuilder path = new StringBuilder();
    dfs(digits, dic, 0, path, res);
    return res;
  }
  
  private void dfs(String digits, String[] dic, int deep, StringBuilder path, List<String> res) {
    if (deep == digits.length()) {
      res.add(path.toString());
      return;
    }
    for (int i = 0; i < dic[digits.charAt(deep)-'0'].length(); ++i) {
      path.append(dic[digits.charAt(deep)-'0'].charAt(i));
      dfs(digits, dic, deep+1, path, res);
      path.deleteCharAt(path.length()-1);
    }
  }
  

  //-------------------------------------------------> 方法3: N00t的Iteration解法
  

  //-------------------------------------------------> ctor来测试各个方法
  public LetterPhoneCombination() {
//  List<List<Character>> ret = new ArrayList<List<Character>>();
//  ret = letterPhoneCombinationTTT("29");
//  for (List<Character> ele : ret) {
//    System.out.println(ele);
//  }
  System.out.println(letterCombinations("29"));
}
  public static void main(String[] args) {
    LetterPhoneCombination lpc = new LetterPhoneCombination();
    
  }
}
