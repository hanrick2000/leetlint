package JiuChap3_DequeStackTrie;

import java.util.HashMap;
import java.util.Map;

/**
 * http://www.lintcode.com/en/problem/add-and-search-word/#
 * Created this class in JiuChap3_DequeStackTrie at 3:24 PM, 11/7/2015.
 */
public class AddSearchWord {

  public static void main(String[] args) {
    AddSearchWord asw = new AddSearchWord();
    asw.verify();
  }

  public void verify() {
    TrieTree trie = new TrieTree();
    String[] words = new String[] { "to", "tea", "ted", "ten", "ind" };
    for (String word : words) {
      trie.addWord(word);
    }

    //System.out.println(trie.search("too"));
    //System.out.println(trie.search("tow"));
    //System.out.println(trie.search("tea"));
    //System.out.println(trie.search("te."));
    //System.out.println(trie.search("in."));

    //trie.addWord("a");
    //System.out.println(trie.search("."));

    trie.addWord("ran");
    trie.addWord("rune");
    trie.addWord("runner");
    trie.addWord("runs");
    trie.addWord("add");
    trie.addWord("adds");
    trie.addWord("adder");
    trie.addWord("addee");
    System.out.println(trie.search("r.n"));
    System.out.println(trie.search("ru.n.e"));
    System.out.println(trie.search("add"));
    System.out.println(trie.search("add."));
    System.out.println(trie.search("adde."));
    System.out.println(trie.search(".an."));
    System.out.println(trie.search("...s"));
    System.out.println(trie.search("....e."));
    System.out.println(trie.search("......."));
    System.out.println(trie.search("..n.r"));
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

  private class TrieTree {
    TrieNode root;

    private TrieTree(TrieNode node) {
      root = node;
    }

    public TrieTree() {
      this(new TrieNode());
    }

    // Adds a word into the data structure.
    public void addWord(String word) {
      // Write your code here
      TrieNode cur = root;
      for (int i = 0; i < word.length(); ++i) {
        char ch = word.charAt(i);
        if (!cur.subtree.containsKey(ch)) {
          cur.subtree.put(ch, new TrieNode());
        }
        cur = cur.subtree.get(ch);
      }
      cur.s = word;
      cur.isString = true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
      return find(word, 0, root);
    }

    /*
    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
      TrieNode cur = root;
      for (int i = 0; i < word.length(); ++i) {
        char ch = word.charAt(i);
        if (ch != '.' && cur.subtree.containsKey(ch)) {
          return false;
        } else if (cur.subtree.containsKey(ch)) {
          cur = cur.subtree.get(ch);
        } else {
          //for (TrieNode next : cur.subtree.values()) {
          //  cur = next;  // need to use recursion to search from this point, or use stack (not sure how)
          //}
        }
      }
    }
    */

    /**
     * My solution, failed in TLE. Never try-and-error! Only code after full algs is designed.
     */
    //public boolean find(String word, int idx, TrieNode root) {
    //  if (idx == word.length()) {
    //    if (root.isString) {
    //      return true;
    //    } else {
    //      return false;
    //    }
    //  }
    //
    //  boolean idxLevel = false;
    //  for (Character key : root.subtree.keySet()) {
    //    char ch = word.charAt(idx);
    //    if (ch == '.') {
    //      for (TrieNode nd : root.subtree.values()) {
    //        idxLevel |= find(word, idx + 1, nd);
    //      }
    //    } else {
    //      if (key != ch) {
    //        //return false;
    //        continue;
    //      } else {
    //        //System.out.println("true at: " + ch + " index: " + idx);
    //        idxLevel |= find(word, idx + 1, root.subtree.get(ch));
    //      }
    //    }
    //  }
    //  return idxLevel;
    //}

    /**
     * 9chap's solution, actually, as http://www.meetqun.com/thread-9248-1-1.html said, it is a simple Trie + DFS
     * However, I messed the DFS part, I got the idea, as Beeder's Ski solution, a global var for all branch find if
     * there is a '.'. But I also include normal case in it, so messed up.
     * @param word
     * @param idx
     * @param root
     * @return
     */
    public boolean find(String word, int idx, TrieNode root) {
      if (idx == word.length()) {
        return root.isString;
      }

      char c = word.charAt(idx);
      if (root.subtree.containsKey(c)) {
        if (idx == word.length()-1 && root.subtree.get(c).isString) {
          return true;
        }
        return find(word, idx+1, root.subtree.get(c));
      } else if (c == '.') {
        //boolean rs = false;
        for (TrieNode nd : root.subtree.values()) {
          if (idx == word.length()-1 && nd.isString) {
            return true;
          }
          //rs |= find(word, idx+1, nd);  // if true, directly return it! Since it is not a largestSea problem.
          if (find(word, idx+1, nd)) {
            return true;
          }
        }
        return false;
      }
      else {
        return false;
      }
    }

    public boolean find(String word) {
      // Write your code here
      TrieNode cur = root;
      for (int i = 0; i < word.length(); ++i) {
        char ch = word.charAt(i);
        if (!cur.subtree.containsKey(ch)) {
          return false;
        }
        cur = cur.subtree.get(ch);
      }
      return cur.isString;
    }
  }
}
