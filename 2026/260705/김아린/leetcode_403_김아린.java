class Solution {
    public boolean canCross(int[] stones) {
        int n = stones.length;

        boolean[][] dp = new boolean[n][n + 1]; // i번째 돌에 k만큼 점프했을 때 도착 가능한지
        dp[0][0] = true;

        Map<Integer, Integer> map = new HashMap<>(); // 숫자-인덱스
        for(int i = 0; i < n; i++) {
            map.put(stones[i], i);
        }

        for(int i = 0; i < n; i++) {
            for(int k = 0; k <= n; k++) {
                if(!dp[i][k]) continue; // 못감

                for(int j = k - 1; j <= k + 1; j++) { // 이동 가능한 거리
                    if(0 <= j && j <= n) {
                        int next = stones[i] + j; // 이동 가능한 다음 숫자
                        int idx = map.getOrDefault(next, -1);

                        if(idx >= 0) { // 존재
                            if(idx == n - 1) return true; // 도착
                            dp[idx][j] = true;
                        }
                    }
                }
            }
        }

        for(int k = 0; k <= n; k++) {
            if(dp[n - 1][k]) return true;
        }

        return false;
    }
}