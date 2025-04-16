import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        int N = Integer.parseInt(inArr[0]);
        int K = Integer.parseInt(inArr[1]);
        String crystal = "-" + br.readLine();
        // dp[i][j][0] : i번 수정이 생겼을 때, j번 움직여서 왼쪽 샘터에 있는데 그 때, 수집한 최대 수정 개수
        // dp[i][j][1] : i번 수정이 생겼을 때, j번 움직여서 오른쪽 샘터에 있는데 그 때, 수집한 최대 수정 개수
        int[][][] dp = new int[N + 1][K + 1][2];

        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < K + 1; j++) {
                dp[i][j] = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
            }
        }
        dp[0][0][0] = 0;

        for (int i = 1; i < N + 1; i++) {
            for (int j = 0; j < K + 1; j++) {
                if (dp[i - 1][j][0] != Integer.MIN_VALUE) {
                    dp[i][j][0] = dp[i - 1][j][0];
                    // i번 수정이 왼쪽에 생겼다.
                    if (crystal.charAt(i) == 'L') {
                        dp[i][j][0]++;
                    }
                }
                if (dp[i - 1][j][1] != Integer.MIN_VALUE) {
                    dp[i][j][1] = dp[i - 1][j][1];
                    // i번 수정이 오른쪽에 생겼다.
                    if(crystal.charAt(i) == 'R') {
                        dp[i][j][1]++;
                    }
                }

                if (j - 1 >= 0 && dp[i - 1][j - 1][1] != Integer.MIN_VALUE) {
                    int temp = dp[i - 1][j - 1][1];
                    // i번 수정이 왼쪽에 생겼다.
                    if (crystal.charAt(i) == 'L' ) {
                        temp++;
                    }
                    dp[i][j][0] = Math.max(dp[i][j][0], temp);
                }
                if (j - 1 >= 0 && dp[i - 1][j - 1][0] != Integer.MIN_VALUE) {
                    int temp = dp[i - 1][j - 1][0];
                    // i번 수정이 오른쪽에 생겼다.
                    if(crystal.charAt(i) == 'R') {
                        temp++;
                    }
                    dp[i][j][1] = Math.max(temp, dp[i][j][1]);
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < K + 1; j++) {
                answer = Math.max(answer, Math.max(dp[i][j][0], dp[i][j][1]));
            }
        }
        System.out.println(answer);
    }
}