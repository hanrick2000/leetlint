package freq3_tony;

import java.util.ArrayList;
import java.util.List;

/**
 * 这里使用N00t的方法来解决三角的path sum问题.
 * 
 * @author tzhang
 *
 */
public class TrianglePathSumN00tDFSDummy {
  /**
   * N00t的第一个解法: DFS+recursion. 注意param的设计. 再次深入理解recursion. 但是这样写就有个recursion最常见的问题: 重复计算. 所以这叫做DFS
   * dummy解法. DFS+DP在另一个class.
   * 
   * @param triangle
   * @param row
   * @param column
   * @param sum : path sum record
   * @param minSum : crux point
   * @return minSum : 这个是关键
   */
  private int DFSmethod1(List<List<Integer>> triangle, int row, int column,
      int sum, int minSum) {
    // add itself
    sum += triangle.get(row).get(column);
    // int minSum2 = 0;
    if (row == triangle.size() - 1) { // last row
      if (sum < minSum)
        return sum; // 留意这里, 并不是一定要在这里返回!!! 但是直接返回2者最小和这样写没区别.
    } else {
      /**
       * 注意这种模式: tony = rec(tony); tony = rec(tony);
       * 
       */
      // minSum2 = DFSmethod1(triangle, row + 1, column, sum, minSum);
      // minSum2 = DFSmethod1(triangle, row + 1, column + 1, sum, minSum);
      /*
       * 这里为什么要返回minSum呢? 这是因为Java pass-by-value. 只能修改object, 不能改primitive.
       * 所以"right Path"要想继承"left path"的结果, 就要让left path返回结果, 再做为right path的输入即可. 而DP解法中,
       * 使用array或者N00t的
       */
      minSum = DFSmethod1(triangle, row + 1, column, sum, minSum);
      minSum = DFSmethod1(triangle, row + 1, column + 1, sum, minSum);
    }

    return minSum;
  }

  private int minimumTotal(List<List<Integer>> triangle) {
    return DFSmethod1(triangle, 0, 0, 0, Integer.MAX_VALUE);
  }

  public List<List<Integer>> buildTriangle() {
    List<List<Integer>> tri = new ArrayList<List<Integer>>();
    /*
     * 用Anonymous Child Class来init ArrayList
     * http://mangstacular.blogspot.com/2013/04/4-ways-to-initialize-list-in-java.html
     */
    List<Integer> level1 = new ArrayList<Integer>() {
      {
        add(2);
      }
    };
    List<Integer> level2 = new ArrayList<Integer>() {
      {
        add(3);
        add(4);
      }
    };
    List<Integer> level3 = new ArrayList<Integer>() {
      {
        add(6);
        add(5);
        add(7);
      }
    };
    List<Integer> level4 = new ArrayList<Integer>() {
      {
        add(-4);
        add(1);
        add(8);
        add(3);
      }
    };
    tri.add(level1);
    tri.add(level2);
    tri.add(level3);
    tri.add(level4);

    return tri;
  }

  public TrianglePathSumN00tDFSDummy() {
    List<List<Integer>> tri = buildTriangle();

    // Stack<Integer> minPath = new Stack<>();
    int result = minimumTotal(tri);
    System.out.println("finally: " + result);
  }

  public static void main(String[] args) {
    TrianglePathSumN00tDFSDummy tpsnt = new TrianglePathSumN00tDFSDummy();
  }
}
