package freq4_tony;

import java.util.*;

public class PermutationII {
  public ArrayList<ArrayList<Integer>> permute(int[] num){
    ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
    ArrayList<Integer> remain = new ArrayList<Integer>();
    for (Integer i: num)
      remain.add(i);
    helper(ans, new ArrayList<Integer>(), remain);
    return ans;
  }
  
  private void helper(ArrayList<ArrayList<Integer>> ans, ArrayList<Integer> path, ArrayList<Integer> remain){
    int len = remain.size();
    if (len==0){
      ans.add(new ArrayList<Integer>(path));
      return;
    }
    
    for (int i = 0; i<len; i++){
      if (i!=0 && remain.get(i-1)==remain.get(i)){
        continue;
      }
      int t = remain.remove(i);
      path.add(t);
      helper(ans, path, remain);
      remain.add(i,t);
      path.remove(path.size()-1);
    }
  }
  
  public static void main(String[] args){
    int[] a = {1, 3, 3};
    ArrayList<ArrayList<Integer>> result = new PermutationII().permute(a);
    for (ArrayList<Integer> i : result)
      System.out.println(i);
  }
}
