package DataStructure;

import java.util.Arrays;
// Time Complexity: (Worst: O(N^2), Average:(NlogN), Best(NlogN)
// Space Complexity: Worst:O(N), Average: O(logN)
// Its worst if it already sorted
public class QuickSort {
    // https://www.youtube.com/watch?v=Vtckgz38QHs&ab_channel=BroCode
    // Compare j and pivot (nums[j] < pivot) . if true swap(nums[++i], nums[j])
    // Therefore it guarantees group all the number smaller than pivot and numbers bigger than pivot
    /*
    input: [6,2,4,1,2,5] , p:pivot
    i 6(j) 2 4 1 2 5(p)
    i 6 2(j) 4 1 2 5(p)  , 2(j) < 5(p)  -> 6(i) 2(j) 4 1 2 5(p) , swap( 6(i), 2(j)) -> 2, 6, 4 1 2 5(p) ....
    2(i) 6(j) 4 1 2 5(p)
    2 4(i) 6(j) 1 2 5(p)
    2 4 1(i) 6(j) 2 5(p)
    2 4 1 2(i) 6(j) 5(p)
    keep repeat until j reach p idx ,  then i++ and swap pivot and i 2 4 1 2 5(p) 6(i), return idx i
     */

    public static void main(String[] args) {
        int nums[] = {6,2,4,1,2,5};
        System.out.println("Before sort: " + Arrays.toString(nums));
        quickSort(nums, 0, nums.length-1);
        System.out.println("After sort: "+ Arrays.toString(nums));
    }

    private static void quickSort(int[] nums, int start, int end) {
        if(end <= start) return; // for example, if pivot is the smallest, the partition will simply swap first one and pivot, return i = 0 , so end must be -1
        int pi = partition(nums, start, end);
        // recursive (the whole logic is same and match for left & right sub-array
        // the pivot is sorted, because left part must be smaller, right part must be larger , so the recursion only focus on [start, pi-1] and [pi+1, end]
        quickSort(nums, start, pi - 1);
        quickSort(nums,pi + 1, end);
    }

    private static int partition(int[] nums, int start, int end) {
        int i = start - 1;
        int pivot = nums[end];
        for(int j = start; j < end; j++) {
            if(nums[j] < pivot) {
                i++;
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        i++;
        int temp = nums[i];
        nums[i] = pivot;
        nums[end] = temp;
        return i;
    }

}
