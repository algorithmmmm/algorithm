package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_떡장수와호랑이 {
	static int N;
    static List<Integer>[] graph;
    static boolean[][] visited;
    static int[] answer;

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()); // 떡 파는 날 수
        graph = new ArrayList[N];
        
        for(int i = 0; i < N; i++) {
        	graph[i] = new ArrayList<>();
        	
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	int m = Integer.parseInt(st.nextToken()); // 떡 개수
        	for(int j = 0; j < m; j++) {
        		graph[i].add(Integer.parseInt(st.nextToken()));
        	}
        }
        
        visited = new boolean[N][10];
        answer = new int[N];
        
        dfs(0, 0);
        
        System.out.println(-1);
	}

	private static void dfs(int day, int prev) {
		if(day == N) {
            for(int a : answer) {
                System.out.println(a);
            }
            System.exit(0);
        }
		
		for(int cur : graph[day]) {
            if(cur != prev && !visited[day][cur]) {
                visited[day][cur] = true;
                answer[day] = cur;
                dfs(day + 1, cur);
            }
        }
	}

}
