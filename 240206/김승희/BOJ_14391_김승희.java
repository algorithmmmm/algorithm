import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_14391 {
    static int N, M, Ans;
    static String[] Paper;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        N = Integer.parseInt(inArr[0]);
        M = Integer.parseInt(inArr[1]);
        Paper = new String[N];
        for (int i = 0; i < N; i++) {
            Paper[i] = br.readLine();
        }
        // end input
        Ans = 0;
        recursion(0, 0, 0, new boolean[N][M]);
        System.out.println(Ans);
    }

    static void recursion(int I, int J, int ans, boolean[][] visit) {
        // 세로 * 가로 i * 1
        for (int i = 1; i <= N; i++) {
            if (i + I > N) continue;
            boolean[][] arr = deepcopy(visit);
            StringBuilder sb = new StringBuilder();
            for (int t = 0; t < i; t++) {
                sb.append(Paper[I+t].charAt(J));
                arr[I + t][J] = true;
            }
            int nans = ans + Integer.parseInt(sb.toString());
            boolean end = true;
            for (int p = 0; p < N; p++) {
                for (int q = 0; q < M; q++) {
                    if (arr[p][q]) continue;
                    end = false;
                    recursion(p, q, nans, deepcopy(arr));
                }
            }
            if (end) {
                Ans = Math.max(Ans, nans);
            }
        }
        // 세로 * 가로 1 * j
        for (int j = 1; j <= M; j++) {
            if (j + J > M) continue;
            boolean[][] arr = deepcopy(visit);
            StringBuilder sb = new StringBuilder();
            for (int t = 0; t < j; t++) {
                sb.append(Paper[I].charAt(J + t));
                arr[I][J + t] = true;
            }
            int nans = ans + Integer.parseInt(sb.toString());
            boolean end = true;
            for (int p = 0; p < N; p++) {
                for (int q = 0; q < M; q++) {
                    if (arr[p][q]) continue;
                    end = false;
                    recursion(p, q, nans, deepcopy(arr));
                }
            }
            if (end) {
                Ans = Math.max(Ans, nans);
            }
        }
    }

    static boolean[][] deepcopy(boolean[][] arr) {
        boolean[][] copy = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copy[i][j] = arr[i][j];
            }
        }
        return copy;
    }
}
