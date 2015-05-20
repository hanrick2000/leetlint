package freq4_tony;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets_n00tc {
  private ArrayList<ArrayList<Integer>> subsetsHelper(int[] S, int cur,
      ArrayList<ArrayList<Integer>> results) {
    // reach end of set -> add [] as a subset
    if (cur >= S.length) {
      results.add(new ArrayList<Integer>());
      return results;
    }
    // generate subsets with remaining elements
    results = subsetsHelper(S, cur + 1, results);
    // append the current one to all subsets made up with remaining elements
    int curSize = results.size();
    while (curSize-- > 0) {
      ArrayList<Integer> res = new ArrayList<Integer>();
      res.add(S[cur]);
      res.addAll(results.get(curSize));
      results.add(res);
    }
    return results;
  }

  public ArrayList<ArrayList<Integer>> subsets_n00tc(int[] S) {
    // sort the given set
    Arrays.sort(S);
    // generate subsets
    return subsetsHelper(S, 0, new ArrayList<ArrayList<Integer>>());
  }

  public static void main(String[] args) {
    int[] a = {3, 1, 2};
    List<ArrayList<Integer>> ans = new Subsets_n00tc().subsets_n00tc(a);
    for (List<Integer> i : ans)
      System.out.println(i);
  }
}
