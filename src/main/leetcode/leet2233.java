import java.util.PriorityQueue;

public class leet2233 {
    class Solution {
        public int maximumProduct(int[] nums, int k) {
            int MOD = (int)Math.pow(10,9) + 7;
            // we always add the min number
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for(int num : nums) {
                pq.add(num);
            }
            while(k != 0) {
                int top = pq.poll();
                pq.add(top+1);
                k--;
            }
            long res = 1;
            while(!pq.isEmpty()) {
                res = (res * pq.poll()) % MOD;
            }
            return (int)res;
        }
    }
/*
Prove:
4 5 6 7 = 840
4 6 6 7 = 840 + 168 = (4*5*6*7) + (4*6*7) = p + p/5
to maximize p + p/5 we want to increase p/5,
to maximize p/5, we need to focus on the 5(denominator), so which want 5 to be as smaller as possible
*/
// take Math.min(k,nums[i] - nums[i-1] +1) as the diff rather than plus 1
class Solution2 {
    public int maximumProduct(int[] nums, int k) {
        int MOD = (int)Math.pow(10,9) + 7;
        if(nums.length == 1) {
            return nums[0] + k;
        }
        // we always add the min number
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int num : nums) {
            pq.add(num);
        }
        while(k != 0) {
            int top = pq.poll();
            int second = pq.peek();
            int diff = Math.min(k, second-top+1);
            top+=diff;
            pq.add(top);
            //pq.add(top+1);

            k-=diff;
        }
        long res = 1;
        while(!pq.isEmpty()) {
            res = (res * pq.poll()) % MOD;
        }
        return (int)res;
    }
}
}
