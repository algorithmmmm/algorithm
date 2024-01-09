import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ_1389 {
    static int N, M;
    static boolean[][] adj;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        N = Integer.parseInt(inArr[0]);
        M = Integer.parseInt(inArr[1]);

        adj = new boolean[N+1][N+1];
        for (int i = 0; i < M; i++) {
            inArr = br.readLine().split(" ");
            int a = Integer.parseInt(inArr[0]);
            int b = Integer.parseInt(inArr[1]);
            adj[a][b] = true;
            adj[b][a] = true;
        }
        // end input

        int user = 1;
        int kb = kevin_bacon(1);
        for (int i = 2; i < N+1; i++) {
            int temp = kevin_bacon(i);
            if (kb > temp) {
                user = i;
                kb = temp;
            }
        }
        System.out.println(user);
    }

    static int kevin_bacon(int start) {
        int[] dist = new int[N + 1];
        Queue<Integer> Q = new ArrayDeque<>();
        Q.add(start);
        dist[start] = Integer.MAX_VALUE;
        int num = 1;
        while (!Q.isEmpty()) {
            int size = Q.size();
            for (int s = 0; s < size; s++){
                int pop = Q.poll();
                for (int i = 1; i < N+1; i++) {
                    if (i == pop) continue;
                    if (dist[i] > 0) continue;
                    if (adj[pop][i]) {
                        Q.add(i);
                        dist[i] = num;
                    }
                }
            }
            num++;
        }
        return acc(dist);
    }

    static int acc(int[] dist) {
        int answer = 0;
        for (int i = 0; i < dist.length; i++) {
            if (dist[i] == Integer.MAX_VALUE) continue;
            answer += dist[i];
        }
        return answer;
    }

}

