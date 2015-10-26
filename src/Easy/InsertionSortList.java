package Easy;

import misc.ListNode;

import java.util.Arrays;

/**
 * Created this class in Easy at 7:30 PM, 10/22/2015.
 */
public class InsertionSortList {
    public static void main(String[] args) {
        int[] test = new int[]{3,2,1,4};
        InsertionSortList isl = new InsertionSortList();
        isl.insertionSortArray(test);
        System.out.println(Arrays.toString(test));
    }

    /**
     * @param head: The first node of linked list.
     * @return: The head of linked list.
     */
    public ListNode insertionSortList(ListNode head) {
        // write your code here
        if (head == null) {
            return head;
        }

        while (head != null) {
            // TODO
        }

        return null;
    }

    /**
     * Review insertion sort
     * @param nums
     */
    public void insertionSortArray(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            for (int j = i; j > 0 && nums[j] < nums[j-1]; j--) {
                exch(nums, j, j-1);
            }
        }
    }

    private static void exch(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
