import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] input = new int[N][3];
        for (int i = 0; i < N; i++) {
            String[] inArr = br.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                input[i][j] = Integer.parseInt(inArr[j]);
            }
        }       // end input

        int[][] maxDP = new int[N][3];      // maxDP[i][j] : i줄에서 j번째 숫자를 선택했을 때, 최댓값
        int[][] minDP = new int[N][3];      // minDP[i][j] : i줄에서 j번째 숫자를 선택했을 때, 최솟값

        for (int j = 0; j < 3; j++) {
            maxDP[0][j] = input[0][j];
            minDP[0][j] = input[0][j];
        }       // init maxDP, minDP

        // j == 0 : 0과 1에서 올 수 있다.
        // j == 1 : 0과 1, 2에서 올 수 있다.
        // j == 2 : 1과 2에서 올 수 있다.
        for (int i = 1; i < N; i++) {
            // maxDP
            maxDP[i][0] = Math.max(maxDP[i - 1][0], maxDP[i - 1][1]) + input[i][0];
            int temp = Math.max(maxDP[i - 1][0], maxDP[i - 1][1]);
            maxDP[i][1] = Math.max(temp, maxDP[i - 1][2]) + input[i][1];
            maxDP[i][2] = Math.max(maxDP[i - 1][1], maxDP[i - 1][2]) + input[i][2];

            // minDP
            minDP[i][0] = Math.min(minDP[i - 1][0], minDP[i - 1][1]) + input[i][0];
            temp = Math.min(minDP[i - 1][0], minDP[i - 1][1]);
            minDP[i][1] = Math.min(temp, minDP[i - 1][2]) + input[i][1];
            minDP[i][2] = Math.min(minDP[i - 1][1], minDP[i - 1][2]) + input[i][2];
        }

        int maxValue = 0;
        int minValue = Integer.MAX_VALUE;
        for (int j = 0; j < 3; j++) {
            maxValue = Math.max(maxValue, maxDP[N - 1][j]);
            minValue = Math.min(minValue, minDP[N - 1][j]);
        }
        System.out.println(maxValue + " " + minValue);
    }
}