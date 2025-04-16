package day240624;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_19538_백자민 {
	
	static int N,M;
	
	static int[] rumor,	neighbor; 
	
	static Queue<Integer> q;
	
	static ArrayList<Integer>[] graph; //주변인 관계를 나타내는 인접리스트
	
	static void bfs() {
				
		while(!q.isEmpty()) {
			int tmp = q.poll();
			
			for(int next: graph[tmp]) {
				neighbor[next]++; //내 주변인에게 루머를 믿는다고 저장
				
				if(rumor[next]<0 && (graph[next].size()+1)/2<=neighbor[next]) {
					rumor[next] = rumor[tmp]+1; //시간 기록
					q.offer(next); //다음 타임에 루머 유포
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		graph = new ArrayList[N+1];
		for(int i=1;i<=N;i++) {
			graph[i] = new ArrayList<>();
		}
		
		StringTokenizer st;
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			while(true) {
				int tmp = Integer.parseInt(st.nextToken());
				if(tmp==0) break;
				
				graph[i].add(tmp);
			}
		}
		
		rumor = new int[N+1]; //i번 사람이 루머를 믿은 시간 기록
		Arrays.fill(rumor, -1);
		
		
		M = Integer.parseInt(br.readLine());
		q = new LinkedList<>(); //루머 유포자를 저장하는 큐
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<M;i++) {
			int tmp = Integer.parseInt(st.nextToken());
			rumor[tmp] = 0; //초기에 루머를 믿는 사람=0초
			q.offer(tmp);
		} //end input
		
		neighbor = new int[N+1]; //루머를 아는 주변인의 수 기록
		bfs();
		
		//print
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<=N;i++) {
			sb.append(rumor[i]+" ");
		}
		System.out.print(sb.toString());
	}
}
