package DataStructure;

import java.util.Arrays;
// Time Complexity O(n^2) in the worst and average cases , O(N) if sorted
// Space Complexity O(1)
public class InsertionSort {
    // Better than BubbleSort in small dataset not large dataset  we loop before i from end to start, compare i(fixed) and j(--)
    // if nums[j] > nums[i] , replace nums[j+1] = nums[j] j--;
    // so it keeps go down and compare nums[j] and nums[i], however swap nums[j+1] and nums[i]
    /*
    5(j) 2(i) 1 4  replace second as 5 -> 5 5 1 4 -> after while loop replace 2 in j -> 2 5 1 4
    2 5(j) 1(i) 4 (5>1) replace -> 2(j) 5 5(i) 4 ,  (2>1) replace 2 2 5 4 -> 1 2 5 4
    1 2 4 5
     */
    public static void main(String[] args) {
        //int[] arr = {5,2,1,6,4,2,8,11,2,9};
        //int[] arr = {5,2,1,4};
        int[] arr = {23,1,10,5,2};
        System.out.println("Before Sort:"+ Arrays.toString(arr));
        insertionSort(arr);
        System.out.println("After Sort:"+Arrays.toString(arr));
    }
    public static void insertionSort(int[] nums) {
        int n = nums.length;
        for(int i = 1; i < n; i++) {
            int j = i - 1;
            int temp = nums[i];
            while(j>=0 && nums[j]>temp) {
                nums[j+1] = nums[j--];
                print(nums);
            }
            nums[j+1] = temp;
            System.out.println("After");
            print(nums);
            System.out.println();
        }
    }

    public static void print(int[] nums) {
        for (int num : nums) {
            System.out.printf("%d ",num);
        }
        System.out.println();
    }
}
