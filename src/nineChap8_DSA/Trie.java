package nineChap8_DSA;

import java.util.*;

/**
 * http://www.lintcode.com/en/problem/implement-trie/
 * Created at 5:32 PM on 11/25/15.
 */
public class Trie {
  private TrieNode root;

  public Trie() {
    root = new TrieNode();
  }

  // Inserts a word into the trie.
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

  // Returns if the word is in the trie.
  public boolean search(String s) {
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

  // Returns if there is any word in the trie
  // that starts with the given prefix.
  public boolean startsWith(String prefix) {
    TrieNode now = root;
    for(int i = 0; i < prefix.length(); i++) {
      Character c = prefix.charAt(i);
      if (!now.subtree.containsKey(c)) {
        return false;
      }
      now = now.subtree.get(c);
    }
    return true;
  }

  class TrieNode {
    // Initialize your data structure here.
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