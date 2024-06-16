package DataStructure;

import java.util.Arrays;
// Time Complexity O(N^2)  (worst, average, and best)
// Space Complexity O(1)
public class SelectionSort {
    // Find the current min or current max and then swap
    // Advantage find the max or min first, then do swap on outer loop
    // This algorithm can have two approach, find largest OR find smallest
    public static void main(String[] args) {
        //int[] arr = {23,1,10,5,2};
        int[] arr = {5,2,1,6,4,2,8,11,2,9};
        System.out.println("Before Sort:"+ Arrays.toString(arr));
        selectionSortBySmall(arr);
        System.out.println("After Sort:"+Arrays.toString(arr));

        System.out.println("Before Sort:"+ Arrays.toString(arr));
        selectionSortByLarge(arr);
        System.out.println("After Sort:"+Arrays.toString(arr));
    }

    private static void selectionSortByLarge(int[] arr) {
        int n = arr.length;
        for(int i = 0; i < n-1; i++) {
            //assume first one is largest
            int max_idx = i;
            for(int j = i+1; j<n; j++) {
                if(arr[j] > arr[max_idx]) {
                    // we found bigger
                    max_idx = j;
                }
            }
            System.out.println("Max_idx:"+ max_idx + " n-i:" +(n-i));
            // we place the largest at first, if want reverse, change i start from last first
            int temp = arr[i];
            arr[i] = arr[max_idx];
            arr[max_idx] = temp;
            print(arr);
        }
    }

    private static void selectionSortBySmall(int[] arr) {
        int n = arr.length;
        for(int i = 0; i < n-1; i++) {
            int min_idx = i;
            for(int j = i; j < n; j++) {
                // we find the smallest
                if(arr[j] < arr[min_idx]) {
                    min_idx = j;
                }
            }
            // Swapping max value with the last
            System.out.println("Max_idx:"+ min_idx + " n-i:" +(n-i));
            // we place the smallest at first
            int temp = arr[i];
            arr[i] = arr[min_idx];
            arr[min_idx] = temp;
            print(arr);

        }
    }
    public static void print(int[] nums) {
        for (int num : nums) {
            System.out.printf("%d ",num);
        }
        System.out.println();
    }
}
