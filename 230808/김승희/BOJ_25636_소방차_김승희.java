import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {
    static class Tuple implements Comparable<Tuple>{
        long a, d;

        public Tuple(long a, long d) {
            this.a = a;
            this.d = d;
        }
        @Override
        public int compareTo(Tuple o) {
            return Long.compare(this.d, o.d);
        }
    }
    static int N;
    static long[] water;
    static Tuple[] answer;
    static ArrayList<ArrayList<Tuple>> adj;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[] inArr = br.readLine().split(" ");
        water = new long[N + 1];
        for (int i = 0; i < N; i++) {
            water[i + 1] = Long.parseLong(inArr[i]);
        }
        adj = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            adj.add(new ArrayList<>());
        }
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            inArr = br.readLine().split(" ");
            int a = Integer.parseInt(inArr[0]);
            int b = Integer.parseInt(inArr[1]);
            long d = Long.parseLong(inArr[2]);
            adj.get(a).add(new Tuple(b, d));
            adj.get(b).add(new Tuple(a, d));
        }
        inArr = br.readLine().split(" ");
        int start = Integer.parseInt(inArr[0]);
        int end = Integer.parseInt(inArr[1]);
        answer = new Tuple[N + 1];
        dijkstra(start);
        if (answer[end] == null) {
            System.out.println(-1);
        } else {
            System.out.printf("%d %d\n", answer[end].d, answer[end].a);
        }
    }

    private static void dijkstra(int start) {
        PriorityQueue<Tuple> PQ = new PriorityQueue<>();
        PQ.add(new Tuple(start, 0));    // a : 정점, d : 거리
        answer[start] = new Tuple(water[start], 0);  // a : 물의 양, d : 거리
        while (!PQ.isEmpty()) {
            Tuple tp = PQ.poll();
            // 이미 작으면 봤었던거
            if (answer[(int)tp.a] != null && answer[(int)tp.a].d < tp.d) continue;
            for (int k = 0; k < adj.get((int)tp.a).size(); k++) {
                Tuple near = adj.get((int)tp.a).get(k);  // 인접 정점
                long nd = tp.d + near.d;     // nd : 새로운 거리
                long nw = answer[(int)tp.a].a + water[(int)near.a]; // nw : 새로운 물의 양
                // 1. 현재 거리 > 새로운 거리
                if (answer[(int)near.a] == null || answer[(int)near.a].d > nd) {
                    answer[(int)near.a] = new Tuple(nw, nd);
                    PQ.add(new Tuple(near.a, nd));
                } else if (answer[(int)near.a].d == nd) {    // 2. 현재 거리 == 새로운 거리
                    answer[(int)near.a].a = Math.max(answer[(int)near.a].a, nw);  // 물의 양이 더 많은 거
                }
            }
        }
    }
}