package nineChap0_ArrNum;

/**
 * Created by 2:38 AM on 10/15/2015.
 */
public class RemoveElem {
    public static void main(String[] args) {
        int[] test = new int[]{0,4,4,0,0,2,4,4};
        System.out.println(new RemoveElem().removeElement(test, 4));
    }

    /**
     * Partition as Algs4
     *
     * @param A
     * @param elem
     * @return
     */
    public int removeElement(int[] A, int elem) {
        // write your code here
        int lo = 0, hi = A.length - 1;
        while (true) {
            while (lo < A.length && A[lo] != elem) {
                lo++;
            }
            while (hi >= 0 && A[hi] == elem) {
                hi--;
            }
            if (lo > hi) {
                break;
            }
            exch(A, lo, hi);
        }
        for (int i : A) {
            System.out.print(i + " ");
        }
        return lo;
    }

    private void exch(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
}
