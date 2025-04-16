import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class BOJ_14442 {
    static int N, M, K;
    static int[][] Board;
    static int[][][] Dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        N = Integer.parseInt(inArr[0]);
        M = Integer.parseInt(inArr[1]);
        K = Integer.parseInt(inArr[2]);
        Board = new int[N][M];
        Dist = new int[N][M][K+1];
        for (int i = 0; i < N; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (chars[j] == '1') {
                    Board[i][j] = 1;
                }
                for (int k = 0; k < K + 1; k++) {
                    Dist[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        // end input

        bfs();
        int answer = Integer.MAX_VALUE;
        for (int k = 0; k < K + 1; k++) {
            answer = Math.min(answer, Dist[N - 1][M - 1][k]);
        }

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, 1, -1};

    static void bfs() {
        Queue<int[]> Q = new ArrayDeque<>();
        boolean[][][] visit = new boolean[N][M][K + 1];
        // Dist[i][j][k] : (i, j)까지 가는데 k번 벽을 부쉈을 때 최단 거리
        Dist[0][0][0] = 1;
        visit[0][0][0] = true;
        // (0, 1) 위치, 2 거리, 3 부순 벽의 개수
        Q.add(new int[]{0, 0, 1, 0});
        while (!Q.isEmpty()) {
            int[] tp = Q.poll();
            for (int k = 0; k < 4; k++) {
                int ni = tp[0] + di[k];
                int nj = tp[1] + dj[k];
                if (ni < 0 || nj < 0 || ni >= N || nj >= M) continue;
                if (Board[ni][nj] == 1) {   // 벽이면 부숴야 함
                    // 이미 방문했거나, 벽을 이미 K개 부쉈으면
                    if (tp[3] >= K || visit[ni][nj][tp[3]+1]) continue;
                    Dist[ni][nj][tp[3]+1] = tp[2] + 1;
                    Q.add(new int[]{ni, nj, tp[2] + 1, tp[3] + 1});
                    visit[ni][nj][tp[3] + 1] = true;
                    continue;
                }
                // 벽이 아니면 그냥 갈 수 있음
                if (visit[ni][nj][tp[3]]) continue;     // 이미 방문 했으면
                Dist[ni][nj][tp[3]] = tp[2] +1;
                Q.add(new int[]{ni, nj, tp[2] + 1, tp[3]});
                visit[ni][nj][tp[3]] = true;
            }
        }
    }
}