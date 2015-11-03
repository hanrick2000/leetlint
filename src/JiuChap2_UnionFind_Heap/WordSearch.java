package JiuChap2_UnionFind_Heap;

/**
 * http://www.lintcode.com/en/problem/word-search/
 * Created this class in JiuChap2_UnionFind_Heap at 6:50 PM, 11/2/2015.
 */
public class WordSearch {
  public static void main(String[] args) {
    WordSearch ws = new WordSearch();
    char[][] board = new char[][] {
        { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' }
    };

    String[] words = new String[] { "ABCCED", "SEE", "ABCB" };
    for (String w : words) {
      boolean res = ws.exist(board, w);
      System.out.println("Backtracking result: " + res);
    }
  }

  /**
   * @param board: A list of lists of character
   * @param word: A string
   * @return: A boolean
   */
  public boolean exist(char[][] board, String word) {
    // write your code here
    if (board == null || board[0].length == 0) {
      return false;
    }
    int R = board.length, C = board[0].length;

    boolean[][] visited = new boolean[R][C];
    boolean ans = false;

    for (int r = 0; r < R; ++r) {
      for (int c = 0; c < C; ++c) {
        //visited = new boolean[R][C];
        //visited[R][C] = true;
        ans = backtrack(board, visited, r, c, 0, word);
        if (ans == true) {
          return true;
        }
      }
    }

    return false;
  }

  private boolean find(char[][] board, int i, int j, String word, int start){
    if(start == word.length())
      return true;

    if (i < 0 || i>= board.length ||
        j < 0 || j >= board[0].length || board[i][j] != word.charAt(start)){
      return false;
    }

    board[i][j] = '#'; // should remember to mark it
    boolean rst = find(board, i-1, j, word, start+1)
                  || find(board, i, j-1, word, start+1)
                  || find(board, i+1, j, word, start+1)
                  || find(board, i, j+1, word, start+1);
    board[i][j] = word.charAt(start);
    return rst;
  }


  int[] dx = new int[] { 0, 0, -1, 1 };
  int[] dy = new int[] { 1, -1, 0, 0 };

  public boolean backtrack(char[][] board, boolean[][] visited, int r, int c, int step,
      String word) {
    if (step == word.length()) {
      return true; //  at this point, visited has the path.
    }
    if (!isValid(board, visited, r, c, step, word)) {
      return false;
    }
    //if (step == word.length()-1) {
    //  return true;  // at this point, visited has not yet the path.
    //}
    visited[r][c] = true;  // remember to mark result param
    boolean levelrc = false;
    for (int i = 0; i < 4; ++i) {
      int rr = r + dx[i];
      int cc = c + dy[i];
      boolean temp = backtrack(board, visited, rr, cc, step + 1, word);
      //System.out.println(rr + ", " + cc + " : " + temp + " VS " + step);
      levelrc |= temp;
    }
    visited[r][c] = false;  // un-mark result param when return to this level(!!!)
    return levelrc;
  }

  private boolean isValid(char[][] board, boolean[][] visited, int r, int c, int step,
      String word) {
    int R = board.length, C = board[0].length;
    if (r < 0 || r >= R || c < 0 || c >= C || step >= word.length() || visited[r][c] == true ||
        word.charAt(step) != board[r][c]) {
      return false;
    }
    //visited[r][c] = true;
    return true;
  }
}
