package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_가로등 {
	static long L;
	static long K;
	static Set<Long> visited;
	static StringBuilder sb;
	static int[] dx = {-1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Long.parseLong(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		K = Long.parseLong(st.nextToken());
		
		Queue<long[]> queue = new LinkedList<>(); // {위치, 거리} 저장 큐
		visited = new HashSet<>(); // long 크기 배열은 불가하므로 set 사용
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) { //가로등 정보를 큐에 넣기
			long a = Long.parseLong(st.nextToken());
			queue.add(new long[] {a, 0});
			visited.add(a);
		}
		
		sb = new StringBuilder();
 		
		bfs(queue);
		
		System.out.println(sb);
	}
	
	public static void bfs(Queue<long[]> queue) {
		int count = 0; // 거리 구한 횟수
		while(!queue.isEmpty()) {
			long[] p = queue.poll();
			long x = p[0];
			long dist = p[1];
			
			sb.append(p[1] + "\n");
			count++;
			
			if(count == K) return; // 종료
			
			for(int i = 0; i < dx.length; i++) { // 인접한 가로등 탐색
				long nx = x + dx[i];
				
				if(nx < 0 || nx > L || visited.contains(nx)) continue;
				queue.add(new long[]{nx, dist + 1});
				visited.add(nx);
			}
		}
	}

}
