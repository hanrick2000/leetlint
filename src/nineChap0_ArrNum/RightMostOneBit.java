package nineChap0_ArrNum;

/**
 * http://www.geeksforgeeks.org/position-of-rightmost-set-bit/
 * Created by 1:47 AM on 10/15/2015.
 */
public class RightMostOneBit {
    public static void main(String[] args) {
        int[] test = new int[]{0, 18, 19};
        for (int i : test) {
            System.out.println(new RightMostOneBit().onlyLeaveRightMostOne(i));
        }
    }

    /**
     * http://www.geeksforgeeks.org/position-of-rightmost-set-bit/
     * @param num
     * @return
     */
    public int findRightMostSetBit(int num) {
        return (int)Math.log((int)(num & -num)) + 1;  // not working  ....
    }

    public int onlyLeaveRightMostOne(int n) {
        return n & -n;
    }
}
