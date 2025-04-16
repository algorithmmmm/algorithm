import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    static class Tuple implements Comparable<Tuple>{
        int node, dist, item;
        public Tuple(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
        // 거리는 짧고, 아이템 개수는 많아야 함
        @Override
        public int compareTo(Tuple o) {
            return Integer.compare(this.dist, o.dist);
        }
    }
    static int N, M;
    static int[] items;
    static ArrayList<ArrayList<Tuple>> adj;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        N = Integer.parseInt(inArr[0]);     // N : 지역의 개수
        M = Integer.parseInt(inArr[1]);     // M : 최대 수색 범위
        int R  = Integer.parseInt(inArr[2]);    // R : 길의 개수
        items = new int[N]; // items[i] : i번 지역에 있는 아이템의 수
        inArr = br.readLine().split(" ");
        adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            items[i] = Integer.parseInt(inArr[i]);
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < R; i++) {
            inArr = br.readLine().split(" ");
            int a = Integer.parseInt(inArr[0]);
            int b = Integer.parseInt(inArr[1]);
            int c = Integer.parseInt(inArr[2]);
            adj.get(a-1).add(new Tuple(b-1, c));
            adj.get(b-1).add(new Tuple(a-1, c));
        } // end input

        int answer = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            answer = Math.max(answer, dijkstra(i));
        }
        System.out.println(answer);
    }

    /**
     *
     * @param start : 예은이가 출발하는 지역
     * @return  start부터 출발해서 얻을 수 있는 최대 아이템 개수
     */
    static int dijkstra(int start) {
        int[] dp = init();
        PriorityQueue<Tuple> PQ = new PriorityQueue<>();
        dp[start] = 0;
        PQ.add(new Tuple(start, 0));
        while (!PQ.isEmpty()) {
            Tuple tp = PQ.poll();
            if (dp[tp.node] < tp.dist) continue;
            for (int i = 0; i < adj.get(tp.node).size(); i++) {
                Tuple near = adj.get(tp.node).get(i);
                if (dp[near.node] < tp.dist + near.dist) continue;
                dp[near.node] = tp.dist + near.dist;
                PQ.add(new Tuple(near.node, dp[near.node]));
            }
        }
        return answer(dp);
    }
    
    static int[] init(){
        int[] dp = new int[N];
        for (int i = 0; i < N; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        return dp;
    }

    /**
     * 
     * @param dp i번 지역에 가는 거리가 M 이하라면 갈 수 있다.
     * @return 최대 아이템 개수를 반환
     */
    static int answer(int[] dp) {
        int acc = 0;
        for (int i = 0; i < N; i++) {
            if (dp[i] <= M) {
                acc += items[i];
            }
        }
        return acc;
    }
}