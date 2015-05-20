package freq4_tony;

import java.util.*;

public class Permutation {
  public List<List<Integer>> permute(int[] num) {
    List<List<Integer>> ans = new LinkedList<List<Integer>>();
    // solution 1:
//    if (num == null || num.length == 0) {
//      return ans;
//    }
//    helper1(ans, new LinkedList<Integer>(), num);

    // solution 2:
    ArrayList<Integer> remain = new ArrayList<Integer>();
    for (Integer i : num)
      remain.add(i);
    helper2(ans, new ArrayList<Integer>(), remain);
    return ans;
  }

  @SuppressWarnings("unused")
  private void helper1(List<List<Integer>> ans, List<Integer> path, int[] num) {
    if (path.size() == num.length) {
      ans.add(new LinkedList<Integer>(path));
      return;
    }
    for (int i = 0; i < num.length; i++) {
      if (!path.contains(num[i])) {
        path.add(num[i]);
        helper1(ans, path, num);
        path.remove(path.size() - 1);
      }
    }
  }

  private void helper2(List<List<Integer>> ans, List<Integer> path, ArrayList<Integer> remain) {
    int len = remain.size();
    if (len == 0) {
      ans.add(new ArrayList<Integer>(path));
      return;
    }
    for (int i = 0; i < len; i++){
      int t = remain.remove(i);
      path.add(t);
      helper2(ans, path, remain);
      remain.add(i, t);  // why?
      path.remove(path.size()-1);
    }
  }

  public static void main(String[] args) {
    int[] a = {3, 1, 2};
    List<List<Integer>> ans = new Permutation().permute(a);
    for (List<Integer> i : ans)
      System.out.println(i);
  }
}
