import java.util.Arrays;

public class leet945 {
    // Time Complexity : O(NlogN)
    // Space Complexity: O(1)
    // Greedy, only choose the least increment
    class Solution {
        public int minIncrementForUnique(int[] nums) {
            Arrays.sort(nums);
            // Array A == answer Array B
            // [1,1,2,2,3,7] != Answer [1,2,3,4,5,7]
            // 1 1 2 2 3 7
            //
            int res = 0;
            for(int i = 1; i < nums.length; i++) {
                if(nums[i] <= nums[i-1]) {
                    // 1 vs 1
                    // 2 vs 2
                    // equal or smaller
                    int diff = nums[i-1] - nums[i] + 1; //  1 - 1 + 1 = 1, so if equals its still +1
                    // if its already increased thats why previous will larger than current then we get the diff
                    // 3 vs 2,  3 - 2 + 1 = 2, so 2 will increase as 4 to prevent duplicate
                    res += diff;
                    nums[i] += diff;
                }
            }

            return res;
        }
    }
}
