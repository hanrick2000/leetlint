package nineChap2_BinarySearch;

/**
 * Easy题目
 * 
 * @author tzhang
 *
 */
import java.util.*;

public class MergeSortArrList {
  public static void main(String[] args) {
    List<Integer> A = new ArrayList<>();
    List<Integer> B = new ArrayList<>();
//    A.add(1);
//    A.add(2);
//    A.add(3);
//    A.add(4);
    B.add(2);
    B.add(4);
    B.add(5);
    B.add(6);
    List<Integer> ans = mergeSortArrList(A, B);
    System.out.println(ans);
  }

  public static List<Integer> mergeSortArrList(List<Integer> A, List<Integer> B) {

    if (A == null && B == null)
      return null;
    int alen = A.size() - 1, blen = B.size() - 1;
    int rlen = A.size() + B.size()-1;
    int[] res = new int[A.size() + B.size()];
    while (alen >= 0 && blen >= 0) {
      if (A.get(alen) >= B.get(blen)) {
        res[rlen--] = A.get(alen--);
//        alen--;
      } else {
        res[rlen--] = B.get(blen--);
//        blen--;
      }
//      rlen--;
    }
    while (alen >= 0) {
      res[rlen--] = A.get(alen--);
//      alen--;
//      rlen--;
    }
    while (blen >= 0) {
      res[rlen--] = B.get(blen--);
//      blen--;
//      rlen--;
    }
    List<Integer> result = new ArrayList<>(A.size() + B.size());
    for (int i : res) {
      result.add(i);
    }
    return result;
  }
}
