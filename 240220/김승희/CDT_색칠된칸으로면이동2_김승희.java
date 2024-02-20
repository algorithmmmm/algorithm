import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
    static int N, M, Answer, Cnt;
    static int[][] Number;
    static int[] pnt;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static boolean[][] isColored;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        N = Integer.parseInt(inArr[0]);
        M = Integer.parseInt(inArr[1]);
        Number = new int[N][M];
        for (int i = 0; i < N; i++) {
            inArr = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                Number[i][j] = Integer.parseInt(inArr[j]);
            }
        }
        Cnt = 0;
        pnt = null;
        isColored = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            inArr = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                if (inArr[j].equals("1")) {
                    isColored[i][j] = true;
                    if (pnt == null) {
                        pnt = new int[]{i, j};
                    }
                    Cnt++;
                }
            }
        }
        // end input
        Answer = Integer.MAX_VALUE;
        binarySearch();
        System.out.println(Answer);
    }

    static void binarySearch() {
        int start = 0;
        int end = 1000000000;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (coloring(mid)) {
                Answer = Math.min(Answer, mid);
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
    }

    private static boolean coloring(int D) {
        Queue<int[]> Q = new ArrayDeque<>();
        int[][] visit = new int[N][M];
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            Arrays.fill(visit[i], Integer.MAX_VALUE);
        }
        Q.add(pnt);
        visit[pnt[0]][pnt[1]] = 0;
        cnt++;

        while (!Q.isEmpty()) {
            int[] poll = Q.poll();
            for (int k = 0; k < 4; k++) {
                int ni = poll[0] + di[k];
                int nj = poll[1] + dj[k];
                if (ni < 0 || nj < 0 || ni >= N || nj >= M) continue;
                int d = Math.max(visit[poll[0]][poll[1]], 
                        Math.abs(Number[poll[0]][poll[1]] - Number[ni][nj]));
                if (visit[ni][nj] <= d || d > D) continue;
                Q.add(new int[]{ni, nj});
                visit[ni][nj] = d;
                if (isColored[ni][nj]) cnt++;
            }
        }
        return cnt == Cnt;
    }
}