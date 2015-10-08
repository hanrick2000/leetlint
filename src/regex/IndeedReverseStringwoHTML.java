package regex;

import java.util.*;

/*-
 * http://www.meetqun.com/thread-11247-1-1.html
 * Indeed phone interview, reverse string; but this string contains HTML coded special character, you shouldn't reverse that.
 * eg: 123<h1>This is heading 1</h1>
 *     321<h1>1 gnidaeh si sihT</h1>
 * @author tzhang
 *
 */
public class IndeedReverseStringwoHTML {

  public static void main(String[] args) {
    // String input = "I will display &#8364 and &euro";
    // System.out.println(reverseSentence(input));
    // System.out.println(reverseWord(input));
    // System.out.println(reverseChunk(input));

    /*-
     * input : "123&amp;456&amp;764"
     * output: "467&amp;654&amp;321"
     * "&euro;"和 "&amp;"代表特殊字符，要当成一个整体
     */
    String input2 = "123&amp;456&&&amp;764"; // 123&amp;456&euro;&&764";
    System.out.println(revHTML(input2));
  }

  /**
   * Event this simple, a lot juice to get
   * 
   * @param sentence
   * @return
   */
  private static String reverseSentence(String sentence) {
    int leng = sentence.length();
    char[] res = new char[leng];
    for (int i = 0; i < leng; ++i) {
      res[i] = sentence.charAt(leng - i - 1);
    }

    /*-
     * String output = res.toString(); // common Java mistake:
     * http://stackoverflow.com/questions/7281469/why-is-java-util-observable-not-an-abstract-class/7284322#7284322
     * http://stackoverflow.com/a/7060025/3984911
     */
    String output = new String(res);
    return output;
  }

  private static String reverseWord(String sentence) {
    String[] words = sentence.split(" ");

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < words.length; ++i) {
      sb.append(revWord(words[i]));
      if (i != words.length - 1) {
        sb.append(" ");
      }
    }
    return sb.toString();
  }

  private static String reverseChunk(String sentence) {
    String[] words = sentence.split(" ");
    StringBuilder sb = new StringBuilder();

    int leng = words.length;
    for (int i = 0; i < leng; ++i) {
      // sb.append(revWord(words[i]));
      sb.append(words[leng - i - 1]);
      if (i != leng - 1) {
        sb.append(" ");
      }
    }
    String res = sb.toString();
    return res;
  }

  enum state {
    ON, OFF, START
  };

  private static String revHTML(String sentence) {
    int lhs = 0, rhs = 0;
    int leng = sentence.length();
    List<int[]> HTML = new ArrayList<>();
    char[] sent = sentence.toCharArray();
    state st = state.OFF;
    Set<String> dict = new HashSet<>();
    dict.add("&euro;");
    dict.add("&amp;");
    // phase 1 find all HTML location
    for (int i = 0; i < leng; ++i) {
      if (st == state.OFF && sent[i] == '&') {
        lhs = i;
        rhs = i;
        st = state.START;
      } else if (st == state.START && sent[i] == ';') {
        rhs = i;
        if (dict.contains(sentence.substring(lhs, rhs + 1))) { // this is a valid HTML, by check in
                                                               // dict map
          st = state.ON;
          HTML.add(new int[] {lhs, rhs});
        } else {
          st = state.OFF;
        }
      } else if (st == state.ON) {
        if (sent[i] == '&') {
          lhs = i;
          rhs = i;
          st = state.START;
        }
        st = state.OFF; // ';' or !'&'
      }
    }
    StringBuilder sb = new StringBuilder();
    for (int p = 0; p < leng;) {
      boolean got = false;
      for (int i = 0; got == false && i < HTML.size(); ++i) {
        int[] pair = HTML.get(i);
        if (p == pair[0]) {
          sb.append(sentence.substring(pair[0], pair[1] + 1));
          p = pair[1] + 1;
          got = true;
          break;
        }
      }
      if (got == false) {
        sb.append(sentence.charAt(leng - p - 1));
        p++;
      }
    }
    return sb.toString();
  }


  /******************************************************
   * util
   *****************************************************/


  private static String revWord(String word) {
    char[] charArr = word.toCharArray();
    int leng = charArr.length;
    for (int i = 0; i < leng / 2; ++i) {
      char tmp = charArr[leng - i - 1];
      charArr[leng - i - 1] = charArr[i];
      charArr[i] = tmp;
    }
    String res = new String(charArr);
    // System.out.println(word + "->" + res);
    return res;
  }

}
