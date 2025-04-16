package day240514;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1005_백자민 {
	
	static int N,K,W;
	
	static int[] time, indegree, result; //건물 건설 소요 시간, 진입 차수, 최소 소요 시간 저장
	
	static ArrayList<Integer>[] graph; //연결 정보 저장
	
	static void sort() { //위상정렬을 수행한 후 각 건물 건설 최소 시간을 result에 저장
		Queue<Integer> q = new LinkedList<>();
		
		for(int i=1;i<=N;i++) { //진입 차수가 0인 건물부터 시작
			if(indegree[i]==0) {
				q.offer(i);
				result[i] = time[i];
			}
		}
		
		while(!q.isEmpty()) {
			int tmp = q.poll();
			
			for(int next: graph[tmp]) {
				indegree[next]--;
				result[next] = Math.max(result[next], result[tmp]+time[next]);
				
				if(indegree[next]==0) {
					q.offer(next);
				}
			}
		}
	}
	
	static void init() { //초기화 함수
		time = new int[N+1];
		indegree = new int[N+1];
		result = new int[N+1];
		
		graph = new ArrayList[N+1];
		for(int i=1;i<=N;i++) {
			graph[i] = new ArrayList<>();
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for(int t=0;t<T;t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			init();
			
			st = new StringTokenizer(br.readLine());
			for(int i=1;i<=N;i++) {
				time[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int k=0;k<K;k++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				graph[a].add(b);
				indegree[b]++;
			}
			
			W = Integer.parseInt(br.readLine());
			
			if(indegree[W]==0) { //진입차수가 0이면 바로 지을 수 있으므로 W번째 건물 건설 소요 시간 출력
				System.out.println(time[W]);
			} else { //다른 건물을 먼저 지어야 하는 경우엔 위상정렬 수행 후 최소 시간 출력
				sort();
				System.out.println(result[W]);
			}
		}
	}
}
