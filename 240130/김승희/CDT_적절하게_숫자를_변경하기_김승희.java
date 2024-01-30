import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        int N = Integer.parseInt(inArr[0]);
        int M = Integer.parseInt(inArr[1]);
        M = Math.min(N, M);
        int[] A = new int[N];
        inArr = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(inArr[i]);
        }
        // end input

        // dp[i][j][k] : i번 숫자까지 고려했고, j번 바뀌었고, 마지막 숫자가 k일 때, 최대 유사도
        int[][][] dp = new int[N][M + 1][5];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M + 1; j++) {
                Arrays.fill(dp[i][j], Integer.MIN_VALUE);
            }
        }
        for (int k = 1; k < 5; k++) {
            dp[0][0][k] = 0;
        }
        dp[0][0][A[0]] = 1;
        
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M + 1; j++) {
                for (int k = 1; k < 5; k++) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (j-1 >= 0) {
                        for (int t = 1; t < 5; t++) {
                            if (t == k) continue;
                            dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - 1][t]);
                        }
                    }
                    if (A[i] == k && dp[i][j][k] != Integer.MIN_VALUE) {
                        dp[i][j][k]++;
                    }
                }
            }
        }

        int answer = 0;
        for (int j = 0; j < M + 1; j++) {
            for (int k = 1; k < 5; k++) {
                answer = Math.max(answer, dp[N - 1][j][k]);
            }
        }
        System.out.println(answer);
    }
}