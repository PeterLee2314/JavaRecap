import java.util.*;

public class leet1509 {
    //Greedy
    class Solution {
        public int minDifference(int[] nums) {
            // make largest smallerm, smaller larger
            Arrays.sort(nums);
            int n = nums.length;
            if(n <= 4) return 0;
            int res = Integer.MAX_VALUE;
            //if largest than 4, we can check the biggest and smallest diff
            for(int left = 0, right = n - 4; left < 4; left++,right++) {
                res = Math.min(res, nums[right] - nums[left]);
            }

            return res;
        }
    }
    /*

    1,2,3,4,5,6,7,8 , assume 6,7,8 become 5, assume 1,7,8 become 6....
    1,2,3,4,5,5,5,5
    */
    //Partial Sort + Greedy O(N)
    class Solution2 {
        public int minDifference(int[] nums) {
            int n = nums.length;
            if (n <= 4) {
                return 0;
            }
            // find smallest 4
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            for(int num:nums) {
                maxHeap.offer(num);
                if(maxHeap.size() > 4) {
                    maxHeap.poll(); // we poll the max as always
                }
            }
            List<Integer> smallestFour = new ArrayList<>(maxHeap);
            Collections.sort(smallestFour);
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            for(int num:nums) {
                minHeap.offer(num);
                if(minHeap.size() > 4) {
                    minHeap.poll(); // we poll the max as always
                }
            }
            List<Integer> largestFour = new ArrayList<>(minHeap);
            Collections.sort(largestFour);
            int res = Integer.MAX_VALUE;
            for(int i = 0; i < 4; i++) {
                res = Math.min(res, largestFour.get(i) - smallestFour.get(i));
            }

            return res;
        }
    }
}
