import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
    static int N, M, K;
    static ArrayList<long[]>[] adj;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        N = Integer.parseInt(inArr[0]);     // N : 도시의 수
        M = Integer.parseInt(inArr[1]);     // M : 도로의 수
        K = Integer.parseInt(inArr[2]);     // K : 면접장의 수

        adj = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            inArr = br.readLine().split(" ");
            int a = Integer.parseInt(inArr[0]);
            int b = Integer.parseInt(inArr[1]);
            int time = Integer.parseInt(inArr[2]);
            adj[b].add(new long[]{a, time});
        }

        inArr = br.readLine().split(" ");
        for (int k = 0; k < K; k++) {
            int goal = Integer.parseInt(inArr[k]);
            adj[0].add(new long[]{goal, 0});
        }

        int number = Integer.MIN_VALUE;
        long dist = Long.MIN_VALUE;
        long[] answer = dijkstra(0);
        for (int i = 1; i < N + 1; i++) {
            if (answer[i] > dist) {
                dist = answer[i];
                number = i;
            }
        }
        System.out.println(number);
        System.out.println(dist);
    }

    static long[] dijkstra(int start) {
        PriorityQueue<long[]> PQ = new PriorityQueue<>((o1, o2) -> Long.compare(o1[1], o2[1]));
        long[] time = init();
        PQ.add(new long[]{start, 0});
        time[start] = 0;
        while (!PQ.isEmpty()) {
            long[] tp = PQ.poll();
            if (time[(int)tp[0]] < tp[1]) continue;
            for (int i = 0; i < adj[(int)tp[0]].size(); i++) {
                long[] near = adj[(int)tp[0]].get(i);
                long nt = tp[1] + near[1];
                if (time[(int)near[0]] > nt) {
                    time[(int)near[0]] = nt;
                    PQ.add(new long[]{near[0], nt});
                }
            }
        }
        return time;
    }
    static long[] init() {
        long[] time = new long[N + 1];
        for (int i = 0; i < N + 1; i++) {
            time[i] = Long.MAX_VALUE;
        }
        return time;
    }
}