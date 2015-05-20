package freq5_tony;

import java.util.*; // hashmap

/**
 * 今天回头复习发现不清楚3个做法是什么思路了. 以后记得要加上reference和思路在注释里面!!!
 * continue to work on 3 sum after finished 2 sum.
 */
public class ThreeSum {

  /**
   * solution 1: use brute force: can't solve duplicates, use Set instead!
   * 
   * @param input
   * @return
   */

  
  public ArrayList<ArrayList<Integer>> threeSum1(int[] input) {
    // HashMap<Integer, Integer> tony = new HashMap<Integer, Integer>(); --hashmap ...
    // won't help in this case.
    // int[] ret = new int[3]; --this one doesn't work since this problem has ...
    // multiple solutions.
    // ArrayList<Integer> each = new ArrayList<Integer>();
    ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
    Arrays.sort(input);
    int Cnt = 0;;
    for (int i = 0; i < input.length; i++) {
      if (input[i] > 0)
        break;
      for (int j = i + 1; j < input.length; j++) {
        if (input[i] + input[j] > 0 && input[j] > 0) // why need input[j] > 0? ...
          break; // since input is sorted, so larger j must fail!

        for (int k = j + 1; k < input.length; k++) {
          if (input[i] + input[j] == -input[k]) {
            ArrayList<Integer> each = new ArrayList<Integer>();
            each.add(input[i]);
            each.add(input[j]);
            each.add(input[k]);
            // if (!ret.containsAll(each)); Doesn't work
            ret.add(new ArrayList<>(each)); // http://stackoverflow.com/questions/25678222/java-arraylist-remains-empty-when-i-add-an-object-containing-integers
            // System.out.println("checking pre: "+ret);
            each.clear();
            // System.out.println("checking post: "+ret);
            Cnt++;
          }
        }
      }
    }
    System.out.println("I got #:" + Cnt);
    return ret;
  }

  /**
   * solution2: use head/tail pointers.
   * 这个就是Ganker或者N00t的方法. 使用sort过后的list, 然后用夹逼法则-->2个pointer.
   * @param args
   */
  public ArrayList<ArrayList<Integer>> threeSum2(int[] input) {
    ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
    Arrays.sort(input);
    for (int i = 0; i < input.length - 2; i++) {
      // avoid duplicate solutions
      if (i == 0 || input[i] > input[i - 1]) {
        int start = i + 1;
        int tail = input.length - 1;
        // int sum = input[i] + input[start] + input[tail]; // wrong! no-update.
        while (start < tail) {
          int sum = input[i] + input[start] + input[tail];
          // case 1:
          if (sum == 0) {
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            tmp.add(input[i]);
            tmp.add(input[start]);
            tmp.add(input[tail]);
            ret.add(tmp);
            start++;
            tail--;
            // avoid duplicate solutions: which is: next solution's input[start] != previous
            // input[start] ...
            // same for the input[tail]
            while (start < tail && input[start] == input[start - 1]) { // since start++ in line 73
              start++;
            }
            while (start < tail && input[tail] == input[tail + 1]) { // since tail-- in line72
              tail--;
            }
          }
          // case 2:
          else if (sum < 0) {
            start++; // interesting!!!!
          }
          // case 3:
          else {
            tail--; // interesting!!!!
          }
        }
      }
    }
    return ret;
  }

  /**
   * solution 3: use Set instead!
   * 
   * @param input
   * @return
   */
  // ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();

  public ArrayList<ArrayList<Integer>> threeSum3(int[] input) {
    ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
    HashSet<ArrayList<Integer>> hashSet = new HashSet<>();
    Arrays.sort(input);
    int Cnt = 0;;
    for (int i = 0; i < input.length; i++) {
      if (input[i] > 0)
        break;
      for (int j = i + 1; j < input.length; j++) {
        if (input[i] + input[j] > 0 && input[j] > 0) // why need input[j] > 0? ...
          break; // since input is sorted, so larger j must fail!

        for (int k = j + 1; k < input.length; k++) {
          if (input[i] + input[j] == -input[k]) {
            ArrayList<Integer> each = new ArrayList<Integer>();
            each.add(input[i]);
            each.add(input[j]);
            each.add(input[k]);
            if (!hashSet.contains(each)) {
              hashSet.add(each);
              ret.add(each);
            }
            // each.clear();
            // System.out.println("checking post: "+ret);
            Cnt++;
          }
        }
      }
    }
    System.out.println("I got #:" + Cnt);
    return ret;
  }



  public static void main(String[] args) {
    int a[] = {-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6};
    System.out.println(new ThreeSum().threeSum2(a));
  }

}
