import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] people;
	static ArrayList<Integer>[] graph;
	static int[][] dp;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); //마을 개수
		people = new int[N + 1]; //마을 주민 수
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 1; i < N + 1; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}
		
		graph = new ArrayList[N + 1]; //마을 관계 리스트
		for(int i = 1; i < N + 1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N - 1; i++) { //마을 관계 연결
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[a].add(b);
			graph[b].add(a);
		}
		
		//구현
		dp = new int[N+1][2]; //0: 우수마을x, 우수마을o
		
		for(int i = 1; i < N + 1; i++) {
			dp[i][0] = 0;
			dp[i][1] = people[i];
		}
		
		visited = new boolean[N + 1];
		
		visited[1] = true;
		dfs(1); //1을 루트로 두고 탐색 시작
		
		//출력
		int answer = Math.max(dp[1][0], dp[1][1]);
		
		System.out.println(answer);
	}

	public static int dfs(int v) {
		visited[v] = true;
		
		for(int i : graph[v]) {
			if(!visited[i]) {
				dfs(i);
				
				dp[v][0] += Math.max(dp[i][0], dp[i][1]); //둘 중 큰 것이 값
				dp[v][1] += dp[i][0]; //현재 우수 마을 -> 다음 우수 마을x
			}
		}
		
		return Math.max(dp[v][0], dp[v][1]);
	}

}