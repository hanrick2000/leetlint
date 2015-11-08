package misc;

import java.util.HashMap;
import java.util.Map;

/**
 * Review trie implement
 * Created this class in misc at 4:37 PM, 11/7/2015.
 */
public class TrieTree {
  TrieNode root ;

  public TrieTree(TrieNode node) {
    root = node;
  }

  public void insert(String s) {
    TrieNode r = root;
    for (int i = 0; i < s.length(); ++i) {
      char ch = s.charAt(i);
      if (r.subtree.containsKey(ch)) {
        r = r.subtree.get(ch);
      }
      else {
        r.subtree.put(ch, new TrieNode());
        r = r.subtree.get(ch);
      }
    }
    r.s = s;
    r.isString = true;
  }

  public boolean find(String s) {
    TrieNode r = root;
    for (int i = 0; i < s.length(); ++i) {
      char ch = s.charAt(i);
      if (!r.subtree.containsKey(ch)) {
        return false;
      }
      else {
        r = r.subtree.get(ch);
      }
    }
    return r.isString;
  }

  private class TrieNode {
    String s;
    Map<Character, TrieNode> subtree;
    boolean isString;

    public TrieNode() {
      s = "";
      subtree = new HashMap<>();
      isString = false;
    }
  }
}
