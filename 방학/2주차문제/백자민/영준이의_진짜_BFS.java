package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * LCA 알고리즘 사용 
 * */

public class 영준이의_진짜_BFS {
	
	static int N;
	
	static int LCA(int n1, int n2, int[] depth, int[][] parent) {
		//깊이가 더 깊은 노드를 찾아서 올려야 함. -> n1이 더 깊은 노드가 되도록 설정 
		if(depth[n1] < depth[n2]) {
			int temp = n1;
			n1 = n2;
			n2 = temp;
		}
		
		//더 깊은 노드를 부모 노드로 초기화 -> 깊이 통일하기
		for(int i=18;i>=0;i--) {
			long diff = depth[n1]-depth[n2];
			if(diff >=(1<<i)) n1 = parent[n1][i];
		}
		
		if(n1==n2) return n1; //깊은 노드를 올렸을 때 두 노드가 같으면 공통 부모 
		
		//2^k부터 줄여가면서 조상노드 탐색 -> 다르면 갱신 
		for(int i=18;i>=0;i--) {
			if(parent[n1][i] != parent[n2][i]) {
				n1 = parent[n1][i];
				n2 = parent[n2][i];
			}
		}
		
		return parent[n1][0];
	}
	
	static void setParent(int[][] parent) { 
		for(int i=1;i<18;i++) {
			for(int n=1;n<=N;n++) {
				int p = parent[n][i-1];
				parent[n][i] = parent[p][i-1];
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		List<Integer>[] tree;
		List<Integer> search = new ArrayList<>();
		Queue<Integer> q = new LinkedList<>();
		
		
		for(int t=1;t<=T;t++) {
			
			N = Integer.parseInt(br.readLine());
			search.clear();
			tree = new ArrayList[N+1];
			
			for(int n=0;n<=N;n++) { //초기화 
				tree[n] = new ArrayList<Integer>();
			}
			
			//입력값 넣기
			st = new StringTokenizer(br.readLine());
			int idx = 2;
			while (st.hasMoreTokens()) {
				int temp = Integer.parseInt(st.nextToken());
				tree[idx].add(temp);
				tree[temp].add(idx);
				idx++;
			}
			
			int[] depth = new int[N+1];
			int[][] parent = new int[N+1][19];
			
			//BFS 탐색 시작
			q.clear();
			q.offer(1);
			
			boolean[] visit = new boolean[N+1];
			
			while(!q.isEmpty()) {
				int tmp = q.poll();
				visit[tmp] = true;
				search.add(tmp);
				
				int adjSize = tree[tmp].size();
				
				for(int i=0;i<adjSize;i++) {
					int adjnode = tree[tmp].get(i);
					if(!visit[adjnode]) {
						q.offer(adjnode);
						
						//바로 위 부모노드 넣기 
						parent[adjnode][0] = tmp;
						depth[adjnode] = depth[tmp]+1;
					}
				}
			}
			
			//각 노드의 2^k 승의 부모노드를 저장하기 
			setParent(parent);
			
			//거리 계산
			long dist = 0;
			for(int i=0, size=search.size()-1;i<size;i++) {
				int n1 = search.get(i);
				int n2 = search.get(i+1);
				int lca = LCA(n1, n2, depth, parent);
				dist += (depth[n1] - depth[lca] + depth[n2] - depth[lca]);
			}
			
			sb.append("#"+t+" "+dist+"\n");
			
		}
		
		System.out.println(sb.toString());
	}
}
