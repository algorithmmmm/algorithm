package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_루머 {
	static int N;
	static List<Integer>[] graph;
	static HashMap<Integer, Integer> countMap;
	static HashMap<Integer, Integer> answerMap;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		graph = new ArrayList[N + 1];
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		countMap = new HashMap<>(); // 사람 번호, 주변인 중 루머 믿는 사람 수 
		answerMap = new HashMap<>(); // 사람 번호, 루머 믿기 시작한 시간 
		
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			while(true) {
				int person = Integer.parseInt(st.nextToken());
				if(person == 0) break;
				
				graph[i].add(person);
			}
			
			answerMap.put(i, -1);
		}
		
		int M = Integer.parseInt(br.readLine());
		
		int[] starts = new int[M];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			starts[i] = Integer.parseInt(st.nextToken());
		}
		
		bfs(starts);
		
		List<Integer> keySet = new ArrayList<>(answerMap.keySet());
		Collections.sort(keySet);
		
		StringBuilder sb = new StringBuilder();
		for(int num : keySet) {
			sb.append(answerMap.get(num) + " ");
		}
		
		System.out.println(sb);
	}

	public static void bfs(int[] starts) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] checked = new boolean[N + 1];
		
		for(int start : starts) {
			queue.add(start);
			checked[start] = true;
			answerMap.put(start, 0);
		}
		
		int time = 1;
		while(!queue.isEmpty()) {
			int size = queue.size();
			
			for(int s = 0; s < size; s++) {
				int p = queue.poll();
				
				for(int person : graph[p]) {
					if(checked[person]) continue;
					
					countMap.put(person, countMap.getOrDefault(person, 0) + 1);
					
					if(countMap.get(person) >= graph[person].size() / 2.0) { // 주변인 절반 이상 루머 믿음 
						queue.add(person);
						checked[person] = true;
						answerMap.put(person, time);
					}
				}
			}
			time++;
		}
	}

}
