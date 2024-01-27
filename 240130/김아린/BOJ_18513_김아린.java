package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_샘터 {
	static int[] dx = {-1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N]; //샘터 위치 저장
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(bfs(arr, K));
	}

	public static long bfs(int[] arr, int k) {
		Queue<Integer> queue = new LinkedList<>(); //탐색을 위한 큐
		HashSet<Integer> visited = new HashSet<>(); //집이 있는지 저장
		
		for(int water : arr) { //샘터 위치를 큐에 넣음
			queue.add(water);
			visited.add(water);
		}
		
		int house = 0; //지은 집의 개수
		long sum = 0; //불행도 합
		int dist = 1; //거리
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int s = 0; s < size; s++) { //거리별로 끊어서 탐색
				int x = queue.poll();
				
				for(int d = 0; d < 2; d++) {
					int nx = x + dx[d];
					
					if(!visited.contains(nx)) { //집이 없음
						queue.add(nx);
						visited.add(nx);
						house++;
						sum += dist;
						
						if(house == k) return sum; //k개 만큼 집 지음
					}
				}
			}
			dist++; //거리 증가
		}
		return sum;
	}

}
