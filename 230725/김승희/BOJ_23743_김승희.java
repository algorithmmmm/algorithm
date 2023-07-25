import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    static class Edge implements Comparable<Edge>{
        int a, b, time;

        public Edge(int a, int b, int time) {
            this.a = a;
            this.b = b;
            this.time = time;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.time, o.time);
        }
    }
    static int N, M;
    static int[] parent, rank;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        N = Integer.parseInt(inArr[0]);
        M = Integer.parseInt(inArr[1]);

        init();
        PriorityQueue<Edge> PQ = new PriorityQueue<>();

        for (int i = 0; i < M; i++) {
            inArr = br.readLine().split(" ");
            int a = Integer.parseInt(inArr[0]);
            int b = Integer.parseInt(inArr[1]);
            int t = Integer.parseInt(inArr[2]);
            PQ.add(new Edge(a, b, t));
        }
        inArr = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            int t = Integer.parseInt(inArr[i]);
            PQ.add(new Edge(i + 1, 0, t));
        } // end input

        int answer = 0;
        while (!PQ.isEmpty()) {
            Edge edge = PQ.poll();
            if (union(edge.a, edge.b)) {
                answer += edge.time;
            }
        }
        System.out.println(answer);
    }

    static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a == b) return false;

        if (rank[a] < rank[b] || (rank[a] == rank[b] && a < b)){
            rank[a]++;
            parent[b] = a;
            find(b);
        }else{
            rank[b]++;
            parent[a] = b;
            find(a);
        }
        return true;
    }
    static void init(){
        parent = new int[N + 1];
        rank = new int[N + 1];
        // 0은 탈출구
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }
}
