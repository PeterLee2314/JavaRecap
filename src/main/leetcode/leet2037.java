import java.util.Arrays;

public class leet2037 {
    class Solution {
        public int minMovesToSeat(int[] seats, int[] students) {
            // can have a seat allow more than 1 studnet to sit
            // Optimal solution
        /*
            Sort first
            We get the shortest distance for seat with nearest students
            1 3 5 vs 2 4 7 by greedy
            (2-1) + (4-3) + (7-5)
            2 2 6 6 vs 1 2 3 6
            1 + 0 + 3 + 0
        */
            Arrays.sort(seats);
            Arrays.sort(students);
            int n = seats.length, res = 0; // guarantee seat.length == students.length
            for(int i = 0; i < n; i++) {
                res += Math.abs(seats[i] - students[i]);
            }
            return res;
        }
    }
}
