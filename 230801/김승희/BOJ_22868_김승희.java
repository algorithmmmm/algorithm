import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

public class Main {
    static int N, M, start, end;
    static int[] prev;
    static ArrayList<ArrayList<Integer>> adj;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        N = Integer.parseInt(inArr[0]);
        M = Integer.parseInt(inArr[1]);
        init();
        for (int i = 0; i < M; i++) {
            inArr = br.readLine().split(" ");
            int a = Integer.parseInt(inArr[0]);
            int b = Integer.parseInt(inArr[1]);
            adj.get(a).add(b);
            adj.get(b).add(a);
        }
        for (int i = 0; i < N + 1; i++) {
            Collections.sort(adj.get(i));
        }
        inArr = br.readLine().split(" ");
        start = Integer.parseInt(inArr[0]);
        end = Integer.parseInt(inArr[1]);
        // end input

        // S -> E
        int ans = BFS(start, end, false);
        // E -> S
        ans += BFS(start, end, true);

        System.out.println(ans);
    }

    static Integer BFS(int start, int end, boolean chk) {
        Queue<Integer> Q = new ArrayDeque<>();
        boolean[] visit = new boolean[N+1];
        if (chk) {
            visit = init_visit(start, end);
        }
        Q.add(start);
        visit[start] = true;
        prev = new int[N + 1];
        prev[start] = 0;
        int dist = 0;
        while (!Q.isEmpty()) {
            int size = Q.size();
            for (int i = 0; i < size; i++) {
                int a = Q.poll();
                for (int k = 0; k < adj.get(a).size(); k++) {
                    int temp = adj.get(a).get(k);
                    // 방문했으면 안가기
                    if (visit[temp]) continue;
                    // 이전에 온 노드 저장
                    prev[temp] = a;
                    if (temp == end){
                        return dist+1;
                    }
                    // 방문 체크
                    visit[temp] = true;
                    // 큐에 넣기
                    Q.add(temp);
                }
            }
            dist++;
        }
        return dist;
    }

    private static boolean[] init_visit(int start, int end) {
        boolean[] visit = new boolean[N + 1];
        int prev_v = end;
        while (prev_v != start) {
            prev_v = prev[prev_v];
            visit[prev_v] = true;
        }
        return visit;
    }

    static void init(){
        adj = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
        }
    }
}