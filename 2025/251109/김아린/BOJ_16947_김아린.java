package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_서울지하철2호선 {
    static int N;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static boolean[] cycled;
    static int[] parent;
    static boolean foundCycle = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        visited = new boolean[N + 1];
        cycled = new boolean[N + 1]; // 순환선인지 체크
        parent = new int[N + 1]; // 경로 저장용

        // 순환선 탐색
        findCycle(1, 0);

        // 거리 계산
        int[] dist = new int[N + 1];
        Arrays.fill(dist, -1);

        bfs(dist);

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(dist[i]).append(" ");
        }
        System.out.println(sb);
    }

    // 거리 계산 
	private static void bfs(int[] dist) {
		Queue<Integer> queue = new LinkedList<>();
        for(int i = 1; i <= N; i++) {
            if(cycled[i]) {
                dist[i] = 0;
                queue.offer(i);
            }
        }

        while(!queue.isEmpty()) {
            int cur = queue.poll();

            for(int next : graph[cur]) {
                if(dist[next] == -1) { // 순환선 아님
                    dist[next] = dist[cur] + 1;
                    queue.offer(next);
                }
            }
        }
	}

    // 순환선 찾기
    private static boolean findCycle(int cur, int prev) {
        visited[cur] = true;

        for(int next : graph[cur]) {
            if(next == prev) continue; // 부모 다시 방문하는 경우 제외

            if(!visited[next]) {
                parent[next] = cur; // 경로 추적용
                if(findCycle(next, cur)) return true;
            }else {
                // 순환 찾음
                markCycle(cur, next);
                return true;
            }
        }
        return false;
    }

    // 순환선 경로 추적하여 표시
    private static void markCycle(int start, int end) {
        cycled[end] = true;
        while(start != end) {
            cycled[start] = true;
            start = parent[start];
        }
    }
}
