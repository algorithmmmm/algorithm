package day240326;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16562_백자민 {
	
	static int N,M,K;
	
	static int[] pay, parent;
	
	static int find(int tmp) {
		if(parent[tmp]==tmp)
			return tmp;
		
		return parent[tmp] = find(parent[tmp]);
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a==b) return;
		
		if(pay[a]>pay[b]) {
			parent[a] = b;
		} else {
			parent[b] = a;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		pay = new int[N+1]; //친구비 저장
		
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			pay[i] = Integer.parseInt(st.nextToken());
		}
		
		parent = new int[N+1]; //서로가 친구일 때 더 적은 비용을 가진 친구가 부모노드가 됨
		//초기화
		for(int i=1;i<=N;i++) {
			parent[i] = i;
		}
		
		//친구 그룹 만들기(가장 적은 비용의 친구비를 요구하는 사람이 대표) 
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			union(a,b);
		}
		
		int ans=0;
		for(int i=1;i<=N;i++) {
			if(parent[i]==i) {
				ans+=pay[i];
			}
		}
		
		if(ans>K) //돈이 부족해 친구비를 모두 낼 수 없는 경우
			System.out.println("Oh no");
		else
			System.out.println(ans);
	}
}
