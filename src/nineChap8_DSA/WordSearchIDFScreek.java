package nineChap8_DSA;


/**
 * Modified based on Programming Creek's solution on Word Search I using DFS
 * http://www.programcreek.com/2014/06/leetcode-word-search-java/
 * 
 * @author tzhang
 *
 */
public class WordSearchIDFScreek {
  public static enum Direction {
    LEFT, RIGHT, UP, DOWN
  };

  public static void main(String[] args) {
    char[][] Mat =
    // new char[][] { {'d', 'o', 'a', 'f'}, {'a', 'g', 'a', 'i'},
    // {'d', 'c', 'a', 'n'}};
        new char[][] { {'a', 'd'}, {'o', 'b'}
        };

    String[] dict = new String[] {"dbd", "oa"}; // {"dad", "dog", "dgdg", "can", "again"};
    boolean ans;
    ans = wordSearchII(Mat, dict[1]);

    System.out.println("\nResult: " + ans);

  }

  private static boolean wordSearchII(char[][] mat, String word) {
    int m = mat.length;
    int n = mat[0].length;
    boolean result = false;
    for (int i = 0; i < m; ++i) {
      for (int j = 0; j < n; ++j) {
        boolean[][] visited = new boolean[m][n];
        if (dfs(mat, word, i, j, 0, visited)) {
          result = true;
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
      if (k == word.length() -1) {
        return true;
      }
      else if ( dfs(board, word, i-1, j, k+1, visited) ||
                 dfs(board, word, i+1, j, k+1, visited) ||
                 dfs(board, word, i, j-1, k+1, visited) ||
                 dfs(board, word, i, j+1, k+1, visited) ) {
        return true;
      }
    }
    return false;
  }
}
