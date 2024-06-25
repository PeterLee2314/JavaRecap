import com.mysql.cj.conf.ConnectionUrlParser;

import java.util.*;

public class leet826 {
    class Solution {
        public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
            // sort by profit
            // how to get profit array according to woerk
            TreeMap<Integer, Integer> tm = new TreeMap<>();  // changed from Map to TreeMap
            // we only store the largest (greedy)
            for(int i = 0; i < difficulty.length; i++) {
                if(tm.get(difficulty[i]) == null || tm.get(difficulty[i]) < profit[i]) {
                    tm.put(difficulty[i], profit[i]);
                }
            }
            // greedy 2 : if lower diff have higher profit, we take it
            int max = 0;
            for(int key : tm.keySet()) {
                //sorted
                max = Math.max(max, tm.get(key));
                tm.put(key , max);
            }
            //System.out.println(tm);


            int res = 0;
            // use floorEntry or floorKey
            Map.Entry<Integer, Integer> entry = null;
            for(int i = 0; i <worker.length; i++) {
                if(tm.containsKey(worker[i])) {
                    res += tm.get(worker[i]);
                }else {
                    //go down
                    entry = tm.floorEntry(worker[i]); // cannot find symbol, because the initialize is Map, Map dk TreeMap method
                    if(entry != null) {
                        res += entry.getValue();
                    }
                }
            }
            /*
            // floorKey , floorKey and get could be null, so use Integer
            for(int i = 0; i <worker.length; i++) {
                Integer key = tm.floorKey(worker[i]);
                res += key != null && tm.get(key) != null ?  tm.get(key) : 0;
            }

             */
            return res;
        }
    }
    /*
    class Solution2 {
        public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
            List<Pair<Integer, Integer>> jobs = new ArrayList<>();
            int n = profit.length, res = 0, j = 0, best = 0;
            // List allow duplicate
            for(int i = 0; i < n; i++) {
                jobs.add(new Pair<>(difficulty[i], profit[i]));
            }
            Collections.sort(jobs, (a, b) -> (a.getKey() - b.getKey())); // sort ascendingly (Comparator.comparing(Pair::getKey))
            Arrays.sort(worker); // we get from lowest to highest , so we can get the best and pass above

            for(int ability: worker) {
                while(j < n && jobs.get(j).getKey() <= ability) {
                    best = Math.max(best, jobs.get(j++).getValue());
                }
                res += best;

            }
            return res;
        }
    }
     */
}
