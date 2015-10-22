package Easy;

/**
 * http://www.lintcode.com/en/problem/space-replacement/
 * Created this class in Easy at 12:32 AM, 10/22/2015.
 */
public class SpaceReplace {
    public static void main(String[] args) {
        String input = "Mr John Smith    ";
        char[] chArr= input.toCharArray();
        new SpaceReplace().replaceBlank(chArr, 13);
        System.out.println(chArr);
//        int len = new SpaceReplace().replaceBlank1(chArr, 13);
//        System.out.println(len);
    }

    /**
     * @param string: Also the output. An array of Char
     * @param length: The true length of the string
     * @return: The true length of new string
     */
    public int replaceBlank(char[] string, int length) {
        if (string == null || string.length == 0) {
            return 0;
        }

        // phase 1: find the final length;
        int newLen = length;
        for (int i = 0; i < length; ++i) {
            if (string[i] == ' ') {
                newLen += 2;
            }
        }

        int newIdx = newLen;
//        char[] result = new char[newLen];
        // backward filling, so no overwritten
        for (int i = length - 1; i >= 0; i--) {
            if (string[i] == ' ') {
//                result[newIdx-1] = '0';
//                result[newIdx-2] = '2';
//                result[newIdx-3] = '%';
                string[newIdx-1] = '0';
                string[newIdx-2] = '2';
                string[newIdx-3] = '%';
                newIdx -= 3;
//                newIdx -= 4;
            }
            else {
//                result[newIdx-1] = string[i];
                string[newIdx-1] = string[i];
                newIdx--;
            }
        }

        return newLen;
//        return new String(result);
    }

    public int replaceBlank1(char[] string, int length) {
        // Write your code here
        if (string == null || length == 0) {
            return 0;
        }
        int result = length;
        for (int i = 0; i < length; i++) {
            if (string[i] == ' ') {
                result += 2;
            }
        }
        int index = result - 1;
        for (int i = length - 1; i >= 0; i--) {
            if (string[i] == ' ') {
                string[index] = '0';
                string[index - 1] = '2';
                string[index - 2] = '%';
                index -= 3;
            } else {
                string[index] = string[i];
                index--;
            }
        }
        return result;
    }
}
