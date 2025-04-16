class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int max_alp = 0;
        int max_cop = 0;
        
        for(int[] problem : problems) { //문제 내 최대 알고력과 코딩력 구하기 
            max_alp = Math.max(max_alp, problem[0]);
            max_cop = Math.max(max_cop, problem[1]);
        }
        
        int[][] dp = new int[max_alp + 1][max_cop + 1];
        for(int i = 0; i < max_alp + 1; i++) { //최대로 초기화
            for(int j = 0; j < max_cop + 1; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        
        alp = Math.min(alp, max_alp); //현재가 더 큰 경우도 있으니 바꿔주기
        cop = Math.min(cop, max_cop);
        dp[alp][cop] = 0; //현재 능력으로는 시간 안걸림
        
        for(int i = alp; i < max_alp + 1; i++) { //최대로 초기화
            for(int j = cop; j < max_cop + 1; j++) {
                //알고력1 얻기 위해 시간 1씀 
                if(i + 1 <= max_alp) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                }
                //코딩력1 얻기 위해 시간 1씀 
                if(j + 1 <= max_cop) {
                    dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
                }
                //문제 풀어서 높임
                for(int[] problem : problems) { //문제 내 최대 알고력과 코딩력 구하기 
                    int alp_req = problem[0];
                    int cop_req = problem[1];
                    int alp_rwd = problem[2];
                    int cop_rwd = problem[3];
                    int cost = problem[4];
                    
                    if(i >= alp_req && j >= cop_req) { //문제를 풀 수 있음
                        int next_alp = Math.min(i + alp_rwd, max_alp);
                        int next_cop = Math.min(j + cop_rwd, max_cop);
                        
                        dp[next_alp][next_cop] = Math.min(dp[next_alp][next_cop], dp[i][j] + cost);
                    }
                }
            }
        }
        
        return dp[max_alp][max_cop];
    }
}