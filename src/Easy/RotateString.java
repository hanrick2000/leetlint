package Easy;

/**
 * Created by 12:29 PM on 10/14/2015.
 */
public class RotateString {
    public static void main(String[] args) {
        String test = "abcdefg";
        char[] str = test.toCharArray();
        new RotateString().rotateString(str, 1);
        for (char c : str) {
            System.out.print(c);
        }
    }

    public void rotateString(char[] str, int offset) {
        // write your code here
        if (str == null || str.length == 0) {
            return;
        }
        int leng = str.length;
        offset = offset % leng;
        reverse(str, 0, leng - offset - 1);
        reverse(str, leng - offset, leng-1);
        reverse(str, 0, leng-1);
    }

    private void reverse(char[] A, int start, int end) {
        for (int i = start, j =end; i<j; i++, j--) {
            char temp = A[i];
            A[i] = A[j];
            A[j] = temp;
        }
    }
}

