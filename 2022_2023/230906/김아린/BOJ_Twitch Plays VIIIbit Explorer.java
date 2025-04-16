package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_25319_2 {
	static int N, M, S, K;
	static char[][] graph;
	static boolean[][] visited;
	static String id;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static char[] order = {'U', 'D', 'L', 'R'}; //명령어
	static char[] result;
	
	static int answer_C, answer_L;
	static String answer_str;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); //세로 길이
		M = Integer.parseInt(st.nextToken()); //가로 길이
		S = Integer.parseInt(st.nextToken()); //아이디의 길이
		
		graph = new char[N][M]; //그래프
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < M; j++) {
				graph[i][j] = line.charAt(j);
			}
		}
		
		id = br.readLine(); //아이디
		
		int count = 0; //그래프에 있는 id에 있는 문자 개수 
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(id.indexOf(graph[i][j]) != -1) { //id에 있는 문자
					count++;
				}
			}
		}
		
		answer_C = count / id.length(); //강화 가능한 수
		answer_L = 0;
		answer_str = "";
		
		//탐색
		visited = new boolean[N][M];
		result = new char[1000000];
		
		K = M * N;
		
		dfs(0, 0, 0, 0, 0, 0);
		
		//출력
		System.out.println(answer_C + " " + answer_L);
		System.out.println(answer_str);
	}
	
	public static void dfs(int x, int y, int idx, int power, int l, int move) { //행, 열, 주워야 하는 문자의 아이디 인덱스, 강화 횟수, 명령어 문자열 길이
		//주워야 하는 문자면 줍기
		if(graph[x][y] == id.charAt(idx)) {
//			System.out.println(graph[x][y] + " == " + id.charAt(idx));
//			System.out.println(x + " " + y + " " + idx + " " + power + " " + l + " 줍기");
			result[l] = 'P'; //행동 추가
			l++;
			
			if(idx == id.length() - 1) { //주운 아이템이 아이디와 일치
//				System.out.println("강화");
				power++; //강화
				idx = 0; //주운 아이템 조기화
			} else {
				idx++; //아이디 인덱스 증가
			}
		}
		
		//탈출 가능
		if(power == answer_C && x == N - 1 && y == M - 1) {
			answer_L = l;
			
			StringBuilder sb  = new StringBuilder();
			for(int i = 0; i < l; i++) {
				sb.append(result[i]);
			}
			
			answer_str = sb.toString();
			
			return;
		}
		
		//사방 탐색
		for(int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(0 <= nx && nx < N && 0 <= ny && ny < M) {
				if(!visited[nx][ny]) {
					result[l] = order[d]; //행동 추가
					visited[x][y] = true;
//					System.out.println(nx + " " + ny + " " + idx + " " + power + " " + (l + 1) +  " 탐색 " + (move + 1));
					dfs(nx, ny, idx, power, l + 1, move + 1); //탐색
					visited[x][y] = false;
				}
			}
		}
	}

}
