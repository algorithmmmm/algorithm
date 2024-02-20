import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static int N, Answer;
    static int[][] Number;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Number = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] inArr = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                Number[i][j] = Integer.parseInt(inArr[j]);
            }
        }
        // end input

        Answer = Integer.MAX_VALUE;
        binarySearch();
        System.out.println(Answer);

    }
    static void binarySearch() {
        int start = 0;
        int end = 1000000;
        while (start <= end) {
            int mid = (start + end) / 2;
            // 색칠된 칸이 전체 칸의 반 이상이라면 -> 좀 덜 색칠해보자
            if (isColored(mid)) {
                end = mid - 1;
                Answer = Math.min(Answer, mid);
            } else { // 그렇지 않다면 -> 더 많이 색칠해야 됨
                start = mid + 1;
            }
        }
    }

    private static boolean isColored(int d) {
        int count = 0;
        boolean[][] visit = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visit[i][j]) continue;
                count = Math.max(coloring(i, j, d, visit), count);
            }
        }
        // 반올림해서 하기 때문에 +1 한 다음에 2로 나누기
        int half = (N * N + 1) / 2;
        // 방문한 곳이 half개 이상이면 true
        if (count >= half) return true;
        return false;
    }

    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    private static int coloring(int i, int j, int d, boolean[][] visit) {
        Queue<int[]> Q = new ArrayDeque<>();
        Q.add(new int[]{i, j});
        visit[i][j] = true;
        int cnt = 1;
        while (!Q.isEmpty()) {
            int[] poll = Q.poll();
            for (int k = 0; k < 4; k++) {
                int ni = poll[0] + di[k];
                int nj = poll[1] + dj[k];
                // 범위를 벗어났거나 방문했으면 안됨
                if (ni < 0 || nj < 0 || ni >= N || nj >= N || visit[ni][nj]) continue;
                // 인접한 곳과 차이가 d 이상이면 안됨
                if ( Math.abs(Number[poll[0]][poll[1]] - Number[ni][nj]) > d) continue;
                // 새로운 곳 방문
                Q.add(new int[]{ni, nj});
                visit[ni][nj] = true;
                cnt++;
            }
        }
        return cnt;
    }
}
