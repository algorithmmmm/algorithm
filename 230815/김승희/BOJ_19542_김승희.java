import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

class Main {
    static int N, S, D;
    static int[] Prev;
    static ArrayList<ArrayList<Integer>> adj;
    static ArrayList<Integer> leaf;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        N = Integer.parseInt(inArr[0]); // N : 노드 개수
        S = Integer.parseInt(inArr[1]); // S : 시작 위치
        D = Integer.parseInt(inArr[2]); // D : 힘

        adj = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < N - 1; i++) {
            inArr = br.readLine().split(" ");
            int a = Integer.parseInt(inArr[0]);
            int b = Integer.parseInt(inArr[1]);
            adj.get(a).add(b);
            adj.get(b).add(a);
        }
        // end input
        Prev = new int[N + 1];  // Prev[i] = i번 노드 오기 전 노드
        BFS(S);
        System.out.println(count());
    }

    static void BFS(int start) {
        leaf = new ArrayList<>();    // leaf = 힘이 D일 때 가야하는 끝노드
        Queue<Integer> Q = new ArrayDeque<>();
        boolean[] visit = new boolean[N + 1];
        Q.add(start);
        visit[start] = true;
        Prev[start] = -1;
        while (!Q.isEmpty()) {
            int pop = Q.poll();
            int cnt = 0;    // cnt = pop이 갈 수 있는 노드의 개수
            for (int i = 0; i < adj.get(pop).size(); i++) {
                int temp = adj.get(pop).get(i);
                if (visit[temp]) continue;
                Q.add(temp);
                visit[temp] = true;
                Prev[temp] = pop;
                cnt++;
            }
            if (cnt == 0) { // cnt = 0이면 진짜 트리의 leaf
                // 힘이 D일 때 트리의 leaf를 찾으러 가자
                back(pop);
            }

        }
    }

    static void back(int pop) {
        int temp = pop;
        for (int i = 0; i < D; i++) {
            if (Prev[temp] == S){
                return;
            }
            temp = Prev[temp];
        }
        leaf.add(temp);
    }

    static int count() {
        boolean[] chk = new boolean[N + 1];
        int cnt = 0;
        for (int i = 0; i < leaf.size(); i++) {
            int temp = leaf.get(i);
            while (temp != S) {
                if (chk[temp]) break;
                cnt++;
                chk[temp] = true;
                temp = Prev[temp];
            }
        }
        return cnt * 2;
    }
}