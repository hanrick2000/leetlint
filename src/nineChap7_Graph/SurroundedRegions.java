package nineChap7_Graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * http://www.lintcode.com/en/problem/surrounded-regions/
 * Created at 3:27 PM on 11/22/15.
 */
public class SurroundedRegions {
  public static void main(String[] args) {
    char[][] M = new char[][] {
        {'X', 'X', 'X', 'X'},
        {'X', 'O', 'O', 'X'},
        {'X', 'X', 'O', 'X'},
        {'X', 'O', 'X', 'X'}
    };
    SurroundedRegions sr = new SurroundedRegions();
    sr.surroundedRegionsBFS(M);
    for (int i = 0; i < M.length; ++i) {
      for (int j = 0; j < M[0].length; ++j) {
        System.out.print(M[i][j] + " ");
      }
      System.out.println();
    }
  }
  /**
   * @param board a 2D board containing 'X' and 'O'
   * @return void
   */
  public void surroundedRegionsBFS(char[][] board) {
    if (board == null) {
      return;
    }
    int R = board.length, C = board[0].length;

    for (int r = 0; r < R; ++r) {
      bfs(board, r, 0);
      bfs(board, r, C-1);
    }

    for (int c = 1; c < C-1; ++c) {
      bfs(board, 0, c);
      bfs(board, R-1, c);
    }
    for (int i = 0; i < R; ++i) {
      for (int j = 0; j < C; ++j) {
        switch(board[i][j]) {
          case 'T' :
            board[i][j] = 'O';
            break;
          case 'O' :
            board[i][j] = 'X';
            break;
        }
      }
    }
  }

  public void surroundedRegionsDFS(char[][] board) {
    // Write your code here
    if (board == null) {
      return;
    }
    int R = board.length, C = board[0].length;
    boolean[][] visited = new boolean[R][C];

    for (int r = 0; r < R; ++r) {
      dfs(board, visited, r, 0);
      dfs(board, visited, r, C-1);
    }

    for (int c = 1; c < C-1; ++c) {
      dfs(board, visited, 0, c);
      dfs(board, visited, R-1, c);
    }
    for (int i = 0; i < R; ++i) {
      for (int j = 0; j < C; ++j) {
        switch(board[i][j]) {
          case 'T' :
            board[i][j] = 'O';
            break;
          case 'O' :
            board[i][j] = 'X';
            break;
        }
      }
    }
  }

  int[] dx = new int[]{1,-1,0,0};
  int[] dy = new int[]{0,0,1,-1};
  private void dfs(char[][] board, boolean[][] visited, int x, int y) {
    // return
    if (!isValid(board, x, y)) {
      return;
    }
    if (visited[x][y]) {
      return;
    }
    //
    board[x][y] = board[x][y] == 'O' ? 'T' : 'O';
    visited[x][y] = true;
    for (int i = 0; i < 4; ++i) {
      int nr = x + dx[i];
      int nc = y + dy[i];
      dfs(board, visited, nr, nc);
      //if (isValid(board, nr, nc)) {
      //  board[nr][nc] = 'T';
      //  dfs(board, visited, nr, nc);
      //}
    }
  }

  private void bfs(char[][] board, int x, int y) {
    if (board[x][y] != 'O') {
      return;
    }
    Queue<Node> bfsQ = new LinkedList<>();
    //boolean[][] visited = new boolean[board.length][board[0].length];
    bfsQ.offer(new Node(x,y));

    while (!bfsQ.isEmpty()) {
      int size = bfsQ.size();
      for (int i = 0; i < size; ++i) {
        Node cur = bfsQ.poll();
        //board[x][y] = board[x][y] == 'O' ? 'T' : 'O'; // valid T node
        if (board[cur.r][cur.c] == 'O') {
          board[cur.r][cur.c] = 'T';
        }
        for (int k = 0; k < 4; ++k) {
          int rr = cur.r + dx[k];
          int cc = cur.c + dy[k];
          if (!isValid(board, rr, cc)) {
            continue;
          }
          bfsQ.offer(new Node(rr, cc));
        }
      }
    }
  }

  private boolean isValid(char[][] board, int x, int y) {
    if (x < 0 || x >= board.length || y < 0 || y >= board[0].length ||
        board[x][y] == 'X' || board[x][y] == 'T') {
      return false;
    }
    return true;
  }

  class Node {
    int r, c;
    Node(int x, int y) {
      r = x;
      c = y;
    }
  }
}
