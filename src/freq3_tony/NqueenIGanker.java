package freq3_tony;
import java.util.*;

/**
 * 最经典的NP问题用递归做. 也是backtracking的入门. 初中生学递归从fib, 高中就是用N-Queens了.
 * 今天又听了段公子的递归DP, top down(memory)和button-up(DP)的区别. 以及求solution和value的区别. 所以Ganker也讲了Queen I/II的区别.
 * @author tzhang
 *
 */
public class NqueenIGanker {
  private static void pirntStrArr(String[] sArr) {
    for (String s : sArr) {
      System.out.println(s);
    }
    System.err.println("---");
  }
  
  //
  public static void main(String[] args) {
    NqueenIGanker nqig = new NqueenIGanker();
    for (String[] strArr : nqig.solveNQ(4)) {
      pirntStrArr(strArr);
    }
  }
  /**
   * client for algs call
   * @param n
   * @return
   */
  public List<String[]> solveNQ(int n) {
    List<String[]> res = new ArrayList<String[]>();
    recur(n, 0, new int[n], res);
    return res;
  }
  
  /**
   * Ganker大神的模版. 当然, 算法竞赛那本书的模版才牛. 很类似9章的模版. 循环递归用在大部分的NP问题. 比如partition, permutation等等都见过.
   * Ganker大神的意思就是
   * @param n : 允许的行数. 即input
   * @param row :  当前这一行
   * @param colForRow : 漂亮: 因为recursion是一层一层递归下去的(关键!). 比如[2,1,3]表示每一行分别放在第2,1,3列.
   * @param res : 因为一个棋盘就是一组n个元素的string array. 所以总的解就是list of string array
   */
  private static void recur(int n, int row, int[] colForRow, List<String[]> res) {
    // 处理其中一次的有效的recursion
    if (row == n) {
      String[] item = new String[n];
      for (int i = 0 ; i < n; ++i) {
        StringBuilder sb = new StringBuilder();  // 每一行new一个stringbuilder来画图
        for (int j = 0; j < n; ++j) {
          if (colForRow[i] == j) {
            sb.append('Q'); // 注意是每一个string就是一个char[]. 所以append是char不是整个string
          } else 
            sb.append('.');
        }
        item[i] = sb.toString();
      }
      res.add(item);
      return; // finish this base condition and go back to the call stack
    }
    for (int i = 0; i < n; ++i) {
      colForRow[row] = i;  // 也就是循环尝试每一行的所有位置
      if (check(row, colForRow)) {  // backtracking, 提前结束, 换该行下一个列
        recur(n, row+1, colForRow, res);
      }
    }
  }
  
  private static boolean check(int row, int[] colForRow) {
    for (int i = 0; i < row; ++i) {
      // 检查是否
      if (colForRow[i] == colForRow[row])
        return false;
      // 检查是否同一个对角线. 注意这里的对角线不是直接一个X, 而是所有斜线!
      if (Math.abs(row-i) == Math.abs(colForRow[row]-colForRow[i]))
        return false;
    }
    return true;
  }
}
