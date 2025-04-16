package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_ACMCraft {
	static int N;
	static List<Integer>[] graph;
	static int[] time;
	static int[] inCount;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			graph = new ArrayList[N + 1];
			
			for(int i = 1; i <= N; i++) {
				graph[i] = new ArrayList<>();
			}
			
			time = new int[N + 1];
			
			st = new StringTokenizer(br.readLine(), " ");
			
			for(int i = 1; i <= N; i++) {
				time[i] = Integer.parseInt(st.nextToken());
			}
			
			inCount = new int[N + 1]; //진입 차수
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				graph[a].add(b);
				inCount[b]++;
			}
			
			int W = Integer.parseInt(br.readLine());
			
			sb.append(bfs(W)).append("\n");
		}
		
		System.out.println(sb);
	}

	public static int bfs(int w) {
		Queue<Integer> queue = new LinkedList<>();
		int[] sum = new int[N + 1]; //인덱스까지의 시간 합
		
		for(int i = 1; i <= N; i++) {
			sum[i] = time[i];
			
			if(inCount[i] == 0) { //진입차수가 0이면 큐에 넣기 
				queue.add(i);
			}
		}
		
		while(!queue.isEmpty()) {
			int p = queue.poll();
			
			for(int next : graph[p]) {
				sum[next] = Math.max(sum[next], sum[p] + time[next]); //최대값으로 선택 
				
				inCount[next]--; //진입 간선 제거 
				
				if(inCount[next] == 0) { //진입차수가 0이면 큐에 넣기 
					queue.add(next);
				}
			}
		}
		
		return sum[w];
	}

}
