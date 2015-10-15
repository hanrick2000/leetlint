package TEST;

/**
 * Created by 7:54 PM on 10/14/2015.
 */
public class OSproj2 {
    public static void main(String[] args) {
        printFix();
    }

    public static void printFix() {
        for (int i = 1048576; i <= 10485760;) {
            System.out.println("RUN_FIXED(" + 1 + ", FIXED_FILE, "+ i + ");");
            System.out.println("RUN_FIXED(" + 2 + ", FIXED_FILE, "+ i + ");");
            System.out.println("RUN_FIXED(" + 3 + ", FIXED_FILE, "+ i + ");");
            System.out.println("RUN_FIXED(" + 11 + ", FIXED_FILE, "+ i + ");");
            i = i + 1048576; //(int)Math.pow(2, i);
        }
    }

    public static void printMix() {
        int lo = 10240, hi = 102400;
        for (int i = 1; i < 1024;) {
            System.out.println("RUN_MIXED(" + i +", MIXED_FILES, "+ lo + ", " + hi + ");");
            i = i + 1; //(int)Math.pow(2, i);
        }
    }
}
