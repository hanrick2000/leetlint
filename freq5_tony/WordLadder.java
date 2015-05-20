package freq5_tony;

import java.util.*;

public class WordLadder {
  public int ladderLength(String start, String end, HashSet<String> dict) {
    LinkedList<String> wordQueue = new LinkedList<>();
    LinkedList<Integer> distanceQueue = new LinkedList<>();

    wordQueue.add(start);
    // wordQueue.push(start);
    distanceQueue.add(1);
    

    while (!wordQueue.isEmpty()) {
      String currWord = wordQueue.pop();   // use this linkedlist as a queue.
      Integer currDistance = distanceQueue.pop();        //Why pop dist???  --- note: return and REMOVE it!

      if (currWord.equals(end)) {
        return currDistance;
      }

      for (int i = 0; i < currWord.length(); i++) {
        char[] temp = currWord.toCharArray();
        for (char c = 'a'; c < 'z'; c++) {
          temp[i] = c;

          String newWord = new String(temp);
          if (dict.contains(newWord)) {
            wordQueue.add(newWord);
            distanceQueue.add(currDistance + 1);   //why add dist in for loop?
            dict.remove(newWord);
          }
        }
      }
    }
    return 0;
  }

  public static void main(String[] args) {
    //http://stackoverflow.com/questions/2041778/how-to-initialize-hashset-values-by-construction
    HashSet<String> dictionary = new HashSet<String>() {{
//        add("lest");
//        add("leet");
//        add("lose");
//        add("code");
//        add("lode");
//        add("robe");
//        add("lost");
//        add("tose");
//        add("less");
        add("ab");
        add("aa");
        add("zb");
        add("xb");
        add("pb");
      }};
    
//    dictionary = { "lest", "leet", "lose", "code", "lode", "robe", "lost" };
    System.out.println(new WordLadder().ladderLength("ab", "aa", dictionary));
  }
}
