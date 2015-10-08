package freq3_tony;

import java.util.*;

/**
 * 简单写法: 自下向上的DP, 不用recursion.
 * http://yishafang.blogspot.com/2015/02/triangle.html
 * @author tzhang
 *
 */
public class TrianglePathSum {

  /**
   * 最简洁高效的解法: loop的buttom-up 的 DP 解法. O(n^2)时间 + O(n)空间
   * @param triangle : input. be careful on init
   * @param path : building the smallest path on the run.
   * @return
   */
  private int minimumTrianglePathSum(List<List<Integer>> triangle, List<Integer> path) {
    if (triangle == null || triangle.size() == 0) {
      path = new ArrayList<>();
      return 0;
    }
    int[] res = new int[triangle.size()];
    // 初始化最底层最优解. 即每一个node的值就行了.
    int minLast = Integer.MAX_VALUE;
    for (int i = 0; i < triangle.size(); i++) {
      res[i] = triangle.get(triangle.size() - 1).get(i);
      minLast = (minLast < res[i]) ? minLast : res[i];
    }
    path.add(minLast);
    // 然后loop解决DP. 还是注意: DP是算法思维方式, recursion是代码结构方式. 没有联系.
    for (int i = triangle.size() - 2; i >= 0; i--) {
      PriorityQueue<Integer> findMin = new PriorityQueue<>();
      for (int j = 0; j <= i; j++) {
        int minres = Math.min(res[j], res[j + 1]);
        res[j] = minres + triangle.get(i).get(j);
        findMin.add(res[j] - minres);
      }
      int min = findMin.peek();
//      System.err.println(min + " ");
      path.add(min);
    }
    return res[0];
  }

  public TrianglePathSum() {
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
        add(4);
        add(1);
        add(8);
        add(3);
      }
    };
    tri.add(level1);
    tri.add(level2);
    tri.add(level3);
    tri.add(level4);

    Stack<Integer> minPath = new Stack<>();
    int result = minimumTrianglePathSum(tri, minPath);
    System.out.println("finally: " + minPath.size());
    while(!minPath.isEmpty())
      System.out.print(minPath.pop() + " ");
  }

  public static void main(String[] args) {
    TrianglePathSum tps = new TrianglePathSum();
    
  }
}
