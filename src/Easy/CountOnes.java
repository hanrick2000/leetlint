package Easy;

/**
 * Created by 9:13 PM on 10/12/2015.
 */
public class CountOnes {
    public static void main(String[] args) {
        int[] test = new int[] {0, 1, 4, 7, -1};
        for (int i : test) {
            System.out.println(i + " " + cnt1(i));
        }
    }

    /**
     * Regular bit operation
     *
     * @param num
     * @return
     */
    public static int cnt1(int num) {
        if (num == 0) {
            return 0;
        }
        int cnt = 1;
        while ((num & (num-1)) != 0) {
            num &= num -1;
            cnt++;
        }
        return cnt;
    }
}
