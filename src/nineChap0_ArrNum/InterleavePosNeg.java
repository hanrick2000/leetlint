package nineChap0_ArrNum;

/**
 * http://www.lintcode.com/en/problem/interleaving-positive-and-negative-numbers/ Created at 2:36 PM
 * on 11/20/15.
 */
public class InterleavePosNeg {
  public static void main(String[] args) {
    int[] A = new int[] {1, 2, 3, 4, -2,
        -6}; //{-33,-19,30,26,21,-9}; // {-1, -2, 10, -5, 3,4,6}; //{5, 4, -1, -2, 3, -3};  //{-13,-8,-12,-15,-14,35,7,-1,11,27,10,-7,-12,28,18}; //
    InterleavePosNeg ipn = new InterleavePosNeg();
    ipn.rerange(A);
    for (int i : A) {
      System.out.print(i + " ");
    }
  }

  /**
   * http://www.cnblogs.com/yuzhangcmu/p/4175620.html
   * SOlution 3 of yuzhangCMU
   * @param A
   * @return
   */
  public static int[] rerange(int[] A) {
    // write your code here
    // Check the input parameter.
    if (A == null || A.length <= 2) {
      return A;
    }

    int len = A.length;

    int cntPositive = 0;

    // store the positive numbers index.
    int i1 = 0;

    for (int i2 = 0; i2 < len; i2++) {
      if (A[i2] > 0) {
        cntPositive++;

        // Put all the positive numbers at in the left part.
        swap(A, i1++, i2);
      }
    }

    // If positive numbers are more than negative numbers,
    // Put the positive numbers at first.
    int posPointer = 1;
    int negPointer = 0;

    if (cntPositive > A.length / 2) {
      // Have more Positive numbers;
      posPointer = 0;
      negPointer = 1;

      // Reverse the array.
      int left = 0;
      int right = len - 1;
      while (left < right) {
        int tmp = A[left];
        A[left] = A[right];
        A[right] = tmp;
        left++;
        right--;
      }
    }

    // Reorder the negative and the positive numbers.
    while (true) {
      // Should move if it is in the range.
      while (posPointer < len && A[posPointer] > 0) {
        posPointer += 2;
      }

      // Should move if it is in the range.
      while (negPointer < len && A[negPointer] < 0) {
        negPointer += 2;
      }

      if (posPointer >= len || negPointer >= len) {
        break;
      }

      swap(A, posPointer, negPointer);
    }

    return A;
  }

  public static void swap(int[] A, int n1, int n2) {
    int tmp = A[n1];
    A[n1] = A[n2];
    A[n2] = tmp;
  }

  public void rerangeWRONG(int[] A) {
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
    if (i == A.length - 1) {
      return; // no pos numbers
    }

    int negNum = i + 1;
    int posNum = A.length - negNum;

    int diff = negNum - posNum;
    int negBdy = 0, posBdy = 0;
    if (diff >= 0) {
      negBdy = posNum - 1; //negNum - diff;
      for (int n = negBdy + 1, p = i + 1; n < negBdy + 1 + posNum; ++n) {
        exch(A, n, p);
        p++;
        posBdy = n;
      }
    } else {
      negBdy = negNum - 1;
      for (int p = i + 1, n = 0; n < negNum; ++n) {
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
    } else {
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
        if (i + 1 >= j) break;
      }
      while (A[--j] > 0) {
        if (i + 1 >= j) break;
      }
      if (i + 1 >= j) break;
      exch(A, i, j);
    }

    i = 0;
    j = leng - 1;
    while (i + 1 <= j) {
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
