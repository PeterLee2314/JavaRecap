import java.util.HashSet;

public class leet633 {
    // Time Complexity O(sqrt(N)) -> O(N)
    // loop from 0 to sqrt(c) , eg sqrt(4) => 2 , so the only possible case from 0 to sqrt(c)
    class Solution {
        public boolean judgeSquareSum(int c) {
            HashSet<Integer> set = new HashSet<>();
            // 0
            // 1
            // 2
            // 3
            // 4 -> 2^2 + 0^2
            for(int i = 0; i <= Math.sqrt(c); i++) {
                set.add(i*i);
                if(set.contains(c - i*i)) {
                    return true;
                }
            }
            return false;
        }
    }
    // Two pointer solution (O(N))
    class Solution2 {
        public boolean judgeSquareSum(int c) {
            long left = 0, right = (long) Math.sqrt(c); // its impossible to have value > sqrt(c)
            while(left <= right) {
                long cur = left * left + right * right;
                if(cur == c) {
                    return true;
                }else if(cur > c) {
                    right--; // too big
                }else if(cur < c) {
                    // too small
                    left++;
                }
            }
            return false;
        }
    }
}
