package JiuChap7_FollowUpB;

/**
 * Created at 2:30 PM on 11/24/15.
 */
public class NextPermutation {
  public static void main(String[] args) {
    int[] A = new int[]{1,3,2};
    NextPermutation np = new NextPermutation();
    np.nextPermutation(A);
    for (int i : A) {
      System.out.print(i+" ");
    }
  }
  /**
   * @param nums: an array of integers
   * @return: return nothing (void), do not return anything, modify nums in-place instead
   */
  public void nextPermutation(int[] nums) {
    // write your code here
    if (nums == null || nums.length == 0) {
      return;
    }

    int size = nums.length;
    int k = -1; // pivot

    // step 1: find the first nums[k] < nums[k+1]
    for (int i = size-2; i >= 0; i--) {
      if (nums[i] < nums[i+1]) {
        k = i;
        break;
      }
    }
    if (k == -1) {
      reverse(nums, 0, size-1);
      return;
    }

    // step 2: swap the pivot with the smallest one greater than it.
    for (int i = size-1; i >= 0; i--) {
      if (nums[i] > nums[k]) {
        int tmp = nums[i];
        nums[i] = nums[k];
        nums[k] = tmp;
        break;
      }
    }

    // step 3: reverse all numbers from k+1 to n
    reverse(nums, k+1, size-1);
  }

  private void reverse(int[] nums, int lb, int ub) {
    for (int i =lb, j = ub; i < j; i++, j--) {
      int tmp = nums[i];
      nums[i] = nums[j];
      nums[j] = tmp;
    }
  }
}
