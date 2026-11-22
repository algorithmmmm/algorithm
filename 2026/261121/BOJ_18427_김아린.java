import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N + 1][H + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= N; i++) {
            // 블록 안고르는 경우
            for (int j= 0; j <= H; j++) {
                dp[i][j] = dp[i - 1][j];
            }

            // 블록 고르는 경우
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                int block = Integer.parseInt(st.nextToken());
                for (int j = block; j <= H; j++) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j - block]) % 10007;
                }
            }
        }

        System.out.println(dp[N][H]);
    }
}
