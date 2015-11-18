package JiuChap6_FollowUpA;

import java.util.PriorityQueue;

/**
 * http://www.lintcode.com/en/problem/kth-smallest-number-in-sorted-matrix
 * Created at 5:13 PM on 11/16/15.
 */
public class KthSmallSortMatrix {
  public static void main(String[] args) {
    int[][] M = new int[][]{
        //{1,5,7},
        //{3,7,8},
        //{4,8,9}
        {1,2,3,4,5},
        {2,3,4,5,6},
        {3,4,5,6,7},
        {4,5,6,7,8}
    };
    KthSmallSortMatrix kssm = new KthSmallSortMatrix();
    int ans = kssm.kthSmallest(M, 19);
    System.out.println(ans);
  }

  /**
   * 9 chap's answer. think twice why he only go for 1 direction
   * @param matrix
   * @param k
   * @return
   */
  public int kthSmallest(int[][] matrix, int k) {
    PriorityQueue<Point> minPQ = new PriorityQueue<>();
    for (int i = 0; i < matrix[0].length; ++i) {
      minPQ.offer(new Point(0, i, matrix[0][i]));
    }
    for (int i = 0; i < k-1; ++i) {
      Point cand = minPQ.poll();
      if (cand.x+1 < matrix.length)
        minPQ.offer(new Point(cand.x+1, cand.y, matrix[cand.x+1][cand.y]));
      //if (cand.y+1 < matrix[0].length)
      //  minPQ.offer(new Point(cand.x, cand.y+1, matrix[cand.x][cand.y+1]));
    }
    return minPQ.poll().val;
  }

  /**
   * Failed in duplicate matrix: don't know which way to go for next step.
   * @param matrix: a matrix of integers
   * @param k: an integer
   * @return: the kth smallest number in the matrix
   */
  public int kthSmallestWRONG(int[][] matrix, int k) {
    // write your code here
    PriorityQueue<Point> minPQ = new PriorityQueue<>();
    minPQ.offer(new Point(0, 0, matrix[0][0]));
    for (int i = 0; i < k-1; ++i) {
      Point cand = minPQ.poll();
      if (cand.x+1 < matrix.length)
        minPQ.offer(new Point(cand.x+1, cand.y, matrix[cand.x+1][cand.y]));
      if (cand.y+1 < matrix[0].length)
        minPQ.offer(new Point(cand.x, cand.y+1, matrix[cand.x][cand.y+1]));
    }
    return minPQ.poll().val;
  }

  class Point implements Comparable<Point> {
    int x, y;
    int val;
    Point(int x, int y, int val) {
      this.x = x;
      this.y = y;
      this.val = val;
    }

    @Override public int compareTo(Point o) {
      return this.val - o.val;
    }
  }
}
