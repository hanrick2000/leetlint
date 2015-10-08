package interview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/8.
 */
class FactoringInt {
    public static List<List<Integer>> get(int n) {
        List<Integer> cur= new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        helper(n,n,cur,result);
        return result;
    }
    private static void helper(int target, int start, List<Integer> cur, List<List<Integer>> result) {
        if(target==1) {
            List<Integer> newList= new ArrayList<>(cur);
            if(cur.size()==1) newList.add(1);
            result.add(newList);
            return;
        }
        for(int i=start;i>1;i--) {
            if(target>=i && target %i==0) {
                cur.add(i);
                helper(target/i,i,cur,result);
                cur.remove(cur.size()-1);
        }
        }
    }
}
