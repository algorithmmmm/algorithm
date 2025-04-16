import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    static class Tuple implements Comparable<Tuple> {
        int x, dist;

        public Tuple(int x, int dist) {
            this.x = x;
            this.dist = dist;
        }

        @Override
        public int compareTo(Tuple o) {
            return Integer.compare(this.dist, o.dist);
        }
    }
    static int N, M;
    static int[] plan;
    static boolean[][] adj;
    static int[][] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        adj = new boolean[N][N];
        dist = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] inArr = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                if (Integer.parseInt(inArr[j]) == 1) {
                    adj[i][j] = true;
                }
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        String[] inArr = br.readLine().split(" ");
        plan = new int[M];
        for (int i = 0; i < M; i++) {
            plan[i] = Integer.parseInt(inArr[i])-1;   // plan : 여행 계획
        }
        // end input
        String answer = process() ? "YES" : "NO";
        System.out.println(answer);

    }


    static boolean process(){
        for (int i = 0; i < N; i++) {
            dijkstra(i);
        }
        return check();
    }

    // dist[start] 배열의 값을 채우기
    // start 노드로부터 갈 수 있는 노드를 구하기
    static void dijkstra(int start){
        PriorityQueue<Tuple> PQ = new PriorityQueue<>();
        PQ.add(new Tuple(start, 0));
        dist[start][start] = 0;
        while (!PQ.isEmpty()) {
            Tuple tp = PQ.poll();
            if (dist[start][tp.x] < tp.dist) continue;
            for (int i = 0; i < N; i++) {
                if (adj[tp.x][i]) {
                    if (dist[start][i] > tp.dist + 1) {
                        dist[start][i] = tp.dist + 1;
                        PQ.add(new Tuple(i, tp.dist + 1));
                    }
                }
            }
        }
    }
    // 여행 계획을 지킬 수 있는지 확인
    static boolean check() {
        int prev = plan[0];
        for (int i = 1; i < M; i++) {
            if (dist[prev][plan[i]] == Integer.MAX_VALUE) return false;
            prev = plan[i];
        }
        return true;
    }
}