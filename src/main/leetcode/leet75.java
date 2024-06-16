public class leet75 {
    // QuickSort
    class Solution {
        /*
        i 2(j) 0 2 1 1 0(p)
        */
        public void sortColors(int[] nums) {
            // quick sort , merge sort
            quickSort(nums, 0, nums.length-1);
        }
        // 2,0,2,1,1,0 (partition = last) swap with start + 1
        public void quickSort(int[] nums, int start, int end) {
            if(end <= start) return;
            int pi = partition(nums, start, end);
            System.out.println(pi);
            // after getting partition part (i) , the left is not yet fully sorted, recurse it
            quickSort(nums,start, pi-1);
            quickSort(nums, pi+1, end);

        }
        public int partition(int[] nums, int start, int end) {
            int pivot = nums[end];
            // if nums[j] < pivot, i++ and then swap(nums[i], nums[j]); we guarantee all value on the left is < pivot
            int i = start-1;
            for(int j = start; j <= end - 1; j++) {
                if(nums[j] < pivot) {
                    i++;
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
            // at the end, i will +1 and swap with pivot, then the left part is gurantee smaller than pivot
            // then do recursively to the left and right
            i++;
            int temp = nums[i];
            nums[i] = pivot;
            nums[end] = temp;
            return i; // the partition part
        }
    }

    // MergeSort
    class Solution2 {
        public void sortColors(int[] nums) {
            mergeSort(nums);
        }
        private static void mergeSort(int[] nums) {
            if(nums.length == 1) return;
            int mid = nums.length/2;
            int[] leftArr = new int [mid];
            int[] rightArr = new int[nums.length-mid];
            for(int i = 0; i < mid; i++) {
                leftArr[i] = nums[i];
            }
            for(int i = mid; i < nums.length; i++) {
                rightArr[i-mid] = nums[i];
            }
            mergeSort(leftArr);
            mergeSort(rightArr);
            merge(nums,leftArr,rightArr);
        }
        private static void merge(int[] nums, int[] leftArr, int[] rightArr) {
            int i = 0, j = 0, k = 0;
            while(i < leftArr.length && j < rightArr.length) {
                if(leftArr[i] < rightArr[j]) {
                    nums[k++] = leftArr[i++];
                }else {
                    nums[k++] = rightArr[j++];
                }
            }
            while(i < leftArr.length) {
                nums[k++] = leftArr[i++];
            }
            while(j < rightArr.length) {
                nums[k++] = rightArr[j++];
            }
        }
    }
}
