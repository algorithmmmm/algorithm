import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Integer>[] graph;
	static boolean[] visited;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken()); //트리의 정점의 수
		int R = Integer.parseInt(st.nextToken()); //루트의 번호
		int Q = Integer.parseInt(st.nextToken()); //쿼리의 수
		
		graph = new ArrayList[N + 1]; //트리
		for(int i = 1; i < N + 1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N - 1; i++) { //간선 정보
			st = new StringTokenizer(br.readLine(), " ");
			int U = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			
			graph[U].add(V);
			graph[V].add(U);
		}
		
		//구현
		dp = new int[N + 1];
		
		for(int i = 1; i < N + 1; i++) { //자기자신 포함
			dp[i] = 1;
		}
		
		visited = new boolean[N + 1];
		
		dfs(R); //루트로 탐색 시작
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < Q; i++) {
			int q = Integer.parseInt(br.readLine());
			sb.append(dp[q] + " \n");
		}
		System.out.println(sb);
	}

	public static int dfs(int v) {
		visited[v] = true; //방문 체크
		
		for(int i : graph[v]) { //인접한 노드
			if(!visited[i]) {
				dp[v] += dfs(i); //탐색
			}
		}
		
		return dp[v];
	}

}