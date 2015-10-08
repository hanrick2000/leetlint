package nineChap1;

/**
 * 九章第一课第一题就是这个, 从这里引申出面试的思路和过程
 * 
 * @author tzhang
 *
 */
public class StrStr {

  public static void main(String[] args) {
    String haystack = "source", needle = "target";
//    String haystack = "abcdabcdefg", needle = "bcd";
    System.out.println(strStrBasic(haystack, needle));
  }

  /**
   * 体现基本功的快速简单解法
   * 
   * @param haystack
   * @param needle
   * @return
   */
  public static int strStrBasic(String haystack, String needle) {
    int ret = -1;
    if (haystack == null || needle == null) {
      return ret;
    }
    int hlen = haystack.length(), nlen = needle.length();
    if (hlen < nlen)
      return ret;
    int i, j;
    for (i = 0; i < hlen - nlen + 1; ++i) {
      for (j = 0; j < nlen; ++j) {
        if (haystack.charAt(i + j) != needle.charAt(j))
          break;
      }
      if (j == nlen) {
        ret = i;
        return ret;
      }
    }
    return ret;
  }
}
