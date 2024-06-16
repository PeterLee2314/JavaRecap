package DataStructure;

import java.util.Arrays;
// Time Complexity : O(NlogN) (worst, average, best)
// Space Complexity O(N)
public class MergeSort {
    // Recursive
    // divide and conquer (divide into smaller problem)
    // Logic: keep divide half and until individual, return merge and go up and compare
    public static void main(String[] args) {
        int nums[] = {6,2,4,1,2,5};
        System.out.println("Before sort: " + Arrays.toString(nums));
        mergeSort(nums);
        System.out.println("After sort: "+ Arrays.toString(nums));
    }

    private static void mergeSort(int[] nums) {
        if(nums.length == 1) return;

        int mid = nums.length/2; // divide (6/2 = 3)
        // create two array to store the divided array
        int leftArr[] = new int[mid]; // 3
        int rightArr[] = new int[nums.length-mid]; // 6 - 3 = 3
        for(int i = 0; i < mid; i++) {
            leftArr[i] = nums[i];
        }
        for(int i = mid; i < nums.length; i++) {
            // IMPORTANT: rightArr[i - mid]
            rightArr[i - mid] = nums[i];
        }

        // repeat Division
        mergeSort(leftArr);
        mergeSort(rightArr);
        // merge it (when reached this part, already only 1 element on left and right side
        merge(nums, leftArr, rightArr);
    }

    private static void merge(int[] nums, int[] leftArr, int[] rightArr) {
        // compare leftArr and rightArr in the loop
        int i = 0, j = 0, k = 0;
        // while both left and right have elements
        while(i < leftArr.length && j < rightArr.length) {
            // put smaller first
            if(leftArr[i] < rightArr[j]) {
                nums[k++] = leftArr[i++];
            }else {
                nums[k++] = rightArr[j++];
            }
        }

        // while only either left or right have elements, put it directly (because its already sort by the division)
        while(i < leftArr.length) {
            nums[k++] = leftArr[i++];
        }
        while(j < rightArr.length) {
            nums[k++] = rightArr[j++];
        }
    }
}
