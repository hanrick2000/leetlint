package freq3_tony;

import java.util.*;

/**
 * N00t的Combination的2个解法: topdown/buttomup: http://n00tc0d3r.blogspot.com/2013/01/combinations.html
 * 经典的DFS解法模版
 * 同样的subsets, word break. 这些都是NP问题, 直接用循环递归遍历所有符合条件的.
 * @author tzhang
 *
 */
public class Combinations {

  //-------------------------------------------------> 方法1 : DFS
  /**
   * Top down的解法
   * @param n
   * @param k
   * @return
   */
  public List<List<Integer>> combine(int n, int k) {
    // http://stackoverflow.com/questions/17998806/how-to-create-and-then-get-elements-array-list-of-lists
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    combineHelper(1, n, k, new ArrayList<Integer>(), result);
    return result;
  }
  
  private void combineHelper(int s, int n, int k, List<Integer> path, List<List<Integer>> res) {
    if (n == 0)
      return;
    if (k == 0) {
      List<Integer> pathCpy = new ArrayList<Integer>(path);
      res.add(pathCpy);
      return;
    }
    
    for (int i = s; i <= n-k+1; ++i) {  // 为什么是n-k+1? 这个k相当于deep. 所以每一次recurse的范围都平移.
      path.add(i);
      combineHelper(i+1, n, k-1, path, res);
      path.remove(path.size()-1);
    }
  }

  //-------------------------------------------------> 方法2 : iteration
  public ArrayList<ArrayList<Integer>> combineIT(int n, int k) {  
    ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();  
    for (; k>0; --k) {  
      int num = results.size();  
      // add first element  
      if (num == 0) {  
        for (int i=1; i<=n-k+1; ++i) {  
          ArrayList<Integer> res = new ArrayList<Integer>();  
          res.add(i);  
          results.add(res);  
        }  
        continue;  
      }  
    
      // append rest  
      for (int j=0; j<num; ++j) { // loop through results from (i-1)-level  
        ArrayList<Integer> cur = results.get(j);  
        int last = cur.get(cur.size() - 1);  
        for (last+=1; last<n-k+1; ++last) {  
          ArrayList<Integer> res = new ArrayList<Integer>(cur);  
          res.add(last);  
          results.add(res);  
        }  
        cur.add(last);  // 为什么? Ans: 跟一遍Debugger就知道了.
      }  
    }  
    
    return results;  
  }  
  
  
  public Combinations() {
    ArrayList<ArrayList<Integer>> result = combineIT(4,2); // combine(4, 2);
    System.out.println(Arrays.asList(result));
  }

  public static void main(String[] args) {
    Combinations c = new Combinations();
  }
}
