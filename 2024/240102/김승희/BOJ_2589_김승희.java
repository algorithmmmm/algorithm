import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ_2589 {
    static int N, M;
    static boolean[][] Map;
    static int[] di = {1, -1, 0, 0};
    static int[] dj = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        N = Integer.parseInt(inArr[0]);
        M = Integer.parseInt(inArr[1]);
        Map = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (chars[j] == 'L')
                    Map[i][j] = true;
            }
        }

        int answer = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (Map[i][j]) {
                    answer = Math.max(answer, BFS(new int[]{i, j}));
                }
            }
        }
        System.out.println(answer);
    }

    static int BFS(int[] start) {
        int[][] visit = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visit[i][j] = Integer.MAX_VALUE;
            }
        }

        Queue<int[]> Q = new ArrayDeque<>();
        Q.add(new int[]{start[0], start[1], 0});
        visit[start[0]][start[1]] = 0;
        while (!Q.isEmpty()) {
            int[] pop = Q.poll();
            for (int k = 0; k < 4; k++) {
                int ni = pop[0] + di[k];
                int nj = pop[1] + dj[k];
                if (ni < 0 || nj < 0 || ni >= N || nj >= M || !Map[ni][nj]) continue;
                if (visit[ni][nj] <= pop[2]+1) continue;
                visit[ni][nj] = pop[2] + 1;
                Q.add(new int[]{ni, nj, visit[ni][nj]});
            }
        }
        int answer = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visit[i][j] == Integer.MAX_VALUE) continue;
                answer = Math.max(answer, visit[i][j]);
            }
        }
        return answer;
    }
}
