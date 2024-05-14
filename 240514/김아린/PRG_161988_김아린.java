class Solution {
    public long solution(int[] sequence) {
        long[][] dp = new long[sequence.length][2]; //i까지의 합의 최대값
        
        dp[0][0] = sequence[0] * 1; //[1, -1, 1, -1 …]
        dp[0][1] = sequence[0] * -1; //[-1, 1, -1, 1 …]
        
        long answer = Math.max(dp[0][0], dp[0][1]);
        for(int i = 1; i < sequence.length; i++) {
            if(i % 2 == 0) {
                dp[i][0] = sequence[i] * 1;
                dp[i][1] = sequence[i] * -1;
            } else {
                dp[i][0] = sequence[i] * -1;
                dp[i][1] = sequence[i] * 1;
            }
            
            if(dp[i - 1][0] + dp[i][0] > dp[i][0]) { //새로 더했을 때 크면 갱신
                dp[i][0] = dp[i - 1][0] + dp[i][0];
            }
            
            if(dp[i - 1][1] + dp[i][1] > dp[i][1]) {
                dp[i][1] = dp[i - 1][1] + dp[i][1];
            }
            
            answer = Math.max(answer, Math.max(dp[i][0], dp[i][1]));
        }
        
        return answer;
    }
}