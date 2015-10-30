package nineChap8_DSA;

import java.util.*;

public class TrieWordSearch9Chap {

  class TrieNode {
    String s;
    boolean isString;
    HashMap<Character, TrieNode> subtree;

    public TrieNode() {
      isString = false;
      subtree = new HashMap<Character, TrieNode>();
      s = "";
    }
  };


  class TrieTree {
    TrieNode root;

    public TrieTree(TrieNode TrieNode) {
      root = TrieNode;
    }

    public void insert(String s) {
      TrieNode now = root;
      for (int i = 0; i < s.length(); i++) {
        if (!now.subtree.containsKey(s.charAt(i))) {
          now.subtree.put(s.charAt(i), new TrieNode());
        }
        now = now.subtree.get(s.charAt(i));
      }
      now.s = s;
      now.isString = true;
    }

    public boolean find(String s) {
      TrieNode now = root;
      for (int i = 0; i < s.length(); i++) {
        if (!now.subtree.containsKey(s.charAt(i))) {
          return false;
        }
        now = now.subtree.get(s.charAt(i));
      }
      return now.isString;
    }
  };

  // I like this
  public int[] dx = {1, 0, -1, 0};
  public int[] dy = {0, 1, 0, -1};

  // DFS to find all the solution, using TrieTree
  public void search(char[][] board, int x, int y, TrieNode root,
      ArrayList<String> ans, String res) {

    if (root.isString == true) {
      if (!ans.contains(root.s)) {
        ans.add(root.s);
      }
    }
    if (x < 0 || x >= board.length || y < 0 || y >= board[0].length
        || board[x][y] == '#' || root == null)
      return;
    if (root.subtree.containsKey(board[x][y])) {
      for (int i = 0; i < 4; i++) {
        char now = board[x][y];
        board[x][y] = '#';
        search(board, x + dx[i], y + dy[i], root.subtree.get(now), ans, res);
        board[x][y] = now;
      }
    }

  }

  public ArrayList<String> wordSearchII(char[][] board, ArrayList<String> words) {
    ArrayList<String> ans = new ArrayList<String>();

    TrieTree tree = new TrieTree(new TrieNode());
    for (String word : words) {
      tree.insert(word);
    }
    String res = "";
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        search(board, i, j, tree.root, ans, res);
      }
    }
    return ans;
  }

  public void test() {
    // String[] dict = new String[] {"dog", "dad", "dgdg", "can", "again"};
    char[][] Mat =
    // new char[][] { {'d', 'o', 'a', 'f'}, {'a', 'g', 'a', 'i'},
    // {'d', 'c', 'a', 'n'}};
        new char[][] { {'a', 'd'}, {'o', 'b'}};

    String[] dictionary = new String[] {"dbd", "oa"}; // {"dad", "dog", "dgdg", "can", "again"};
    ArrayList<String> dict = new ArrayList<>();
    for (String s : dictionary) {
      dict.add(s);
    }
    ArrayList<String> ans;

    ans = wordSearchII(Mat, dict);
    for (String str : ans) {
      System.out.println(str);
    }
  }

  public static void main(String[] args) {
    TrieWordSearch9Chap tp = new TrieWordSearch9Chap();
    tp.test();
  }

}
