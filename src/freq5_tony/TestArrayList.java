/**
 * 
 */
package freq5_tony;

import java.util.*;

/**
 * @author tzhang
 * 
 */
public class TestArrayList {

  /**
   * 
   */
  public ArrayList<ArrayList<Integer>> testArrayList(int[] input) {
    //
    ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
    ArrayList<Integer> each = new ArrayList<Integer>();
    for (int i = 0; i < input.length; i++) {
      each.add(input[i]);
      if (i % 3 == 2) {
        ret.add(each);
        System.out.println(ret);
        each.clear();

      }
    }
    return ret;
  }
  
  static int cnt;
  public static void modu(int n){
    int res1 = n /2;
    int res2 = n >>1;
    int rem1 = n %2;
    int rem2 = n &1;
    cnt++;
    System.out.println(cnt +". I got yu shu: "+ res1+ " vs " + res2);
    System.out.println(cnt + ". I got remainder: "+ rem1+ " vs " + rem2);
  }

  public static ArrayList<ArrayList<Integer>> testReference(){
    ArrayList<Integer> al = new ArrayList<Integer>();
    ArrayList<ArrayList<Integer>> alal = new ArrayList<>();
    al.add(0);
    al.add(3);
    al.add(99);
    alal.add(al);
//    al.clear();       --- clear the reference, so alal got clear too!
    return alal;
  }
  /**
   * @param args
   */
  public static void main(String[] args) {
    int a[] = {1, 2, 3, 4, 5, 6};
//    System.out.println((new TestArrayList().testArrayList(a)));
//    modu(3);
//    modu(-3);
    testReference();
    System.out.println(testReference().get(0));
    System.out.println(Integer.MIN_VALUE);
  }

}
