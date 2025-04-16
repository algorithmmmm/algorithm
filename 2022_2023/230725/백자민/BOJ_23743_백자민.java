package day0725;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_23743_백자민 {
	
	static int N,M;
	
	static int[] parent;
	
	static List<Node> nlist;
	
	static int find(int tmp) {
		if(tmp==parent[tmp])
			return tmp;
		return parent[tmp] = find(parent[tmp]);
	}
	
	static boolean union(int x, int y) {
		int xP = find(x);
		int yP = find(y);
		
		//서로 같은 부모면 이미 같은 트리 안에 있음
		if(xP==yP) 
			return false;
		
		//부모가 다를 경우에는 트리를 합친다. 
		parent[xP] = yP;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		nlist = new LinkedList<>();
		parent = new int[N+1];
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			nlist.add(new Node(s,e,w));
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			int w = Integer.parseInt(st.nextToken());
			nlist.add(new Node(0,i,w));
			//0번 노드(없는 노드)를 만들고 가중치는 탈출구의 설치 비용으로 한다.
			//모든 정점을 0번 노드에 탈출구를 놓는다고 가정하고 리스트에 넣는다. 
		}

		//최소 비용으로 간선을 연결하기 위해 정렬하는데, 이전에 만들어둔 0번노드와 탈출구가 같이 정렬되니까 
		//만약에 탈출구를 설치하는 비용이 더 작으면 탈출구를 설치하도록 나올 것.
		//그럼 0번 노드랑 연결된 것처럼 보이지만, 사실 0번노드는 없는 노드니까 각자가 다른 트리가 되는 거고 
		//가중치는 탈출구를 만드는 비용이 더해질 것. 
		nlist.sort(null);
		
		
		for(int i=0;i<=N;i++) 
			parent[i] = i;
		
		int time = 0;
		for(Node n: nlist) {
			//트리를 합친 경우, 즉 워프나 탈출구를 설치한 경우 설치 시간을 더한다. 
			if(union(n.s, n.e)) {
				time += n.w;
			}
		}
	}
	
	static class Node implements Comparable<Node> {
		int s, e, w;
		public Node(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}
		
		@Override
		public int compareTo(Node n) {
			return this.w-n.w;
		}
	}
}
