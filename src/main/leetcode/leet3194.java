import java.util.Arrays;

public class leet3194 {
    //3194. Minimum Average of Smallest and Largest Elements
    class Solution {
        public double minimumAverage(int[] nums) {

            Arrays.sort(nums);
            int n = nums.length;
            int operation = n/2;
            int start = 0, last = n-1;
            double minRes = Integer.MAX_VALUE;
            while(start < last) {
                minRes = Math.min(minRes, (double)(nums[start++]+nums[last--])/2);
                //System.out.println(minRes);
            }
            return minRes;
        }
    }
}
