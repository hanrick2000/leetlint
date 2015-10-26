package Easy;

import java.util.ArrayList;
import java.util.List;

/**
 * http://www.lintcode.com/en/problem/merge-sorted-array-ii/
 * Created this class in Easy at 7:47 PM, 10/22/2015.
 */
public class MergeSortArrayII {
    public static void main(String[] args) {
        new MergeSortArrayII().test2();
    }

    public void test1() {
        ArrayList<Integer> A = new ArrayList<>();
        ArrayList<Integer> B = new ArrayList<>();

        A.add(2);
        A.add(115);
//        A.add(1);
//        A.add(2);
//        A.add(3);
//        A.add(4);

        B.add(2);
        B.add(4);
        B.add(5);
        B.add(7);

//        System.out.println(new MergeSortArrayII().mergeSortedArray(A,B));

//        int[] A = new int[]{1,2,3,4};
//        int[] B = new int[]{2,4,5,6};
//        int[] res = new MergeSortArrayII().mergeSortedArr(A, B);
//        System.out.println(Arrays.toString(res));

        System.out.println(new MergeSortArrayII().smallSortArr(A, B));
    }

    public void test2() {
        ArrayList<Integer> A = new ArrayList<>();
        ArrayList<Integer> B = new ArrayList<>();

        A.add(1);
        B.add(1);
//        A.add(5);
//        B.add(2);
//        B.add(3);

//        A.add(1);
//        A.add(2);
//        A.add(3);
//        A.add(4);
//
//        B.add(2);
//        B.add(4);
//        B.add(5);
//        B.add(7);
        System.out.println(new MergeSortArrayII().smallSortArr(B,A));
    }
    /**
     * @param A and B: sorted integer array A and B.
     * @return: A new sorted integer array
     */
    public ArrayList<Integer> mergeSortedArray(ArrayList<Integer> A, ArrayList<Integer> B) {
        // write your code here
        if (A == null) {
            return B;
        }
        if (B == null) {
            return A;
        }

        List<Integer> small, big;
        if (A.size() < B.size()) {
            small = A;
            big = B;
        }
        else {
            small = B;
            big = A;
        }

        ArrayList<Integer> result = new ArrayList<>();
        int ia = 0, ib = 0;
        while (result.size() < A.size() + B.size()) {
            if (ia > A.size() - 1) {
//                for (;ib < B.size(); ib++) {
//                    result.add(B.get(ib));
//                    ib++;
//                }
                result.add(B.get(ib++));
            }
            else if (ib > B.size() - 1) {
//                for (; ia < A.size(); ia++) {
//                    result.add(A.get(ia));
//                    ia++;
//                }
                result.add(A.get(ia++));
            }
            else if (A.get(ia) > B.get(ib)) {
                result.add(B.get(ib));
                ib++;
            }
            else {
                result.add(A.get(ia));
                ia++;
            }
        }

        return result;
    }

    /**
     * Merge sort for sorted array
     *
     * @param A
     * @param B
     * @return
     */
    public int[] mergeSortedArr(int[] A, int[] B) {
        int aLen = A.length;
        int bLen = B.length;
        int rLen = aLen + bLen;
        int[] result = new int[rLen];

        int ia = 0, ib = 0;
        for (int k = 0; k < rLen; ++k) {
            if (ia > aLen - 1) {
                result[k] = B[ib++];
            }
            else if (ib > bLen - 1) {
                result[k] = A[ia++];
            }
            else if (A[ia] < B[ib]) {
                result[k] = A[ia++];
            }
            else {
                result[k] = B[ib++];
            }
        }

        return result;
    }


    /**
     * http://algorithm.yuanbin.me/zh-cn/integer_array/merge_sorted_array_ii.html
     * use binary search to insert small element into result.
     *
     * time: O(large) + small * O(lg(large)).
     *
     * @param A
     * @param B
     * @return
     */
    public ArrayList<Integer> smallSortArr(List<Integer> A, List<Integer> B) {
        int aLen = A.size();
        int bLen = B.size();
        List<Integer> small; // = aLen < bLen ? A : B;
        List<Integer> large; // = aLen <= bLen ? B : A;

        if (aLen == bLen) {
            small = A;
            large = B;
        }

        else {
            small = aLen < bLen ? A : B;
            large = aLen > bLen ? A : B;
        }

        int sLen = small.size();
        int lLen = large.size();

        List<Integer> result = new ArrayList<>();
        for (int val : large) {
            result.add(val);
        }

        outer:
        for (int s : small) {
            int lo = 0, hi = result.size() - 1;  // during while loop, after insertion, the size changed
            while (lo + 1 < hi) {
                int mid = lo + (hi - lo) / 2;
                int midV = result.get(mid);
                if (midV == s) {
                    result.add(mid, s);
//                    break;
                    continue outer;
                } else if (midV < s) {
//                    if (mid == hi) {
//                        result.add(hi, s);
//                        continue outer;
//                    }
                    lo = mid;
                } else { // midV < s
//                    if (mid == 0) {
//                        result.add(0, s);
//                        continue outer;
//                    }
                    hi = mid;
                }
            }
            if (result.get(lo) == s) {
                result.add(lo, s);
            } else if (result.get(hi) == s) {
                result.add(s);
            } else {
                result.add(lo + 1, s);
            }
        }
        return (ArrayList<Integer>)result;
    }
}
