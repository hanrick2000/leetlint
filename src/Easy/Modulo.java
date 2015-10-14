package Easy;

/**
 * Created by 10:08 PM on 10/12/2015.
 */
public class Modulo {
    public static void main(String[] args) {
        int[] data = new int[]{-10, 0, 15};
        for (int d : data) {
            System.out.println(d + " " + mod(d, 4));
        }
    }

    /**
     * Java's mod is finding remainder, not mod!!! Need to take care of neg number!
     * http://stackoverflow.com/a/4412200/3984911
     *
     * @param num
     * @param mod
     * @return
     */
    public static int mod(int num, int mod) {
        return (num % mod + mod) % mod;
//        return num % mod;
    }
}
