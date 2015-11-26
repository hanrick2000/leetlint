package nineChap8_DSA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created at 2:31 PM on 11/25/15.
 */
public class TopKFrequentWords {
  public static void main(String[] args) {
    String[] words = new String[]{
        //"yes", "lint", "code",
        //"yes", "code", "baby",
        //"you", "baby", "chrome",
        //"safari", "lint", "code",
        //"body", "lint", "code"
        "B", "B" ,
        "C",
        "A", "A",
        "D", "D",
    };
    int k = 3;
    String[] ans = new TopKFrequentWords().topKFrequentWords(words, k);
    for (String word : ans) {
      System.out.println(word);
    }
  }

  class Pair implements Comparable<Pair> {
    String word;
    int count;
    Pair(String w, int c) {
      word = w;
      count = c;
    }

    @Override public int compareTo(Pair other) {
      if (this.count == other.count) {
        return this.word.compareTo(other.word);
      }
      return other.count - this.count;
    }
  }

  /**
   * @param words an array of string
   * @param k an integer
   * @return an array of string
   */
  public String[] topKFrequentWords(String[] words, int k) {
    // Write your code here
    if (words == null || words.length == 0) {
      return null;
    }

    PriorityQueue<Pair> maxPQ = new PriorityQueue<>();
    Map<String, Integer> map = new HashMap<>();
    for (String word : words) {
      if (!map.containsKey(word)) {
        map.put(word, 0);
      }
      map.put(word, map.get(word)+1);
    }

    for (String word : map.keySet()) {
      maxPQ.offer(new Pair(word, map.get(word)));
    }

    List<String> result = new ArrayList<>();
    String[] ans = new String[k];
    for (int i = 0; i < k; ++i) {
      //result.add(maxPQ.poll().word);
      ans[i] = maxPQ.poll().word;
    }
    return ans;
  }
}
