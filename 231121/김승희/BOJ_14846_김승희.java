import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_14846 {
    static int N;
    static int[][] input;
    static int[][][] check;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        input = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] inArr = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                input[i][j] = Integer.parseInt(inArr[j]);
            }
        }

        // check[i][j] : (i, j)까지 누적 숫자의 개수
        check = new int[N][N][11];
        check[0][0][input[0][0]] = 1;
        for (int i = 1; i < N; i++) {
            check[i][0] = deepcopy(check[i - 1][0]);
            check[i][0][input[i][0]] ++;
            check[0][i] = deepcopy(check[0][i-1]);
            check[0][i][input[0][i]] ++;
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                check[i][j] = minus(plus(check[i-1][j], check[i][j-1]), check[i-1][j-1]);
                check[i][j][input[i][j]]++;
            }
        }

        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            String[] inArr = br.readLine().split(" ");
            int x1 = Integer.parseInt(inArr[0]) - 1;
            int y1 = Integer.parseInt(inArr[1]) - 1;
            int x2 = Integer.parseInt(inArr[2]) - 1;
            int y2 = Integer.parseInt(inArr[3]) - 1;
            int[] answer = check[x2][y2];
            if (x1 - 1 >= 0) {
                answer = minus(answer, check[x1 - 1][y2]);
            }
            if (y1 - 1 >= 0) {
                answer = minus(answer, check[x2][y1 - 1]);
            }
            if (x1 - 1 >= 0 && y1 - 1 >= 0) {
                answer = plus(answer, check[x1 - 1][y1 - 1]);
            }
            sb.append(count(answer)).append("\n");
        }
        System.out.println(sb);
    }

    static int count(int[] arr) {
        int cnt = 0;
        for (int i = 0; i < 11; i++) {
            if (arr[i] > 0) {
                cnt++;
            }
        }
        return cnt;
    }

    static int[] deepcopy(int[] arr) {
        int[] copy = new int[11];
        for (int i = 0; i < 11; i++) {
            copy[i] = arr[i];
        }
        return copy;
    }

    static int[] plus(int[] a, int[] b) {
        int[] arr = new int[11];
        for (int i = 0; i < 11; i++) {
            arr[i] = a[i] + b[i];
        }
        return arr;
    }
    static int[] minus(int[] a, int[] b) {
        int[] arr = new int[11];
        for (int i = 0; i < 11; i++) {
            arr[i] = a[i] - b[i];
        }
        return arr;
    }
}