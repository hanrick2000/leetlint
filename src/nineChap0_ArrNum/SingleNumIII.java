package nineChap0_ArrNum;

import java.util.ArrayList;
import java.util.List;

/**
 * Super smart, Polia's <How to solve problem?> : link new puzzle to solved old puzzle
 * This is 2n+2 and the 2 single number are different. I know how to do 2n+1, then how
 * to do 2n+2? how to separate 2n+2 => 2n+1 + 2m+1. Then I know how to solve.
 *
 * Created by 1:24 AM on 10/15/2015.
 */
public class SingleNumIII {
    public static void main(String[] args) {
        int[] test = new int[]{4,12}; //{1,2,3,3,2,4,1,5};
        System.out.println(new SingleNumIII().singleNumberIII(test));
    }

    public List<Integer> singleNumberIII(int[] A) {
        // write your code here
        if (A == null || A.length == 0) {
            return null;
        }
        List<Integer> result = new ArrayList<>();
        int aXORb = 0; // remember, when doing all number XOR, the init is 0, not 1!!!!
        for (int i : A) {
            aXORb ^= i;
        }

        System.out.println(aXORb);
        int lastOne = aXORb & -aXORb;
        System.out.println(aXORb + " " + lastOne);

        int TwoN1 = 0, TwoM1 = 0; // 2*n+1, 2*m+1
        for (int i : A) {
            if ((i & lastOne) == 0) {
                TwoN1 ^= i;
            }
            else {
                TwoM1 ^= i;
            }
        }

        result.add(TwoN1);
        result.add(TwoM1);
        return result;
    }

}
