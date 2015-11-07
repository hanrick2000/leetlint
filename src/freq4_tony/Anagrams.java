package freq4_tony;

import java.util.*;

public class Anagrams {

  public static ArrayList<String> anagrams(String[] strs) {
    HashMap<String, ArrayList<String>> hash = new HashMap<>();
    for (String str : strs) {
      String key = generateLabel(str);
      ArrayList<String> res = hash.get(key);
      // 妙, 只有当key在hashmap里面加value才有意义, 否则要创造一个空的arraylist, 并建
      // 立key和空arraylist的对应.
      if (res == null) {
        res = new ArrayList<String>();
        hash.put(key, res);
      }
      // 注意:不是每一个if 都要跟着else. 这里的if相当于一个过滤器, 空value才建立.
      // 而每次都还是要加入str到hashmap.
      res.add(str);
    }
    ArrayList<String> resSet = new ArrayList<>();
    for(ArrayList<String> anagram: hash.values()){
      if (anagram.size()>1)  resSet.addAll(anagram);
//        System.out.print(anagram);
//      System.out.println("");
    }
    return resSet;
  }

  public static String generateLabel(String str) {
    int hash[] = new int[26];
    for (int i = 0; i < str.length(); i++) {
      int index = (int) (str.charAt(i) - 'a');
      hash[index]++;
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 26; i++) {
      if (hash[i] == 0)
        continue;
      char c = (char) ('a' + i);
      sb.append(c);
      sb.append(hash[i]);
    }
    return sb.toString();
  }

  public static void main(String[] args) {
//    System.out.println("I got: " + generateLabel("tonyjing"));
//    System.out.println("I got: " + generateLabel("jinytong"));
    String[] input = {"tea","and","ace","ad","eat","dan"};
    ArrayList<String> valSet = anagrams(input);
    for (String res: valSet)
      System.out.println(res);
  }

}
