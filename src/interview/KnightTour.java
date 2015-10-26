package interview;

import java.util.*;

/**
 * Created this class in interview at 3:01 PM, 10/23/2015.
 */
public class KnightTour {
  static final int ROW = 3;
  static final int COL = 10;
  static final int SIZE = ROW * COL;

  public static void main(String[] args) {
    new KnightTour().knightTour(0, 0);
  }

  int[] dx = new int[]{-1, 1, -2, 2, -2, 2, -1, 1};
  int[] dy = new int[]{2, 2, 1, 1, -1, -1, -2, -2};

  public int knightTour(int row, int col) {
    int[][] board = new int[ROW][COL]; // 2d array of visited steps, -1 means not visited

    for (int i = 0; i < ROW; ++i) {
      for (int j = 0; j < COL; ++j) {
        board[i][j] = -1;  // -1 means not visited
      }
    }

    board[0][0] = 0;

//    boolean result = helperGeek(board, 1, 0, 0);

//    boolean result = helperACMer(board, 1, 0, 0);

    Integer[] data = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7};
    List<Integer> list = Arrays.asList(data);
    boolean result = helperShuffle(board, 1, 0, 0, list);
    if (result == false) {
      System.out.println("NO result");
      return 0;
    }

    System.out.println(Arrays.deepToString(board).replace("], ", "]\n"));

    int[] ans = new int[SIZE];

    int idx = 0;
    for (int[] array : board) {
      for (int i : array) {
        ans[idx++] = i;
      }
    }

    Arrays.sort(ans);
    System.out.println(Arrays.toString(ans));
    return 0;
  }

  /**
   * Learn this geekforgeek implementation! This will set/reset next Node
   * http://www.geeksforgeeks.org/backtracking-set-1-the-knights-tour-problem/
   *
   * @param steps
   * @param step
   * @param row
   * @param col
   * @return
   */
  public boolean helperGeek(int[][] steps, int step, int row, int col) {
    // save temp result
    if (step == SIZE) {
      return true;
    }

    // backtracking with prune
    Integer[] shu = new Integer[]{4, 5, 6, 7, 3, 2, 1, 0}; //{0,1,2,3,4,5,6,7};
    List<Integer> list = Arrays.asList(shu);
//    Collections.shuffle(list);  WHY the shuffle leads the helper gointo forever ???
    for (int s : list) { // recursion for all eight direction
      if (!isValid(steps, row + dx[s], col + dy[s])) {
        continue;
      }
      /**
       * I got confused: the next_x, next_y should have step number: step or step+1, or I should assign
       * current x,y with step or step+1?
       */
      steps[row + dx[s]][col + dy[s]] = step;
      /**
       * found a Knight traverse then return. NOTICE: this is recursion call, so ??? times recursion,
       * I got this recursion call result. Can be earlier if recursion failed.
       */
      if (helperGeek(steps, step + 1, row + dx[s], col + dy[s]) == true) {
        return true;  // !!!
      } else {
        steps[row + dx[s]][col + dy[s]] = -1;
      }
    }

    return false;  // !!!
  }

  /**
   * Tested shuffled random iterating all 8 direction, so each run get different result.
   * @param steps
   * @param step
   * @param row
   * @param col
   * @param list
   * @return
   */
  public boolean helperShuffle(int[][] steps, int step, int row, int col, List<Integer> list) {
    if (step == SIZE) {
      return true;
    }

    Collections.shuffle(list);
    for (int i : list) {
      int next_x = row + dx[i];
      int next_y = col + dy[i];
      if (isValid(steps, next_x, next_y)) {
        steps[next_x][next_y] = step;
        if (true == helperShuffle(steps, step+1, next_x, next_y, list)) {
          return true;
        }
        else {
          steps[next_x][next_y] = -1;
        }
      }
    }

    return false;
  }

  /**
   * ACMer's solution, note this will set/reset current node
   * http://www.acmerblog.com/knights-tour-problem-5738.html
   *
   * @param steps
   * @param step
   * @param row
   * @param col
   * @return
   */
  public boolean helperACMer(int[][] steps, int step, int row, int col) {
    steps[row][col] = step;

    if (step == SIZE) {
      return true;
    }

    for (int i = 0; i < 8; ++i) {
      int next_x = row + dx[i];
      int next_y = col + dy[i];

      if (isValid(steps, next_x, next_y)) {
        if (helperACMer(steps, step + 1, next_x, next_y) == true) {
          return true;
        }
      }
    }

    steps[row][col] = -1;
    return false;
  }

  /**
   * First trial.
   *
   * @param steps
   * @param step
   * @param row
   * @param col
   * @return
   */
  public boolean helperWRONG(int[][] steps, int step, int row, int col) {
    // save temp result
    if (step == SIZE) {
      return true;
    }

    // backtracking with prune
    int[] dxy = new int[]{0, 1, 2, 3, 4, 5, 6, 7};
//    Collections.shuffle(list);  WHY the shuffle leads the helper gointo forever ???
    for (int s : dxy) { // recursion for all eight direction
      if (!isValid(steps, row + dx[s], col + dy[s])) {
        continue;
      }
      /**
       * I got confused: the next_x, next_y should have step number: step or step+1, or I should assign
       * current x,y with step or step+1?
       */
      steps[row + dx[s]][col + dy[s]] = step;
      helperWRONG(steps, step + 1, row + dx[s], col + dy[s]);
      steps[row + dx[s]][col + dy[s]] = -1;
    }

    return false;  // !!!
  }

  private boolean isValid(int[][] steps, int nextR, int nextC) {
    if (nextR < 0 || nextR >= ROW || nextC < 0 || nextC >= COL ||
        steps[nextR][nextC] != -1) {
      return false;
    }
    return true;
  }

  public static void printRow(int[] row) {
    for (int i : row) {
      System.out.print(i);
      System.out.print("\t");
    }
    System.out.println();
  }
}


class RandomPermuteIterator implements Enumeration<Long> {
  int c = 1013904223, a = 1664525;
  long seed, N, m, next;
  boolean hasNext = true;

  public RandomPermuteIterator(long N) throws Exception {
    if (N <= 0 || N > Math.pow(2, 62)) throw new Exception("Unsupported size: " + N);
    this.N = N;
    m = (long) Math.pow(2, Math.ceil(Math.log(N) / Math.log(2)));
    next = seed = new Random().nextInt((int) Math.min(N, Integer.MAX_VALUE));
  }

//  public static void main(String[] args) throws Exception {
//    RandomPermuteIterator r = new RandomPermuteIterator(100);
//    while (r.hasMoreElements()) System.out.print(r.nextElement() + " ");
//    //output:50 52 3 6 45 40 26 49 92 11 80 2 4 19 86 61 65 44 27 62 5 32 82 9 84 35 38 77 72 7 ...
//  }

  @Override
  public boolean hasMoreElements() {
    return hasNext;
  }

  @Override
  public Long nextElement() {
    next = (a * next + c) % m;
    while (next >= N) next = (a * next + c) % m;
    if (next == seed) hasNext = false;
    return next;
  }
}
