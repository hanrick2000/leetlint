package freq3_tony;

import java.util.*;

/**
 * 这里用的是N00t的方法
 * @author tzhang
 *
 */
public class SurroundRegion {
  private void mark(char[][] board, int row, int col, Queue<Integer> que) {
    if (board[row][col] != 'O') {
      return;
    }
    board[row][col] = 'N';
    int r = board.length;
    int c = board[0].length;
    que.offer(row * c + col);
  }

  public void markBFS(char[][] board, int row, int col) {
    Queue<Integer> que = new LinkedList<>();
    mark(board, row, col, que);
    int rows = board.length;
    int cols = board[0].length;
    while (!que.isEmpty()) {
      int pair = que.poll();
      int r = pair / cols;
      int c = pair % cols;
      // 这里是优化过的Flood fill, 即忽略四角和边框, 因为他们总是不会flip的. 注意这里的边界!
      if (r + 1 < rows - 1)
        mark(board, r + 1, c, que);
      if (r - 1 > 0)
        mark(board, r - 1, c, que);
      if (c + 1 < cols - 1)
        mark(board, r, c + 1, que);
      if (c - 1 > 0)
        mark(board, r, c - 1, que);
    }
  }

  public void solve(char[][] board) {
    if (board.length < 1 || board[0].length < 1)
      return;
    int rows = board.length;
    int cols = board[0].length;

    // start from 'O' on the edge and mark all NON_FLIP. from 1 to rows-2 ROWS
    for (int i = 1; i < rows - 1; ++i) {
      if (board[i][0] == 'O')
        markBFS(board, i, 1);
      if (board[i][cols - 1] == 'O')
        markBFS(board, i, cols - 2);
    }

    // start from 'O' on the edge and mark All NON_FLIP from 1 to cols-2 COLS
    for (int j = 1; j < cols - 1; ++j) {
      if (board[0][j] == 'O')
        markBFS(board, 1, j);
      if (board[rows - 1][j] == 'O')
        markBFS(board, rows - 2, j);
    }

    // finally flip all flippable
    for (int i = 1; i < rows - 1; ++i) {
      for (int j = 1; j < cols - 1; ++j) {
        if (board[i][j] == 'O') {
          board[i][j] = 'X';
        } else if (board[i][j] == 'N') {
          board[i][j] = 'O';
        }
      }
    }
  }

  public SurroundRegion() {
    // array is not a primitive type but an obj
    char[][] board =
        new char[][] { {'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'},
            {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
    solve(board);
    for (int i = 0; i < board.length; ++i)
      System.out.println(board[i]);
  }

  public static void main(String[] args) {
    SurroundRegion sr = new SurroundRegion();
  }
}
