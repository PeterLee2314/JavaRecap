package DataStructure;

import java.util.Arrays;
// Time Complexity O(n^2) in the worst and average cases, O(N) if sorted
// Space Complexity O(1)
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {5,2,1,6,4,2,8,11,2,9};
        System.out.println("Before Sort:"+ Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println("After Sort:"+Arrays.toString(arr));
    }
    public static void bubbleSort(int[] nums) {
        for(int i = 0; i < nums.length; i++) {
            for(int j = 1; j < nums.length; j++) {
                if(nums[j-1] > nums[i]) {
                    int temp = nums[j-1];
                    nums[j-1] = nums[i];
                    nums[i] = temp;
                }
            }
        }
    }
}

