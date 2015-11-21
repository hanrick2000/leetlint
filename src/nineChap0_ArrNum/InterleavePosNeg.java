package nineChap0_ArrNum;

/**
 * http://www.lintcode.com/en/problem/interleaving-positive-and-negative-numbers/
 * Created at 2:36 PM on 11/20/15.
 */
public class InterleavePosNeg {
  public static void main(String[] args) {
    int[] A = new int[]{26,-31,10,-29,17,18,-24,-10}; //{-33,-19,30,26,21,-9}; // {-1, -2, 10, -5, 3,4,6}; //{5, 4, -1, -2, 3, -3};  //{-13,-8,-12,-15,-14,35,7,-1,11,27,10,-7,-12,28,18}; //
    InterleavePosNeg ipn = new InterleavePosNeg();
    ipn.rerange(A);
    for (int i : A) {
      System.out.print(i + " ");
    }
  }

  public void rerange(int[] A) {
    if (A == null || A.length == 0) {
      return;
    }
    int i = -1;
    int j = 0;
    for (; j < A.length; j++) {
      if (A[j] <= 0) {
        i++;
        exch(A, i, j);
      }
    }

    if (i < 0) {
      return; // no neg numbers
    }
    if (i == A.length-1) {
      return; // no pos numbers
    }

    int negNum = i+1;
    int posNum = A.length - negNum;

    int diff = negNum - posNum;
    int negBdy = 0, posBdy = 0;
    if (diff >= 0) {
      negBdy =posNum-1; //negNum - diff;
      for (int n = negBdy+1, p = i+1; n < negBdy+1+posNum; ++n) {
        exch(A, n, p);
        p++;
        posBdy = n;
      }
    }
    else {
      negBdy = negNum - 1;
      for (int p = i+1, n = 0; n < negNum; ++n) {
        exch(A, n, p);
        posBdy = p;
        p++;
      }
      //negBdy = i;
      //posBdy = i+negNum;//A.length - 1 - diff;
    }

    if (A[0] < 0) {
      int lp = 0, rp = posBdy;
      lp++;
      rp--;
      while (lp >= 0 && rp <= posBdy && lp < rp) {
        exch(A, lp, rp);
        lp += 2;
        rp -= 2;
      }
    }

    else {
      int lp = 0, rp = posBdy;
      while (lp <= negBdy && lp <= rp && lp < rp) {
        exch(A, lp, rp);
        lp += 2;
        rp -= 2;
      }
    }
  }
  /**
   * @param A: An integer array.
   * @return: void
   */
  public void rerangeBAD(int[] A) {
    // write your code here
    //if (A == null || A.length % 2 == 1) {
    //  return;
    //}
    if (A == null || A.length == 0) {
      return;
    }
    int leng = A.length;
    int i = -1, j = leng;
    while (true) {
      while (A[++i] < 0) {
        if (i + 1 >= j)  break;
      }
      while (A[--j] > 0) {
        if (i + 1 >= j)  break;
      }
      if (i + 1 >= j)  break;
      exch(A, i, j);
    }

    i = 0;
    j = leng-1;
    while (i+1 <= j) {
      exch(A, i, j);
      i += 2;
      j -= 2;
    }
  }

  private void exch(int[] A, int i, int j) {
    int tmp = A[i];
    A[i] = A[j];
    A[j] = tmp;
  }
}
