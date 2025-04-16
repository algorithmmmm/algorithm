import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class BOJ_18430 {
    static class Tuple{
        int x, y, k,acc;

        public Tuple(int x, int y, int k, int acc) {
            this.x = x;
            this.y = y;
            this.k = k;
            this.acc =acc;
        }
    }
    static int N, M, ANS;
    static int[][] board;
    static int[][] di = {
            {0, 1},
            {0, -1},
            {-1, 0},
            {1, 0}
    };
    static int[][] dj = {
            {-1, 0},
            {-1, 0},
            {0, 1},
            {0, 1}
    };
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
        }
        // end input

        ANS = Integer.MIN_VALUE;
        recursion(0, 0, new boolean[N][M], new boolean[N][M],0);
        System.out.println(ANS);
    }

    static boolean[][] deepcopy(boolean[][] arr) {
        boolean[][] copy = new boolean[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                copy[i][j] = arr[i][j];
            }
        }
        return copy;
    }

    static void recursion(int i, int j, boolean[][] chk, boolean[][] visit, int acc) {
        if (chk[i][j]) return;
        visit[i][j] = true;
        for (int k = 0; k < 5; k++) {
            boolean[][] nchk = deepcopy(chk);
            boolean[][] nvisit = deepcopy(visit);
            int nacc = acc;
            if (k != 4) {
                int ai = di[k][0] + i;
                int aj = dj[k][0] + j;
                int bi = di[k][1] + i;
                int bj = dj[k][1] + j;
                if (ai < 0 || aj < 0 || bi < 0 || bj < 0 ||
                        ai >= N || aj >= M || bi >= N || bj >= M) continue;
                if (nchk[ai][aj] || nchk[bi][bj]) continue;
                nchk[ai][aj] = true;
                nchk[bi][bj] = true;
                nchk[i][j] = true;
                nvisit[ai][aj] = true;
                nvisit[bi][bj] = true;
                nacc += board[ai][aj] + board[bi][bj] + board[i][j] * 2;
            }

            ANS = Math.max(ANS, nacc);
            for (int p = 0; p < N; p++) {
                for (int q = 0; q < M; q++) {
                    if (!nvisit[p][q]) {
                        recursion(p, q, nchk, nvisit, nacc);
                        break;
                    }
                }
            }
        }
    }


}
