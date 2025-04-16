import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static final String HPS = "HPS";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] B = new int[N];
        // H 주먹, P 보자기, S 가위

        for (int i = 0; i < N; i++) {
            B[i] = HPS.indexOf(br.readLine());
        }
        // end input

        if (N == 1) {
            System.out.println(1);
            System.exit(0);
        }

        // left[i][0] : 0번부터 i번까지 주먹냈을 때 이긴 횟수
        // left[i][1] : 0번부터 i번까지 보자기냈을 때 이긴 횟수
        // left[i][2] : 0번부터 i번까지 가위냈을 때 이긴 횟수
        int[][] left = new int[N][3];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                if (i != 0) {
                    left[i][j] = left[i - 1][j];
                }
                if (isWin(j, B[i])) {
                    left[i][j]++;
                }
            }
        }
        // right[i][0] : N-1번부터 i번까지 주먹냈을 때 이긴 횟수
        // right[i][1] : N-1번부터 i번까지 보자기냈을 때 이긴 횟수
        // right[i][2] : N-1번부터 i번까지 가위냈을 때 이긴 횟수
        int[][] right = new int[N][3];
        for (int i = N-1; i >= 0; i--) {
            for (int j = 0; j < 3; j++) {
                if (i != N-1) {
                    right[i][j] = right[i + 1][j];
                }
                if (isWin(j, B[i])) {
                    right[i][j]++;
                }
            }
        }

        int answer = 0;

        for (int i = 0; i < N-1; i++) {
            for (int j = 0; j < 3; j++) {
                for (int t = 0; t < 3; t++) {
                    // 0부터 i번까지는 j를 내고, i+1부터 N-1번까지는 t를 냈을 때 이길 수 있는 게임의 수
                    int temp = left[i][j] + right[i + 1][t];
                    answer = Math.max(answer, temp);
                }
            }
        }
        System.out.println(answer);
    }

    private static boolean isWin(int a, int b) {
        if (a - b == 1) {
            return true;
        }
        if (b - a == 1) {
            return false;
        }
        if (a == 0 && b == 2) {
            return true;
        }
        return false;
    }
}