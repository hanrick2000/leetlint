package nineChap8_DSA;

import java.util.*;

public class WordSearchIIDFScreek {
  public static void main(String[] args) {
    char[][] Mat =
    // new char[][] { {'d', 'o', 'a', 'f'}, {'a', 'g', 'a', 'i'},
    // {'d', 'c', 'a', 'n'}};
        new char[][] { {'a', 'd'}, {'o', 'b'}};

    String[] dict = new String[] {"dbd", "oa", "dao"}; // {"dad", "dog", "dgdg", "can", "again"};
    List<String> ans;
    ans = wordSearchI(Mat, dict);

    System.out.println("\nResult: " + ans);

  }

  private static List<String> wordSearchI(char[][] mat, String[] words) {
    int m = mat.length;
    int n = mat[0].length;
    List<String> result = new ArrayList<>();

    for (String word : words) {
      for (int i = 0; i < m; ++i) {
        for (int j = 0; j < n; ++j) {
          boolean[][] visited = new boolean[m][n];
          if (dfs(mat, word, i, j, 0, visited)) {
            result.add(word);
          }
        }
      }
    }
    return result;
  }

  public static boolean dfs(char[][] board, String word, int i, int j, int k,
      boolean[][] visited) {
    int m = board.length;
    int n = board[0].length;

    if (i < 0 || j < 0 || i >= m || j >= n) {
      return false;
    }

    if (visited[i][j] == true) {
      return false;
    }

    if (board[i][j] == word.charAt(k)) {
      visited[i][j] = true;
      if (k == word.length() - 1) {
        return true;
      } else if (dfs(board, word, i - 1, j, k + 1, visited)
          || dfs(board, word, i + 1, j, k + 1, visited)
          || dfs(board, word, i, j - 1, k + 1, visited)
          || dfs(board, word, i, j + 1, k + 1, visited)) {
        return true;
      }
    }
    return false;
  }
}
