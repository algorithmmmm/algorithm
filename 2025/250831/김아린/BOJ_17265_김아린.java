package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_나의인생에는수학과함께 {
    static int N;
    static char[][] graph;
    static int[][] min_dp, max_dp;
    static int[] dx = {1, 0}; // 아래, 오른쪽
    static int[] dy = {0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        graph = new char[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = st.nextToken().charAt(0);
            }
        }

        // 구현
        min_dp = new int[N][N];
        max_dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(min_dp[i], Integer.MAX_VALUE);
            Arrays.fill(max_dp[i], Integer.MIN_VALUE);
        }

        bfs(0, 0, N, N);

        // 출력
        System.out.println(max_dp[N - 1][N - 1] + " " + min_dp[N - 1][N - 1]);
    }

    private static void bfs(int start_x, int start_y, int end_x, int end_y) {
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{0, 0});
        min_dp[0][0] = max_dp[0][0] = graph[0][0] - '0';

        while(!queue.isEmpty()) {
            int[] p = queue.poll();
            int x = p[0], y = p[1];

            int curMin = min_dp[x][y];
            int curMax = max_dp[x][y];

            for(int d = 0; d < 2; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

                char cur = graph[x][y];
                char next = graph[nx][ny];

                if('0' <= next && next <= '9') {
                    int num = next - '0';
                    char op = cur;

                    int cal1 = calculate(curMin, op, num);
                    int cal2 = calculate(curMax, op, num);
                    int nextMin = Math.min(cal1, cal2);
                    int nextMax = Math.max(cal1, cal2);

                    boolean visited = false;
                    
                    if(nextMin < min_dp[nx][ny]) {
                        min_dp[nx][ny] = nextMin;
                        visited = true;
                    }
                    if(nextMax > max_dp[nx][ny]) {
                        max_dp[nx][ny] = nextMax;
                        visited = true;
                    }
                    if(visited) queue.offer(new int[]{nx, ny});
                } else {
                	boolean visited = false;
                    if(curMin < min_dp[nx][ny]) {
                        min_dp[nx][ny] = curMin;
                        visited = true;
                    }
                    if(curMax > max_dp[nx][ny]) {
                        max_dp[nx][ny] = curMax;
                        visited = true;
                    }
                    if(visited) queue.offer(new int[]{nx, ny});
                }
            }
        }
    }

    private static int calculate(int a, char op, int b) {
        if (op == '+') return a + b;
        if (op == '-') return a - b;
        return a * b;
    }
}
