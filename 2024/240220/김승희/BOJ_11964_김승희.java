import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
    static int T, A, B;
    static boolean[] possible;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        T = Integer.parseInt(inArr[0]);
        A = Integer.parseInt(inArr[1]);
        B = Integer.parseInt(inArr[2]);
        int answer = 0;
        if (T % A == 0 || T % B == 0 || T % (A + B) == 0) {
            answer = T;
        } else {
            answer = bfs(new int[]{A, B});
        }
        System.out.println(answer);
    }

    static int bfs(int[] num) {
        Queue<int[]> Q = new ArrayDeque<>();
        // visit[t][0] : t 포만감을 만드는데 물을 안마셨다
        // visit[t][1] : t 포만감을 만드는데 물을 마셨다
        boolean[][] visit = new boolean[T + 1][2];
        // 포만감 0은 항상 가능
        visit[0][0] = true;
        visit[0][1] = true;
        for (int n : num) {
            Q.add(new int[]{n, 0});
            visit[n][0] = true;
        }
        while (!Q.isEmpty()) {
            int[] poll = Q.poll();
            for (int n : num) {     // Q에서 꺼낸 포만감에 A를 더해보고 B도 더해본다.
                int temp = poll[0] + n;
                if (temp > T || visit[temp][poll[1]]) continue;
                Q.add(new int[]{temp, poll[1]});
                visit[temp][poll[1]] = true;
            }
            // 만약 물을 안마셨으면 물을 마셔본다.
            if (poll[1] == 0) {
                int temp = (int) Math.floor(poll[0] / 2.0);
                if (visit[temp][1]) continue;
                Q.add(new int[]{temp, 1});
                visit[temp][1] = true;
            }
        }
        return check(visit);
    }

    static int check(boolean[][] visit) {
        for (int t = T; t >= 0; t--) {
            if (visit[t][0] || visit[t][1]) return t;
        }
        return 0;
    }
}
