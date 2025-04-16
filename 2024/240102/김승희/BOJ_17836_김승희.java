import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ_17836 {
    static int N, M;
    static int[] di = {1, -1, 0, 0};
    static int[] dj = {0, 0, 1, -1};
    static int[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        N = Integer.parseInt(inArr[0]);
        M = Integer.parseInt(inArr[1]);
        int T = Integer.parseInt(inArr[2]);
        int[] knife = new int[2];
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            inArr = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(inArr[j]);
                if (board[i][j] == 2) knife = new int[]{i, j};
            }
        }
        // end input

        // 용사가 바로 공주가 있는 곳까지 가는 시간 (0, 0) -> (N-1, M-1)
        int direct = work2goal(new int[]{N - 1, M - 1});

        // 용사가 검이 있는 곳까지 가고 (0, 0) -> knife, 검을 들고 공주가 있는 곳까지 가는 시간 knife -> (N-1, M-1)
        int weapon = breakWall(work2goal(knife), knife);

        int answer = Math.min(direct, weapon);
        System.out.println(answer > T ? "Fail" : answer);
    }

    static int breakWall(int curr, int[] knife) {
        if (curr == Integer.MAX_VALUE) return Integer.MAX_VALUE;
        return curr + (N - 1 - knife[0]) + (M - 1 - knife[1]);
    }

    static int work2goal(int[] goal) {
        boolean[][] visit = new boolean[N][M];
        Queue<int[]> Q = new ArrayDeque<>();
        visit[0][0] = true;
        Q.add(new int[]{0, 0});
        int time = 0;
        while (!Q.isEmpty()) {
            int size = Q.size();
            for (int s = 0; s < size; s++) {
                int[] pnt = Q.poll();
                for (int k = 0; k < 4; k++) {
                    int ni = di[k] + pnt[0];
                    int nj = dj[k] + pnt[1];
                    if (ni < 0 || nj < 0 || ni >= N || nj >= M) continue;
                    if (visit[ni][nj] || board[ni][nj] == 1) continue;
                    if (ni == goal[0] && nj == goal[1]) return time + 1;
                    visit[ni][nj] = true;
                    Q.add(new int[]{ni, nj});
                }
            }
            time++;
        }
        return Integer.MAX_VALUE;
    }
}
