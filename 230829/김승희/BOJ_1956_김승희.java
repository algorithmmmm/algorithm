import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
    static class Tuple implements Comparable<Tuple>{
        int node, dist;

        public Tuple(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Tuple o) {
            return Integer.compare(this.dist, o.dist);
        }
    }
    static class Path implements Comparable<Path>{
        int start, end, dist;

        public Path(int start, int end, int dist) {
            this.start = start;
            this.end = end;
            this.dist = dist;
        }

        @Override
        public int compareTo(Path o) {
            return Integer.compare(this.dist, o.dist);
        }
    }
    static int V, E;
    static ArrayList<Tuple>[] adj;
    static int[][] Dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        V = Integer.parseInt(inArr[0]);
        E = Integer.parseInt(inArr[1]);
        adj = new ArrayList[V];
        Dist = new int[V][V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<Tuple>();
            for (int j = 0; j < V; j++) {
                Dist[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < E; i++) {
            inArr = br.readLine().split(" ");
            int a = Integer.parseInt(inArr[0])-1;
            int b = Integer.parseInt(inArr[1])-1;
            int c = Integer.parseInt(inArr[2]);
            adj[a].add(new Tuple(b, c));
        }// end input

        dijkstra();
        PriorityQueue<Path> PQ = new PriorityQueue<>();
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i == j || Dist[i][j] == Integer.MAX_VALUE) continue;
                PQ.add(new Path(i, j, Dist[i][j]));
            }
        }
        int answer = Integer.MAX_VALUE;
        int[][] check = new int[V][V];
        while (!PQ.isEmpty()) {
            Path p = PQ.poll();
            check[p.start][p.end] = p.dist;
            if (check[p.end][p.start] != 0){
                answer = Math.min(answer, check[p.start][p.end] + check[p.end][p.start]);
            }
        }
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);

    }
    static void dijkstra(){
        for (int start = 0; start < V; start++) {
            PriorityQueue<Tuple> PQ = new PriorityQueue<>();
            Dist[start][start] = 0;
            PQ.add(new Tuple(start, 0));
            while (!PQ.isEmpty()) {
                Tuple tp = PQ.poll();
                // 이미 봄
                if (Dist[start][tp.node] < tp.dist) {
                    continue;
                }
                // 이제 보기
                for (int i = 0; i < adj[tp.node].size(); i++) {
                    int n = adj[tp.node].get(i).node;
                    int d = tp.dist + adj[tp.node].get(i).dist;
                    if (Dist[start][n] <= d) continue;
                    Dist[start][n] = d;
                    PQ.add(new Tuple(n, d));
                }
            }
        }
    }
}