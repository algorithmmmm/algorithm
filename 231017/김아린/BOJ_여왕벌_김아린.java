import java.io.*;
import java.util.*;

public class Main {
	static int M;
	static int[][] graph;
	static int[] size;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		graph = new int[M][M];
		
		for(int i = 0; i < M; i++) { //1로 초기화
			Arrays.fill(graph[i], 1);
		}
		
		//구현
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int zero = Integer.parseInt(st.nextToken());
			int one = Integer.parseInt(st.nextToken());
			int two = Integer.parseInt(st.nextToken());
			
			getSize(zero, one, two); //왼쪽 열, 위쪽 행 크기 배열화 하기
			
			growEdge(); //왼쪽 열, 위쪽 행 자라기
		}
		
		growElse(); //나머지 자라기
		
		//출력
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < M; j++) {
				sb.append(graph[i][j] + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void getSize(int zero, int one, int two) {
		size = new int[2 * M - 1];
		
		for(int i = zero; i < zero + one; i++) {
			size[i] = 1;
		}
		
		for(int i = zero + one; i < 2 * M - 1; i++) {
			size[i] = 2;
		}
	}

	public static void growEdge() {
		for(int i = 0; i < size.length / 2; i++) { //왼쪽 열 (0,0 제외)
			graph[M - 1 - i][0] += size[i];
		}
		
		for(int i = size.length / 2; i < size.length; i++) { //왼쪽 열
			graph[0][i - (size.length / 2)] += size[i];

		}
	}

	public static void growElse() {
		for(int i = 1; i < M; i++) {
			for(int j = 1; j < M; j++) {
				int L = graph[i][j - 1]; //왼쪽
				int D = graph[i - 1][j - 1]; //왼쪽 위
				int U = graph[i - 1][j]; //위쪽
				
				int max = Math.max(L, Math.max(D, U));
				
				graph[i][j] = max;
			}
		}
	}

}

//다른 풀이 : 더 빠른 버전
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[] size = new int[M * 2 + 1];
		
		for(int i = 0; i < M; i++) { //1로 초기화
			Arrays.fill(size, 1);
		}
		
		//구현
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			//가장자리 자라기
			int idx = 0;
			for(int j = 0; j <= 2; j++) {
				int n = Integer.parseInt(st.nextToken());
				
				for(int k = 0; k < n; k++) {
					size[idx++] += j;
				}
			}
		}
		
		//출력
		StringBuilder sb = new StringBuilder();
		
		for(int i = M - 1; i >= 0; i--) {
			sb.append(size[i] + " ");
			
			for(int j = M; j < 2 * M - 1; j++) { //위에것 그대로 내려옴
				sb.append(size[j] + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

}