package freq1_tony;

import java.util.*;

/**
 * For example, given: s: "barfoothefoobarman" words: ["foo", "bar"], return [0,9]
 * 
 * Ganker的代码写得很好, 也体现出了代码逻辑思维能力的功底, 特别是while{if{if}}这里, 一开始确实是搞晕了, debug了半天似懂非懂. N00t这里也写得很好,
 * 而且清晰易懂一些.
 * 
 * @author tzhang
 *
 */
public class ConcatenationSubstr {

  /**
   * Tony
   * 
   * @param s
   * @param diction
   * @return
   */
  public List<Integer> findSubstr(String s, String[] diction) {
    List<Integer> result = new ArrayList<>();
    if (s == null || s.length() == 0 || diction.length == 0)
      return result;
    // 先建立词典. 注意词典有可能包含重复的单词.
    Map<String, Integer> dict = new HashMap<>();
    for (String e : diction) {
      if (dict.containsKey(e)) {
        dict.put(e, dict.get(e) + 1);
      } else {
        dict.put(e, 1);
      }
    }

    // 开始正确的遍历所有可能组成单词的substring, 并使用双指针, 维护curMap.
    int wordlen = diction[0].length();
    for (int i = 0; i < wordlen; ++i) {
      Map<String, Integer> curMap = new HashMap<>();
      int cnt = 0;
      int left = i;
      for (int j = i; j <= s.length() - wordlen; j += wordlen) {
        String str = s.substring(j, j + wordlen);

        if (dict.containsKey(str)) { // 如果当前这个单词属于词典
          if (curMap.containsKey(str)) {
            curMap.put(str, curMap.get(str) + 1);
          } else {
            curMap.put(str, 1);
          }
          if (curMap.get(str) <= dict.get(str)) {
            cnt++;
          } else { // 这里的设计很好
            while (curMap.get(str) > dict.get(str)) { // 为什么用while? ans: 例如{foo=2, bar=1}, s =
                                                      // "foobarbarfoofoo"
              String tmp = s.substring(left, left + wordlen);
              if (curMap.containsKey(tmp)) {
                curMap.put(tmp, curMap.get(tmp) - 1);
                if (curMap.get(tmp) < dict.get(tmp)) { // 一开始这里没有想明白, tmp, left, cnt很容易搞晕.
                  cnt--;
                }
              }
              left += wordlen; // left指针应该指向Concatenation substr的起点. 所以要细腻的操作.
            }
          }
          if (cnt == wordlen) {
            result.add(left);
            String tmp = s.substring(left, left + wordlen);
            if (curMap.containsKey(tmp)) {
              curMap.put(tmp, curMap.get(tmp) - 1);
            }
            cnt--;
            left += wordlen;
          }
        }

        else { // 如果当前这个单词不属于词典
          curMap.clear();
          cnt = 0;
          left += wordlen;
        }
      }
    }

    return result;
  }

  // --------------------------------------------------------> N00t的做法, 还是搞不懂...
  private void addWord(String w, HashMap<String, Integer> words) {
    if (words.containsKey(w)) {
      words.put(w, words.get(w) + 1);
    } else {
      words.put(w, 1);
    }
  }

  private void removeWord(String w, HashMap<String, Integer> words) {
    if (!words.containsKey(w))
      return;
    if (words.get(w) > 1) {
      words.put(w, words.get(w) - 1);
    } else {
      words.remove(w);
    }
  }

  private int slideWindow(String S, int begin, int wordLen,
      HashMap<String, Integer> words) {
    String old = S.substring(begin, begin + wordLen);
    addWord(old, words);
    return begin + wordLen;
  }

  public ArrayList<Integer> findSubstring(String S, String[] L) {
    ArrayList<Integer> indices = new ArrayList<Integer>();
    if (L.length == 0)
      return indices;

    int total = L.length, wordLen = L[0].length();

    // store the words and frequencies in a hash table
    HashMap<String, Integer> expectWords = new HashMap<String, Integer>();
    for (String w : L) {
      addWord(w, expectWords);
    }

    // find concatenations
    for (int i = 0; i < wordLen; ++i) {
      // check if there are any concatenations
      int count = 0;
      HashMap<String, Integer> collectWords =
          new HashMap<String, Integer>(expectWords);
      for (int j = i, begin = i; j <= S.length() - (total - count) * wordLen
          && begin <= S.length() - total * wordLen;) {
        String sub = S.substring(j, j + wordLen);
        if (!expectWords.containsKey(sub)) { // if not an expect word, reset
          begin = j + wordLen;
          j = begin;
          count = 0;
          collectWords.putAll(expectWords);
        } else if (!collectWords.containsKey(sub)) { // if duplicate, forward begin by 1
          begin = slideWindow(S, begin, wordLen, collectWords);
        } else {
          removeWord(sub, collectWords);
          j += wordLen;
          ++count;
          if (collectWords.isEmpty()) {
            indices.add(begin);
            begin = slideWindow(S, begin, wordLen, collectWords);
            --count;
          }
        }
      }
    }

    return indices;
  }

  public static void main(String[] args) {
    String txt = "foobarbarfoofoofoobar";
    String[] dict = new String[] {"foo", "foo", "bar"};
    List<Integer> res = (new ConcatenationSubstr().findSubstring(txt, dict));
    System.out.println(res);
  }
}
