import java.util.Arrays;

public class leet990 {
    // O(nlogn) can improve if just simply two loop , first loop union, second loop find conflict
    class Solution {
        public boolean equationsPossible(String[] equations) {
            // find a and b which match all equations
        /*
        a==b , b!=a (false) if a == b, meaning 1 == 1, but 1 != 1 is false
        */
            // only != and == exist , a - z
            // if a == b, then b == c is also true, However, if a == b, b!= c while a == c (Conflict)
            // search all == first => so its Union in this question
            // if !=, we will recursively back to find all ==
            boolean res = true;
            Union u = new Union();
            // we union all first, then find conflict by every value , b != a then a and b shouldn't in same union group
            int n = equations.length;
            // sort by == first
            Arrays.sort(equations, (a, b) -> b.indexOf("==")); // sort by ==, so we can do all == first and find conflict
            for(int i = 0; i < n; i++) {
                int firstChar = equations[i].charAt(0) - 'a';
                int secondChar = equations[i].charAt(3) - 'a';
                if(equations[i].indexOf("==") != -1) {
                    // ==
                    u.union(firstChar, secondChar);
                } else {
                    // !=
                    if(u.find(firstChar) == u.find(secondChar)) {
                        return false;
                    }
                }
            }


            return res;
        }

    }
    class Union {
        int[] parent = new int[26]; // a ~ z unique set

        Union() {
            for(int i = 0; i <26; i++ ) {
                parent[i] = i;
            }
        }
        public int find(int i) {
            if(parent[i] == i) {
                return i;
            }

            return find(parent[i]);
        }

        public void union(int i, int j) {
            int leaderA = find(i);
            int leaderB = find(j);

            // either A or B become leader of whole
            parent[leaderA] = leaderB; // now leaderB become leaderA
        }
    }

    // O(N+N)
    class Solution2 {
        public boolean equationsPossible(String[] equations) {
            boolean res = true;
            Union u = new Union();
            int n = equations.length;
            for(int i = 0; i < n; i++) {
                int firstChar = equations[i].charAt(0) - 'a';
                int secondChar = equations[i].charAt(3) - 'a';
                if(equations[i].charAt(1) == '=') {
                    u.union(firstChar, secondChar);
                }
            }

            for(int i = 0; i < n; i++) {
                int firstChar = equations[i].charAt(0) - 'a';
                int secondChar = equations[i].charAt(3) - 'a';
                if(equations[i].charAt(1) == '!' && u.find(firstChar) == u.find(secondChar)) {
                    return false;
                }
            }
            return res;
        }

    }

    // O(n+n), no class version
    class Solution3 {
        int[] u = new int[26];
        public boolean equationsPossible(String[] equations) {
            int n = equations.length;

            for(int i = 0; i < 26; i++) u[i] = i;

            for(int i = 0; i < n; i++) {
                int firstChar = equations[i].charAt(0) - 'a';
                int secondChar = equations[i].charAt(3) - 'a';
                if(equations[i].charAt(1) == '=') {
                    u[find(firstChar)] = find(secondChar); // we can union here rather than a class method
                }
            }

            for(int i = 0; i < n; i++) {
                int firstChar = equations[i].charAt(0) - 'a';
                int secondChar = equations[i].charAt(3) - 'a';
                if(equations[i].charAt(1) == '!' && find(firstChar) == find(secondChar)) {
                    return false;
                }
            }
            return true;
        }
        public int find(int i) {
            if(u[i] != i) {
                u[i] = find(u[i]);
            }
            return u[i];
        }

    }
}
