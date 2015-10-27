package interview;

import java.util.HashMap;
import java.util.Map;

/**
 * http://ying.ninja/?p=984
 * Phone Screen @Uber Oct. 26th, 2015
 * Created this class in interview at 4:21 PM, 10/26/2015.
 */
public class PatternMatchUberYing {
  public static void main(String[] args) {
    PatternMatchUberYing pmuy = new PatternMatchUberYing();
    System.out.println(pmuy.match("", "")); //true
    System.out.println(pmuy.match("a", "a")); //true
    System.out.println(pmuy.match("ab", "abab")); //true
    System.out.println(pmuy.match("abab", "howwhathowwhat")); //true
    System.out.println(pmuy.match("abab", "howwhatwhathow")); //false
    System.out.println(pmuy.match("aaaa", "howwhatwhathow")); //false
    System.out.println(pmuy.match("aaaa", "whatwhatwhatwhat")); //true
  }

  /**
   * pattern: abab
   * input: howwhathowwhat
   * mapping: a => how, b => what
   * so match(abab, howwhathowwhat) -> true
   * match(abab, whathowhowwhat) -> false
   * match(¡°¡±, ¡°¡±) -> true
   * match(¡°a¡±, ¡°a¡±) -> true
   * match(¡°ab¡±, ¡°abab¡±) -> true
   * match(¡°aaaa¡±, ¡°howwhatwhathow¡±) -> false
   * match(¡°aaaa¡±, ¡°whatwhatwhatwhat¡±) -> true
   *
   * Each char in pattern string can match 1-n chars in input string. Same char can only match same string. Different chars can match same strings.
   *
   * @param pattern
   * @param str
   * @return
   */
  public boolean match(String pattern, String str) {
    if (pattern == null || pattern.length() > str.length()) {
      return false;
    }

    Map<Character, String> map = new HashMap<>();
    if (helper(0, pattern, str, map, 0) == true) {
      return true;
    }
    else {
      return false;
    }

//    return true;
  }

  /**
   * I like this problem, it's still using the classical backtracking template, but with yourself design
   * of the API of helper. Also, it is not as simple as knight tour that simple loop, it checks 2 cases:
   * pattChar in map or not, but needs to continue for recursion.
   * The similar part to Knight tour is that: the recursion needs to be done, which means, only 2 cases:
   *    1. there is a solution for this sIdx+val.length().
   *    2. there is no solution after this sIdx+val.length().
   *
   * @param index : the index of pattern char.
   * @param pattern
   * @param str
   * @param map : mapping the char/substring
   * @param sIdx : current ending of checked substring.
   * @return
   */
  public boolean helper(int index, String pattern, String str, Map<Character, String> map, int sIdx) {
    if (index == pattern.length()) {
      if (sIdx == str.length()) {
        return true;
      }
      return false;
    }

    char pchar = pattern.charAt(index);

    // so 0...sIdx has been checked, now check next pattern char and substring.
    if (map.containsKey(pchar)) {
      String val = map.get(pchar);
      if (sIdx + val.length() <= str.length() && str.substring(sIdx, sIdx+val.length()).equals(val)) {
        return helper(index+1, pattern, str, map, sIdx+val.length());
      }
      return false;
    }
    for (int sEnd = sIdx; sEnd < str.length(); ++sEnd) {
      String substr = str.substring(sIdx, sEnd + 1);
      map.put(pchar, substr);
      if (true == helper(index+1, pattern, str, map, sEnd+1)) {
        return true;
      }
      map.remove(pchar);
    }

    return false;
  }
}
