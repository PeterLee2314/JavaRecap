public class leet1482 {
    class Solution {
        public int minDays(int[] bloomDay, int m, int k) {
            if(bloomDay.length < (long) m*k) {
                return -1;
            }
            int minDay = 0, maxDay = 0;
            for(int bloom : bloomDay) {
                minDay = Math.min(minDay, bloom);
                maxDay = Math.max(maxDay, bloom);
            }
            // need m*k flower
            // k adjacent flower -> 3 flower in 1 group -> then its 1 m

            // logic: at each day, the max m we can form
            // check adjacent (1 ~ k)
            // if current flower is bloom, we check current + k - 1 is it all bloom

            // when at day X, we already form
            // what if we mark the bloom as 1, no matter day 7,8,9 if it already pass?
            // what if we binary search the day and loop? then we can achieve o(nlogn)
            int res = Integer.MAX_VALUE, n = bloomDay.length;
            while(minDay <= maxDay) {
                int midDay = minDay + (maxDay-minDay)/2;
                //System.out.println(midDay);
                // check is current day can form m
                int adjacentCount = 0, currentBouquet = 0;
                for(int i = 0; i < n; i++) {
                    // loop how many bloom can form
                    //System.out.println(bloomDay[i] + " midDay:" +midDay);
                    if(bloomDay[i] <= midDay) {
                        adjacentCount++;
                    }else {
                        adjacentCount = 0;
                    }
                    //System.out.println("adjacentCount:" + adjacentCount + " currentBouquet " + currentBouquet);
                    if(adjacentCount!= 0 && adjacentCount % k == 0) {
                        currentBouquet++;
                        adjacentCount = 0; // reset
                    }

                }
            /*
            for(int j = 0; j < n; j++) {
                System.out.print(isBloom[j] + " ");
            }
            */
                //System.out.println();
                //System.out.println("res:" + res+ " m*k:" + m*k + " currentBouquet:"+currentBouquet);
                if(currentBouquet >= m) {
                    res = Math.min(res, midDay);
                    maxDay = midDay-1;
                }else {
                    minDay = midDay + 1;
                }


            }
            return res == Integer.MAX_VALUE? -1 : res;
        }
    }

    class Solution2 {
        public int minDays(int[] bloomDay, int m, int k) {
            // the reason why minDay loop till maxDay and no answer, because m*k is exceed 4byte int , which need long
            if(bloomDay.length < (long) m*k) {
                return -1;
            }
            int minDay = 1, maxDay = 1000000000;
            int n = bloomDay.length;
            while(minDay < maxDay) {
                int midDay = minDay + (maxDay-minDay)/2, adjacentCount = 0, currentBouquet = 0;
                for(int i = 0; i < n; i++) {
                    if(bloomDay[i] > midDay) {
                        adjacentCount = 0;
                    }else{
                        adjacentCount++;
                    }
                    if(adjacentCount >= k) {
                        currentBouquet++;
                        adjacentCount = 0;
                    }

                }
                if(currentBouquet >= m) {
                    maxDay = midDay;
                }else {
                    minDay = midDay + 1;
                }


            }
            return minDay;
        }
    }
}
