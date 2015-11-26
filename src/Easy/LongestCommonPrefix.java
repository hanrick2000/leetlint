package Easy;

/**
 * Created at 2:51 PM on 11/25/15.
 */
public class LongestCommonPrefix {
  public static void main(String[] args) {
    String[] words = new String[] {
        "ABCDEFG", "ABCEFG", "ABCEFA"
    };
    String lcp = new LongestCommonPrefix().longestCommonPrefix(words);
    System.out.println(lcp);
  }

  /**
   * @param strs: A list of strings
   * @return: The longest common prefix
   */
  public String longestCommonPrefix(String[] strs) {
    // write your code here
    StringBuilder sb = new StringBuilder();
    if (strs == null || strs.length == 0) {
      return sb.toString();
    }

    String prefix = strs[0];
    //if (prefix == "")  return "";
    for (int i = 1; i < strs.length; ++i) {
      int j = 0;
      while (j < prefix.length() && j < strs[i].length() && prefix.charAt(j) == strs[i].charAt(j)) {
        j++;
      }
      if (j == 0) {
        return "";
      }
      prefix = prefix.substring(0, j);
    }
    return prefix;
  }
}
