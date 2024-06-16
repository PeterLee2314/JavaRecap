import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

// Hard-Medium
// BFS + Dijkstra
public class leet2812 {
    class Solution {
        public int maximumSafenessFactor(List<List<Integer>> grid) {
            // |x1-x2| + |y1-y2|
            // safeness factor = the path with nearest Manhattan distance to the thief
            // calculate all safeness first, then use BFS to pick the lowest
            Queue<int[]> q = new LinkedList<>();
            int[] dir = {1, 0, -1, 0, 1};
            int n = grid.size(), m = grid.get(0).size();
            // get all cell with 1 first, then calculate all safeness factor
            for(int i = 0; i < n; i++) {
                for(int j = 0; j <m; j++) {
                    if(grid.get(i).get(j) == 1){
                        q.add(new int[] {i,j});
                    }
                    // 0,3
                }
            }
            while(!q.isEmpty()) {
                int[] cur = q.poll();
                for(int i = 0; i < 4; i++) {
                    int x = cur[0] + dir[i];
                    int y = cur[1] + dir[i+1];
                    if(Math.min(x,y) >= 0 && Math.max(x,y) < n && grid.get(x).get(y) == 0) {
                        // we get +1 , because we can expand this value further which SAME as Manhattan distance
                        int curValue = grid.get(cur[0]).get(cur[1]);
                        //System.out.println("curValue:" + curValue + " x:" + x + " y:" + y);
                        grid.get(x).set(y,curValue + 1);
                        q.add(new int[]{x,y});
                    }
                }
            }

            for(int i = 0; i < n; i++) {
                for(int j = 0; j <m; j++) {
                    System.out.print(grid.get(i).get(j));
                }
                System.out.println();
                // the maximum safeness is safeness -1 , because the current value assume its not the closest cell
                // so we can find the minimum safeness among the maximum as answer
            }


            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (b[0]-a[0]));
            // what to store in pq? (safeness, i, j)
            pq.add(new int[] {grid.get(0).get(0), 0, 0});
            while(pq.peek()[1] < n-1 || pq.peek()[2] < m-1) {
                // run BFS
                //System.out.println(pq.peek()[1]+ " " + pq.peek()[2]);
                int[] cur = pq.poll();
                for(int i = 0; i < 4; i++) {
                    int x = cur[1] + dir[i];
                    int y = cur[2] + dir[i+1];

                    //grid.get(x).get(y) > 0 (whether visited)
                    if(Math.min(x,y) >= 0 && Math.max(x,y) < n && grid.get(x).get(y) > 0) {
                        // In here, the min meaning i will pick the minimum safeness value
                        // However, the pq is sorted from large to small, meaning everytime i choose the maximum value
                        // Therefore, Math.min pick the Current Maximum value vs Maximum Value inside the pq
                        pq.add(new int[] {Math.min(cur[0],grid.get(x).get(y)) , x,y});
                        //System.out.println("curValue:" + pq.peek()[0] + " x:" + x + " y:" + y);
                        grid.get(x).set(y,-1);
                    }
                    //System.out.println("x:"+x +" y:"+y);
                }
            }
            // 1,1 vs 0,2
            // |1-0| + |1-2| = 2
            return pq.peek()[0]-1;

        }

    }
}
