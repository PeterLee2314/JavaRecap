import java.util.Deque;
import java.util.LinkedList;
import java.util.TreeMap;

// Sliding Window Technique and 2 Dequeue or TreeMap (easy to get min max)
public class leet1438 {
    // Dequeue
    class Solution {
        public int longestSubarray(int[] nums, int limit) {
            Deque<Integer> maxDeque = new LinkedList<>(); // maintain the order that is max from small
            Deque<Integer> minDeque = new LinkedList<>(); // maintain oreder that is min from max
        /*
        max [8], min [8]
        [8], [2] > limit => [8, 2], [2]

        */
            int res = 1, start = 0;
            for(int end = 0; end < nums.length; end++) {
                while(!maxDeque.isEmpty() && maxDeque.peekLast() < nums[end]) {
                    maxDeque.removeLast();
                }
                maxDeque.addLast(nums[end]);

                while(!minDeque.isEmpty() && minDeque.peekLast() > nums[end]) {
                    minDeque.removeLast();
                }
                minDeque.addLast(nums[end]);
                System.out.println("before min:"+minDeque);
                System.out.println("before max:"+maxDeque);
                while (maxDeque.peekFirst() - minDeque.peekFirst() > limit) {
                    if (maxDeque.peekFirst() == nums[start]) maxDeque.pollFirst();
                    if (minDeque.peekFirst() == nums[start]) minDeque.pollFirst();
                    start++;
                }
                System.out.println("min:"+minDeque);
                System.out.println("max:"+maxDeque);
                res = Math.max(res, end-start+1);
            }
            return res;
        }
    }

    //TreeSet, keep track of maximum size of window  O(NlogN)
    class Solution2 {
        public int longestSubarray(int[] A, int limit) {
            int i = 0, j;
            TreeMap<Integer, Integer> m = new TreeMap<>(); // firstEntry () smallest value , lastEntry largest value
            for (j = 0; j < A.length; j++) {
                m.put(A[j], 1 + m.getOrDefault(A[j], 0));
                if (m.lastEntry().getKey() - m.firstEntry().getKey() > limit) {
                    m.put(A[i], m.get(A[i]) - 1);
                    if (m.get(A[i]) == 0)
                        m.remove(A[i]);
                    i++;
                }
                //System.out.println(m + " i:"+i + " j:"+j);
            }
            return j - i; // the maximum size of the window, because we only shrink the window when not valid
            // when under [2,2,2] its the maximum size of the window, it will not increase
        }
    }
}
