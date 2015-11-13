package POJ.DP;

import java.util.ArrayList;
import java.util.List;

/**
 * Relearn Recursion!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * http://poj.org/problem?id=1088
 * Created this class in POJ.random at 11:44 AM, 10/27/2015.
 */
public class POJ1088_Ski {

  public static void main(String[] args) {
    POJ1088_Ski p1088 = new POJ1088_Ski();
    p1088.test();
  }

  /**
   * 1  2  3  4  5
   * 16 17 18 19 6
   * 15 24 25 20 7
   * 14 23 22 21 8
   * 13 12 11 10 9
   *
   * 25-24-23-...-3-2-1
   *
   * 1  2  3  4
   * 12 13 14 5
   * 11 16 15 6
   * 10 9  8  7
   *
   *
   * 1 5 6
   * 9 4 7
   * 2 3 8
   *
   * 8-7-6-5-4-3-2
   *
   */
  public void test() {
    int sz = 4;
    int R = sz, C = sz;
    int[][] node = new int[R][C];
    int[] data = new int[]{1,2,3,4,12,13,14,5,11,16,15,6,10,9,8,7}; //{1,2,3,4,5,16,17,18,19,6,15,24,25,20,7,14,23,22,21,8,13,12,11,10,9}; //{1,5,6,9,4,7,2,3,8}; //
    for (int r = 0; r < R; ++r) {
      for (int c = 0; c < C; ++c) {
        node[r][c] = data[r*C+c];
      }
    }

    poj1088(node);
  }

  public void poj1088(int[][] node) {
    if (node == null) {
      return;
    }
    int Row = node.length, Col = node[0].length;
    int[][] step = new int[Row][Col]; // the maximum steps to reach node[i][j].

    List<Integer>[][] lujin = new ArrayList[Row][Col];
//    int res00 = helperP(node, step, lujin, 0, 0);
//    System.out.println(lujin);

//    for (int i = 0; i < Row; ++i) {
//      for (int j = 0; j < Col; ++j) {
//        List<Integer> lij = new ArrayList<>();
//        lij.add(node[i][j]);
//        lujin[i][j] = lij;
//      }
//    }


//    int lkj = helperP(node, step, lujin, 0, 0); // not correct!

    int jie = 0;
    int maxR = 0,  maxC = 0;
    for (int i = 0; i < Row; ++i) {
      for (int j = 0; j < Col; ++j) {
//        List<Integer> lij = new ArrayList<>();
//        lij.add(node[i][j]);
//        List<Integer>[][] list = new ArrayList[Row][Col];
//        list[i][j] = lij;
        List<Integer>[][] list = new ArrayList[Row][Col];  // learn from : http://stackoverflow.com/a/28588120/3984911
        for (int ii = 0; ii < Row; ++ii) {
          for (int jj = 0; jj < Col; ++jj) {
            List<Integer> lij = new ArrayList<>();
            lij.add(node[ii][jj]);
            list[ii][jj] = lij;
          }
        }
        int tmp = helperP(node, step, list, i, j); // NullPointerException!
        if (jie < tmp) {
          jie = tmp;
          lujin = list;
          maxR = i;
          maxC = j;
        }
      }
    }

    for (int i : lujin[maxR][maxC]) {
      System.out.print(i+" ");
    }
    System.out.println();
//    int douban00 = helperDouban(node, step, 0, 0);
    step = new int[Row][Col]; // why i have to reset step?

    int res = 0;
    for (int i = 0; i < Row; ++i) {
      for (int j = 0; j < Col; ++j) {
        int tmp = helperDouban(node, step, i, j);
        res = Math.max(res, tmp);
      }
    }
    System.out.println("Douban: " + (res + 1));

    step = new int[Row][Col]; // why i have to reset step?

    int beeder20 = helper(node, step, 0, 0, Row, Col);
    int beeder10 = helper(node, step ,1, 0, Row, Col);
    int ans = 0;
    for (int i = 0; i < Row; ++i) {
      for (int j = 0; j < Col; ++j) {
        int tmp = helper(node, step, i, j, Row, Col);
        ans = Math.max(ans, tmp);
      }
    }
    System.out.println("Beeder:" + ans);

    return;
  }

  /**
   * http://beeder.github.io/2015/01/06/POJ-Problem-1088-skiing/
   *
   * @param node
   * @param path
   * @param r
   * @param c
   */
  private int helper(int[][] node, int[][] path, int r, int c, int Row, int Col) {
    // add result
    int rs = 0;  // beeder's solution
    for (int i = 0; i < 4; ++i) {
      int next_r = r + dx[i];
      int next_c = c + dy[i];
      if (isValid(node, r, c, next_r, next_c, Row, Col)) {
        if (path[next_r][next_c] != 0) {
          rs = Math.max(rs, path[next_r][next_c]);
        }
        else {
          int tmp = helper(node, path, next_r, next_c, Row, Col);
          rs = Math.max(rs, tmp);
        }
      }
    }
    return path[r][c] = rs + 1;
  }

  /**
   * How to return the longest path, instead the length?
   * Need to fully understand my design, take an hour to fix.
   *
   * @param node
   * @param path
   * @param list
   * @param r
   * @param c
   * @return
   */
  private int helperP(int[][] node, int[][] path, List<Integer>[][] list, int r, int c) {
    int rs = 0;

    int lastSize = 0;
    for (int i = 0; i < 4; ++i) {
      int rr = r + dx[i];
      int cc = c + dy[i];
      if (isValid(node, r, c, rr, cc, node.length, node[0].length)) {
        if (path[rr][cc] != 0) {
          if (rs < path[rr][cc]) {
            for (int id = 0; id < lastSize; ++id) {
              list[r][c].remove(list[r][c].size() - 1);
            }
            list[r][c].addAll(list[rr][cc]); //add(node[rr][cc]); //
            lastSize = list[rr][cc].size();
            rs = path[rr][cc];
          }
          else {
            rs = rs;
//            list[r][c].remove(node[rr][cc]);
          }
        }

        else {
          int tmp = helperP(node, path, list, rr, cc);

          if (tmp > rs) {
            rs = tmp;
            for (int id = 0; id < lastSize; ++id) {
              list[r][c].remove(list[r][c].size() - 1);
            }
            list[r][c].addAll(list[rr][cc]); // add(node[rr][cc]);
            lastSize = list[rr][cc].size();
          }
          else {
            rs = rs;
//            list[r][c].removeAll(list[rr][cc]);
//            for (int id = 0; id < lastSize; ++id) {
//              list[r][c].remove(list[r][c].size() - 1);
//            }
          }
        }
      }
    }

//    list.add(node[r][c]);
    return path[r][c] = rs + 1;
  }

  /**
   * http://www.douban.com/note/209925117/
   *
   * @param node
   * @param path
   * @param r
   * @param c
   * @return
   */
  private int helperDouban(int[][] node, int[][] path, int r, int c) {
    if (path[r][c] > 0) {
      return path[r][c];
    }

    for (int i = 0; i < 4; ++i) {
      int next_r = r + dx[i];
      int next_c = c + dy[i];
      if (isValid(node, r, c, next_r, next_c, node.length, node[0].length)) {
        path[r][c] = Math.max(path[r][c], helperDouban(node, path, next_r, next_c) + 1);
      }
    }

    return path[r][c];
  }

  int[] dx = new int[]{0, 0, 1, -1};
  int[] dy = new int[]{1, -1, 0, 0};

  private boolean isValid(int[][] node, int r, int c, int next_r, int next_c, int R, int C) {
    if (next_r < 0 || next_r >= R || next_c < 0 || next_c >= C ||
        r < 0 || r >= R || c < 0 || c >= C ||
        node[r][c] > node[next_r][next_c]) {
      return false;
    }
    return true;
  }
}
