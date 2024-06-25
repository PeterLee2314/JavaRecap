import java.util.HashMap;
import java.util.Map;

// Use Prefix, sliding Window is not allow in here, because of negative value
// In logic of brute force what we are searching for?
/*
we are searching for whether the current sub-array is == k
and we know sum(i,j) = sum(0,j) - sum(0,i) so its a prefix sum
SO we are looking for a sub-array sum(0,j) - sum(0,i) == k , so that that sub-array is exactly k
On the contrary, we are looking for sum(0,j) - k == sum(0,i) , because we are looking for whether
eg [1,2,3,4] is our prefix and we are looking k == 2
prefix1 - k which is 2 - 2 == 0, which is exactly the array we are looking for , so +1. Before we already map.put(0,1) in case a perfect match array eg [1,1] k =2
Similarly we looking prefix2 , 3 - 2 == 1, which is prefix0 ,so its answer + map.get(1)
 */

public class leet560 {
    class Solution {
        public int subarraySum(int[] nums, int k) {
            Map<Integer,Integer> map = new HashMap<>();
            int res = 0, cur = 0;
            map.put(0,1);
            for(int i = 0; i < nums.length; i++) {
                cur += nums[i];
                res += map.getOrDefault(cur-k, 0);
                map.put(cur, map.getOrDefault(cur,0) + 1);

            }
            return res;
        }
    }
}
