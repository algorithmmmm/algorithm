import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SWEA_0_1_Knapsack {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int N, K;
    static int[] volume, cost;

    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());    // T : testcase의 수
        for(int t = 1; t < T + 1; t++){
            input();
            output(t, knapsack());
        }
        System.out.println(sb.toString());
    }
    static void input() throws IOException {
        String[] inArr = br.readLine().split(" ");
        N = Integer.parseInt(inArr[0]);         // N : 물건의 개수
        K = Integer.parseInt(inArr[1]);         // K : 가방의 최대 부피
        volume = new int[N + 1];                   // volume[i] : i번 물건의 부피
        cost = new int[N + 1];                     // cost[i] : i번 물건의 가치
        for (int i = 1; i < N + 1; i++) {
            inArr = br.readLine().split(" ");
            volume[i] = Integer.parseInt(inArr[0]);
            cost[i] = Integer.parseInt(inArr[1]);
        }
    }

    static int knapsack() {
        int[][] dp = new int[N + 1][K + 1];     // dp[i][j] : i번 물건까지 고려하고 부피의 최대를 j라고 했을 때 최대 부피

        for(int n = 1; n < N+1; n++){
            for(int k = 1; k < K + 1; k++){
                if(volume[n] == k){                                 // n번 물건의 부피가 최대 고려 부피인 k이면 
                    dp[n][k] = Math.max(dp[n-1][k], cost[n]);       // n번 물건을 고려하지 않는 경우와 고려하는 경우 중 큰 값
                }else if(volume[n] < k){                            // n번 물건의 부피가 최대 고려 부피 k보다 작으면
                    dp[n][k] = Math.max(dp[n-1][k], dp[n-1][k-volume[n]] + cost[n]);    // n번 물건을 고려하지 않는 경우와 고려하는 경우 중 큰 값
                }else{                                              // n번 물건의 부피가 최대 고려 부피 k보다 크면
                    dp[n][k] = dp[n - 1][k];                        // n번 물건을 고려하지 않아야 함
                }
            }
        }
        return dp[N][K];
    }

    static void output(int t, int answer){
        sb.append("#").append(t).append(" ").append(answer).append("\n");
    }
}
