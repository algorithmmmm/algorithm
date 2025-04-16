import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N, M, ans;
    static int[] start, end;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static char[][] Board;
    static int[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inArr = br.readLine().split(" ");
        M = Integer.parseInt(inArr[0]);
        N = Integer.parseInt(inArr[1]);
        Board = new char[N][M];
        start = new int[2];
        end = new int[2];
        boolean flag = false;
        for (int i = 0; i < N; i++) {
            Board[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (Board[i][j] == 'C') {
                    if (!flag) {
                        start = new int[]{i, j};
                        flag = true;
                    } else {
                        end = new int[]{i, j};
                    }
                }
            }
        }
        // end input

        // start -> end 로 가는 경로에서 꺾이는 부분의 개수 세기
        ans = Integer.MAX_VALUE;
        for (int k = 0; k < 4; k++) {
            visit = init();
            DFS(start[0],start[1], k, 0);
        }
        System.out.println(ans);
    }

    static void DFS(int I, int J, int D, int cnt) {
        if (cnt >= ans) return;  // 이미 정답보다 횟수가 많으면 볼 필요 없음
        if (I == end[0] && J == end[1]) {
            ans = Math.min(ans, cnt);
            return;
        }
        visit[I][J] = cnt;
        for (int k = 0; k < 4; k++) {
            int ni = I + di[k];
            int nj = J + dj[k];
            if (ni < 0 || nj < 0 || ni >= N || nj >= M || visit[ni][nj] <= cnt || Board[ni][nj] == '*') continue;

            if (k == D) {
                DFS(ni, nj, k, cnt);
            } else {
                DFS(ni, nj, k, cnt+1);
            }
        }
    }
    static int[][] init() {
        int[][] visit = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visit[i][j] = Integer.MAX_VALUE;
            }
        }
        return visit;
    }
}