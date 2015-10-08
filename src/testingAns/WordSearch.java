package testingAns;

import java.util.*;

public class WordSearch {
  /**
   * @param board: A list of lists of character
   * @param words: A list of string
   * @return: A list of string
   */
  public ArrayList<String> wordSearchII(char[][] board, ArrayList<String> words) {
    // write your code here
    TrieTree trie = new TrieTree(new TrieNode());
    for (String word : words) {
      trie.insert(word);
    }

    ArrayList<String> ans = new ArrayList<>();

    int R = board.length, C = board[0].length;
    for (int r = 0; r < R; ++r) {
      for (int c = 0; c < C; ++c) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        search(ans, trie.root, board, r, c, visited);
      }
    }
    return ans;
  }

  int[] dx = new int[] {1, 0, -1, 0};
  int[] dy = new int[] {0, 1, 0, -1};

  public void search(ArrayList<String> path, TrieNode root, char[][] board,
      int x, int y, boolean[][] visited) {
    // add ans into path
    if (root.isString == true) {
      if (!path.contains(root.s)) {
        path.add(root.s);
      }
    }

    if (x < 0 || x >= board.length || y < 0 || y >= board[0].length
        || visited[x][y] == true || root == null) {
      return;
    }
    // DFS
    if (root.subtree.containsKey(board[x][y])) {
      for (int i = 0; i < 4; ++i) {
        visited[x][y] = true;
        search(path, root.subtree.get(board[x][y]), board, x + dx[i], y + dy[i],
            visited);
        visited[x][y] = false;
      }
    }
  }

  class TrieNode {
    String s;
    Map<Character, TrieNode> subtree;
    boolean isString;

    TrieNode() {
      s = "";
      subtree = new HashMap<Character, TrieNode>();
      isString = false;
    }
  }

  class TrieTree {
    TrieNode root;

    public TrieTree(TrieNode node) {
      root = node;
    }

    public void insert(String s) {
      TrieNode cur = root;
      for (int i = 0; i < s.length(); ++i) {
        if (!cur.subtree.containsKey(s.charAt(i))) {
          cur.subtree.put(s.charAt(i), new TrieNode());
        }
        cur = cur.subtree.get(s.charAt(i));
      }
      cur.s = s;
      cur.isString = true;
    }

    public boolean find(String s) {
      TrieNode cur = root;
      for (int i = 0; i < s.length(); ++i) {
        if (!cur.subtree.containsKey(s.charAt(i))) {
          return false;
        }
        cur = cur.subtree.get(s.charAt(i));
      }
      return cur.isString;
    }
  }


}
