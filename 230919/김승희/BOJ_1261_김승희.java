import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    static int N, M;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static char[][] Board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        M = Integer.parseInt(inArr[0]);
        N = Integer.parseInt(inArr[1]);
        Board = new char[N][M];
        for (int i = 0; i < N; i++) {
            Board[i] = br.readLine().toCharArray();
        }
        // end input
        System.out.println(BFS());
    }
    static Integer BFS() {
        // (int[0], int[1]) 에 int[2] 개의 벽을 부수고 옴
        // int[2]를 기준으로 우선순위 큐 -> 벽을 적게 부수는 것부터 확인
        PriorityQueue<int[]> Q = new PriorityQueue<>((o1, o2) ->  Integer.compare(o1[2], o2[2]));
        int[][] visit = init();
        visit[0][0] = 0;
        Q.add(new int[]{0, 0, 0});
        while (!Q.isEmpty()) {
            int[] tp = Q.poll();
            for (int k = 0; k < 4; k++) {
                int ni = tp[0] + di[k];
                int nj = tp[1] + dj[k];
                if (ni < 0 || nj < 0 || ni >= N || nj >= M) continue;
                if (ni == N-1 && nj == M-1) return tp[2];   // 벽을 적게 부수는 것부터 확인하기 때문에 도착지에 도착하는 거 그냥 반환
                // 벽이고, (ni, nj)에 tp[2]+1보다 적게 벽을 부쉈을 때 왔었으면
                if (Board[ni][nj] == '1' && visit[ni][nj] < tp[2] + 1){
                    Q.add(new int[]{ni, nj, tp[2] + 1});
                    visit[ni][nj] = tp[2] + 1;
                }
                // 벽이 아니고, (ni, nj)에 tp[2]보다 적게 벽을 부쉈을 때 왔었으면
                if (Board[ni][nj] == '0' && visit[ni][nj] < tp[2]){
                    Q.add(new int[]{ni, nj, tp[2]});
                    visit[ni][nj] = tp[2];
                }
            }
        }
        // 존재하는 모든 벽을 부숴서라도 (N-1, M-1)로 도착할 수 있기 때문에 위에서 return 이 되야 함
        // 위에서 return 이 안되는 경우는 
        // 1 1
        // 0
        return 0;
    }
    static int[][] init(){
        int[][] visit = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visit[i][j] = -1;
            }
        }
        return visit;
    }
}