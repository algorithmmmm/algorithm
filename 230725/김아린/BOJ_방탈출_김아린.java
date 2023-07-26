import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class b23743 {
	static int N, M;
	static Edge[] edgeList;
	static int[] parents;
	
	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
	
	static void make() {
		parents = new int[N + 1];
		
		for(int i = 0; i < N + 1; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) {
		if(parents[a] == a) return a;
		
		return parents[a] = find(parents[a]);
		
	}
	
	static boolean union(int a , int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken()); //방의 개수
		M = Integer.parseInt(st.nextToken()); //설치할 수 있는 워프의 개수
		
		edgeList = new Edge[M + N]; //간선 배열
		
		//워프 연결
		for(int i = 0; i < M; i ++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
		
			edgeList[i] = new Edge(a, b, c);
		}
		
		//탈출구 설치
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 1; i <= N; i ++) {
			int t = Integer.parseInt(st.nextToken());
			edgeList[M + i - 1] = new Edge(0, i, t); //탈출구는 0
		}
		
		//처리
		make(); //부모 노드 만들기
		Arrays.sort(edgeList); //시간 낮은 순 오름차순 정렬
		
		//크루스칼 알고리즘
		int result = 0;
		int count = 0;
		for(Edge edge : edgeList) { //낮은 비용부터 연결
			if(union(edge.from, edge.to)) { //사이클이 존재하지 않으면 간선 선택
				result += edge.weight; //비용 갱신
				if(++count == N) break; //정점 개수 - 1 만큼 선택했으면 종료 : N + 1(탈출구 포함) - 1
			}
		}
		
		//출력
		System.out.println(result);
	}

}