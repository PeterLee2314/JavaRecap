import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class leet2285 {
    /*
    class Solution {
        public long maximumImportance(int n, int[][] roads) {
            // assign value from 1 to n to each road,
            // in-degree + out-degree , sort them by this
            Map<Integer,Integer> map = new HashMap<>();
            for(int i = 0; i < roads.length; i++) {
                map.put(roads[i][0], map.getOrDefault(roads[i][0],0) + 1);
                map.put(roads[i][1], map.getOrDefault(roads[i][1],0) + 1);
            }
            long res = 0;
            //System.out.println(map);
            List<Pair<Integer,Integer>> pairList = new ArrayList<>(); // sort the roads by getvalue
            for(Map.Entry<Integer,Integer> entry : map.entrySet()) {
                pairList.add(new Pair(entry.getKey(), entry.getValue()));
            }
            Collections.sort(pairList, (a,b) -> b.getValue()-a.getValue());
            for(Pair<Integer,Integer> p : pairList) {
                map.put(p.getKey(),n--);
            }
            //System.out.println(map);
            for(int i = 0; i < roads.length; i++) {
                res += map.get(roads[i][0]) + map.get(roads[i][1]);
            }
            return res;
        }
    }
    */
    class Solution {
        public long maximumImportance(int n, int[][] roads) {
            long[] degree = new long[n]; // total degree
            for(int [] road : roads) {
                degree[road[0]]++;
                degree[road[1]]++;
            }
            Arrays.sort(degree);
            long res = 0;
            int start = 1; // 1 to n
            for(long value : degree) {
                res += start * value;
                start++;
            }
            return res;
        }
    }
}
