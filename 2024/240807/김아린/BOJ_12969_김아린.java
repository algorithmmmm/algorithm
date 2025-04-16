package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_ABC {
	static int N, K;
	static boolean[][][][] visited;
	static char[] result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		visited = new boolean[31][31][31][(30 * 29 / 2) + 1]; //A개수, B개수, C개수로 k개쌍 만드는거 가능한지
		result = new char[31]; //문자열 결과 저장 
		
		StringBuilder sb = new StringBuilder();
		if(dfs(0, 0, 0, 0)) { //S 만들기 가능
			for(int i = 0; i < N; i++) {
				sb.append(result[i]);
			}
		} else { //불가능 
			sb.append(-1);
		}
		
		System.out.println(sb);
	}

	public static boolean dfs(int a, int b, int c, int k) {
		//종료 조건
		if(a + b + c == N) {
			return (k == K) ? true : false;
		}
		
		//가지치기
		if(visited[a][b][c][k]) return false;
		
		visited[a][b][c][k] = true;
		
		//A 추가되면 쌍 개수 변하지 않음 
		result[a + b + c] = 'A';
		if(dfs(a + 1, b, c, k)) return true;
		
		//B 추가되면 쌍 개수 A 개수만큼 추가됨
		result[a + b + c] = 'B';
		if(dfs(a, b + 1, c, k + a)) return true;
		
		//C 추가되면 쌍 개수 A, B 개수만큼 추가됨
		result[a + b + c] = 'C';
		if(dfs(a, b, c + 1, k + a + b)) return true;
		
		return false;
	}

}
