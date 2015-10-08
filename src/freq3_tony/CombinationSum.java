package freq3_tony;
import java.util.*;
/**
 * 结合N00t和Ganker的解法. 就是NP常用的循环递归来遍历所有情形. 注意这里控制recursion的元素从Combination里面的deep变成了target. 
 * 而且要注意Ganker的解法更好, 因为他在递归中增加了一个判断跳过重复元素(是指本来给的list就有多个相同的元素)的使用, 因为本来就是reuse同一个元素. 这样可以避免重复解. 
 * N00t的分析指出了这个candidates里面不会有负数. 因为这和Shortest Path里面如果有path是负数的情况(???)
 * @author tzhang
 *
 */
public class CombinationSum {
  private List<List<Integer>> combinationSum(int[] list, int target) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    if (list == null || list.length == 0)
      return res;
    Arrays.sort(list);
//    dfsI(list, 0, target, new ArrayList<Integer>(), res);
    dfsII(list, 0, target, new ArrayList<Integer>(), res);
    return res;
  }
  
  /**
   * 这个是DFS I: 用来解决COmbinationSUm的I. 即可以reuse, 但不能使用list中的重复的元素.
   * 超级DFS模版: 循环递归解决NP的所有解
   * @param list : NP的输入集合
   * @param start : 每一次recursion中的起点
   * @param target : 每一个recursion是去解决一个子问题. 关键处.
   * @param path : 当前recursion解决的path.
   * @param result : NP的解集合
   */
  private void dfsI(int[] list, int start, int target, List<Integer> path, List<List<Integer>> result) {
    if (target < 0) 
      return;
    if (target == 0) {
      result.add(new ArrayList<Integer>(path));
    }
    for (int i = start; i < list.length; ++i) {
      if (i > 0 && list[i] == list[i-1])
        continue;  // Ganker这里做得很好. 避开了重复解的情况.
      if (list[i] > target) 
        break;  // 为什么没有提前退出呢?
      path.add(list[i]);
      dfsI(list, i, target-list[i], path, result);  // 注意这里是i.
      path.remove(path.size()-1);
    }
  }
  
  /**
   * 这里的dfsII用来解决CombinationSUmII, 即可以使用list中重复的元素, 但不能reuse自己. 所以每次recursion的start+1. 而且
   * @param list
   * @param start
   * @param target
   * @param path
   * @param res
   */
  private void dfsII(int[] list, int start, int target, List<Integer> path, List<List<Integer>> res) {
    if (target < 0 || start >= list.length)
      return;
    if (target == 0) {
      res.add(new ArrayList<Integer>(path));
    }
    for (int i = start; i < list.length; ++i) {
      if (i > start && list[i] == list[i-1])  // 注意这里是i>start
        continue;
      path.add(list[i]);
      dfsII(list, i+1, target - list[i], path, res);  // 注意这里i+1了
      path.remove(path.size()-1);
    }
  }
  
  public CombinationSum(){
//    int[] list = new int[] { 2, 2, 3, 6, 7};
    int[] list = { 10,1,2,7,6,1,5 };
    List<List<Integer>> jie = new ArrayList<List<Integer>>();
    jie = combinationSum(list, 8);
    System.out.println(jie);
  }
  
  public static void main(String[] args) {
    CombinationSum csi = new CombinationSum();
    
  }
}
