package testingAns;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class WordLadderJiuZhang {

  /**
   * 
   * @param start
   * @param end
   * @param dict
   * @return
   */
  public int ladderLengthJiuZhang(String start, String end, Set<String> dict) {
    if (dict == null || dict.size() == 0) {
      return 0;
    }

    HashSet<String> hash = new HashSet<String>();
    Queue<String> queue = new LinkedList<String>();
    queue.offer(start);
    hash.add(start);

    int level = 1;
    while (!queue.isEmpty()) {
      level++; // then I come to the next level
      int Qsize = queue.size(); // since queue is changing along the loop, so need temp var: Qsize
      for (int i = 0; i < Qsize; i++) { // to search for the same level
        String word = queue.poll();
        // if (oneDistance(word, end)) { // no need to consider twice oneDistance with new
        // getNextWords()
        // return length+1;
        // }
        for (String nextWord : getNextWords(word, dict, end)) {
          if (hash.contains(nextWord)) {
            continue;
          }
          if (nextWord.equals(end)) {
            return level;
          }
          // if (oneDistance(nextWord, end)) {
          // return length+1;
          // }

          hash.add(nextWord);
          queue.offer(nextWord);
        }
      }
    }
    return 0;
  }

  private boolean oneDistance(String nextWord, String end) {
    if (nextWord.length() != end.length())
      return false;
    for (char c = 'a'; c <= 'z'; ++c) {
      for (int i = 0; i < nextWord.length(); ++i) {
        if (nextWord.charAt(i) == c) {
          continue;
        }
        if (end.equals(replace(nextWord, i, c))) {
          return true;
        }
      }
    }
    return false;
  }

  // replace character of a string at given index to a given character
  // return a new string
  private String replace(String s, int index, char c) {
    char[] chars = s.toCharArray();
    chars[index] = c;
    return new String(chars);
  }

  /*-
   * get connections with given word. for example, given word = 'hot', dict = {'hot', 'hit', 'hog'}. it will return ['hit', 'hog']
   * A small change, I also add end into neighbors, since it then will find and return after call this method
   * @param word
   * @param dict
   * @param end
   * @return
   */
  private ArrayList<String> getNextWords(String word, Set<String> dict,
      String end) {
    ArrayList<String> nextWords = new ArrayList<String>();
    for (char c = 'a'; c <= 'z'; c++) {
      for (int i = 0; i < word.length(); i++) {
        if (c == word.charAt(i)) {
          continue;
        }
        String nextWord = replace(word, i, c);
        if (dict.contains(nextWord)) {
          nextWords.add(nextWord);
        }
        if (nextWord.equals(end)) {
          nextWords.add(nextWord);
        }
      }
    }
    return nextWords;
  }

  /************************************************************************/

  public int ladderLength(String start, String end, Set<String> dict) {
    // write your code here
    if (dict == null || dict.size() == 0) {
      return 0;
    }

    Queue<String> bfsQ = new LinkedList<>();
    Set<String> visited = new HashSet<>();
    bfsQ.offer(start);
    visited.add(start);

    int level = 1;

    while (!bfsQ.isEmpty()) {
      int Qsize = bfsQ.size();
      level++;
      for (int bLevel = 0; bLevel < Qsize; bLevel++) {
        String cur = bfsQ.poll();
        for (int i = 0; i < cur.length(); i++) {
          for (char c = 'a'; c <= 'z'; ++c) {
            String newStr = replace(cur, i, c); // create neighbors nodes to BFS
            if (visited.contains(newStr)) {
              continue; // found reverse traverse, try next word
            }
            if (newStr.equals(end)) { // reached the end node
              return level;
            }
            if (dict.contains(newStr)) {
              visited.add(newStr);
              bfsQ.offer(newStr);
            }
          }
        }
      }
    }

    return level;
  }

  private void test() {
    Set<String> dict = new HashSet<>();
    dict.add("hot");
    dict.add("dot");
    dict.add("dog");
    dict.add("lot");
    dict.add("log");
    String start = "hit";
    String end = "cog";

//    dict.add("a");
//    dict.add("b");
//    dict.add("c");
//    String start = "a", end = "c";

    int ans = ladderLength(start, end, dict);
    System.out.println("Jiu Zhang's Final result: " + ans);
  }

  public static void main(String[] args) {
    WordLadderJiuZhang wljz = new WordLadderJiuZhang();
    wljz.test();
  }
}
