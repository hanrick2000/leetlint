package testingAns;

import java.util.ArrayList;

/**
 * Created at 12:24 AM on 11/16/15.
 */
public class KthLargest9Chap {
  public static void main(String[] args) {
    int[] A = new int[]{1,2,3,4,5,6,8,9,10,7,5,5,6}; //{1,1,1}; //
    KthLargest9Chap kl = new KthLargest9Chap();
    ArrayList<Integer> Alist = new ArrayList<>();
    for (int a : A) {
      Alist.add(a);
    }
    int ans;
    for (int i = 1; i <= A.length; ++i) {
      ans = kl.kthLargestElement(A.length-i+1, Alist);
      System.out.println(ans);
    }
  }

  public int kthLargestElement(int k, ArrayList<Integer> numbers) {
    if (numbers == null || numbers.size() == 0) {
      return 0;
    }
    if (k <= 0) {
      return 0;
    }
    return helper(numbers, 0, numbers.size() - 1, k);
  }

  public int helper(ArrayList<Integer> numbers, int l, int r, int k) {
    if (l == r) {
      return numbers.get(l);
    }
    //int position = partition(numbers, l, r);

    //int pivot = numbers.get((l+r)/2);
    //int position = partition(numbers, l, r, pivot);
    int position = partition(numbers, l, r);
    if (position + 1 == k) {
      return numbers.get(position);
    } else if (position + 1 < k) {
      return helper(numbers, position + 1, r, k);
    }  else {
      return helper(numbers, l, position - 1, k);
    }
  }

  //public int partition(ArrayList<Integer> numbers, int l, int r) {
  //  if (l == r) {
  //    return l;
  //  }
  //  int num = numbers.get(r);
  //  int index = l;
  //  for (int i = l; i < r; i ++) {
  //    if (numbers.get(i) >= num) {
  //      int temp = numbers.get(i);
  //      numbers.set(i, numbers.get(index));
  //      numbers.set(index, temp);
  //      index ++;
  //    }
  //  }
  //
  //  numbers.set(r, numbers.get(index));
  //  numbers.set(index, num);
  //  return index;
  //}

  public int partition(ArrayList<Integer> nums, int p, int r, int pivot) {
    if (p == r) {
      return p;
    }
    int i = p;
    for (int j = p+1; j <= r; ++j) {
      if (nums.get(j) < pivot) {
        ++i;
        exch(nums, i, j);
      }
      else if (nums.get(j) == pivot) {
        exch(nums, p, j);
        j--;
      }
    }
    exch(nums, p, i);
    return i;
  }

  public int partition(ArrayList<Integer> nums, int p, int r) {
    int x = nums.get((p+r)/2);
    int i = p-1, j = r+1;
    while (true) {
      while (nums.get(++i) <= x) {
        if (i == (p+r)/2)  continue;
        if (i == r)  break;
      }
      while (nums.get(--j) > x) {
        if (j == (p+r)/2)  continue;
        if (j == p)  break;
      }
      if (i >= j) {
        break;
      }
      exch(nums, i, j);
    }
    exch(nums, (p+r)/2, j);
    return j;
  }

  private void exch(ArrayList<Integer> nums, int i, int j) {
    int tmp = nums.get(i);
    nums.set(i, nums.get(j));
    nums.set(j, tmp);
  }
}
