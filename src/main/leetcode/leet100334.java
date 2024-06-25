public class leet100334 {
    class Solution {
        public int minimumArea(int[][] grid) {
            int res = 0, width = 0, height = 0;
            int n = grid.length,m = grid[0].length;
            // 4 direction left , top , bottom, right
            int left = m-1, top = n-1, bottom = 0, right = 0;
            // think reversely, left wnat LeftMost so start from right .. similarly for other direction
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    if(grid[i][j] == 1) {
                        left = Math.min(left,j);
                        right = Math.max(right,j);
                        top = Math.min(top,i);
                        bottom = Math.max(bottom,i);
                    }

                }
            }
            return (right-left+1) * (bottom-top+1);
            // why this formula? because right is j bigger left become smaller,
            // bottom is i bigger, top is i smaller
        }
    }
}
