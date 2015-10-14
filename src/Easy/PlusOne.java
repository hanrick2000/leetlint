package Easy;

/**
 * Created by 2:39 AM on 10/14/2015.
 */
public class PlusOne {
    public static void main(String[] args) {
        int[] test = new int[]{0}; //{9,9,9}; // {1,2,4};
        for (int i : plus1(test)) {
            System.out.print(i);
        }
    }

    public static int[] plus1(int[] digs) {
        if (digs == null || digs.length == 0) {
            return null;
        }
        int carry = 1;
        for (int i = digs.length-1; i >= 0; i--) {
            int tmp = carry + digs[i];
            carry = tmp / 10;
            digs[i] = tmp % 10;
        }
        if (carry == 1) {
            int[] res = new int[digs.length+1];
            for (int i = 1; i < digs.length+1; ++i) {
                res[i] = digs[i-1];
            }
            res[0] = carry;
            return res;
        }
        else {
            return digs;
        }
    }
}
