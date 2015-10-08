package nineChap8_DSA;

import java.util.*;

public class WordSearchIItrieTree {
  /**
   * @param board: A list of lists of character
   * @param words: A list of string
   * @return: A list of string
   */

  class TrieNode {
    String s;
    boolean isString;
    Map<Character, TrieNode> subtree;

    public TrieNode() {
      s = "";
      isString = false;
      subtree = new HashMap<Character, TrieNode>();
    }
  }

  class TrieTree {
    TrieNode root;

    TrieTree(TrieNode node) {
      root = node;
    }

    public void insert(String s) {
      TrieNode cur = root;
      for (int i = 0; i < s.length(); ++i) {
//        if (!cur.subtree.containsKey(s)) {
        if (!cur.subtree.containsKey(s.charAt(i))) {
          cur.subtree.put(s.charAt(i), new TrieNode());
        }
        cur = cur.subtree.get(s.charAt(i));
      }
      cur.s = s;
      cur.isString = true;
    }

    public boolean find(String s) {
      TrieNode probe = root;
      for (int i = 0; i < s.length(); ++i) {
        if (probe.subtree.get(s.charAt(i)) == null) {
//          break;
          return false;
        }
        probe = probe.subtree.get(s.charAt(i));
      }
      return probe.isString;
    }
  }

  int[] dx = new int[] {1, 0, -1, 0};
  int[] dy = new int[] {0, 1, 0, -1};

  // public void search(List<String> result, String str, TrieNode root,
  public void search(List<String> result, TrieNode root, char[][] board, int x,
      int y, boolean[][] visited) {
    // add cur into result
    if (root.isString == true) {
      if (!result.contains(root.s)) {
        result.add(root.s);
      }
    }

    int t = 100;
    // DFS to search all solution
    if (x < 0 || x >= board.length || y < 0 || y >= board[0].length
        || visited[x][y] == true || root == null) { // root.subtree.size() == 0) {
      return;
    }
    if (root.subtree.containsKey(board[x][y])) {
      for (int i = 0; i < 4; ++i) {
        visited[x][y] = true;
        // str += board[x][y];
        root = root.subtree.get(board[x][y]); // ????????????????????!!!!!!!!!!!!!!!!!!!!!
        if (root == null) {
          t = 10;
        }
        search(result, root, board, x + dx[i], y + dy[i], visited);
//        search(result, root.subtree.get(board[x][y]), board, x + dx[i], y + dy[i], visited);
        visited[x][y] = false;
        // str -= board[x][y];
      }
    }
  }

  public ArrayList<String> wordSearchII(char[][] board, ArrayList<String> words) {
    ArrayList<String> ans = new ArrayList<String>();
    TrieTree trie = new TrieTree(new TrieNode());

    for (String word : words) {
      trie.insert(word);
    }

    for (int r = 0; r < board.length; ++r) {
      for (int c = 0; c < board[0].length; ++c) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        search(ans, trie.root, board, r, c, visited);
      }
    }
    return ans;
  }

  public void test() {
    char[][] Mat =
    // new char[][] { {'d', 'o', 'a', 'f'}, {'a', 'g', 'a', 'i'},
    // {'d', 'c', 'a', 'n'}};
        new char[][] { {'a', 'd'}, {'o', 'b'}};

    String[] dictionary = new String[] {"dbo", "oa"}; // {"dad", "dog", "dgdg", "can", "again"};
    ArrayList<String> dict = new ArrayList<>();
    for (String s : dictionary) {
      dict.add(s);
    }
    ArrayList<String> ans;
    ans = wordSearchII(Mat, dict);
    for (String find : ans) {
      System.out.println(find);
    }
  }
  
  public static void main(String[] args) {
    WordSearchIItrieTree w2t = new WordSearchIItrieTree();
    w2t.test();
  }
}
