import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static class Tuple{
        int i, j, d, acc;

        public Tuple(int i, int j, int d, int acc) {
            this.i = i;
            this.j = j;
            this.d = d;
            this.acc = acc;
        }
    }
    static int N, M, ANS;
    // 왼쪽 오른쪽 아래쪽
    static int[] di = {0, 0, 1};
    static int[] dj = {-1, 1, 0};
    static int[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        N = Integer.parseInt(inArr[0]);
        M = Integer.parseInt(inArr[1]);
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            inArr = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(inArr[j]);
            }
        } // end input

        ANS = Integer.MIN_VALUE;
        int[][][] dp = init();
        Queue<Tuple> Q = new ArrayDeque<>();
        Q.add(new Tuple(0, 0, 2, board[0][0]));
        while (!Q.isEmpty()) {
            Tuple tp = Q.poll();
            if (dp[tp.i][tp.j][tp.d] > tp.acc) continue;
            int chk = tp.d == 0 ? 1 : (tp.d == 1? 0 : -1) ;
            for (int k = 0; k < di.length; k++) {
                if (chk == k) continue;
                int ni = tp.i + di[k];
                int nj = tp.j + dj[k];
                if (ni < 0 || nj < 0 || ni >= N || nj >= M) continue;
                int temp = tp.acc + board[ni][nj];
                if (dp[ni][nj][k] >= temp) continue;
                dp[ni][nj][k] = temp;
                if (ni == N-1 && nj == M-1) {
                    ANS = Math.max(ANS, dp[ni][nj][k]);
                    continue;
                }
                Q.add(new Tuple(ni, nj, k, temp));
            }
        }
        System.out.println(ANS);
    }
    static int[][][] init(){
        int[][][] dp = new int[N][M][3];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = Integer.MIN_VALUE;
                }
            }
        }
        return dp;
    }
}