package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_어항정리 {
	static int N;
	static int[][] arr;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		arr = new int[N][N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[N - 1][i] = Integer.parseInt(st.nextToken());
		}
		
		//구현
		int answer = 0;
		while(calDiff() > K) {
			//물고기가 가장 적은 어항에 한 마리씩 추가
			addFish();
			//어항 쌓기, 회전
			arrangeBowl1();
			//물고기 조절
			controlFish();
			//어항 일렬로 놓기
			unrollBowl();
			//다시 공중 부양
			arrangeBowl2();
			//물고기 조절
			controlFish();
			//어항 일렬로 놓기
			unrollBowl();
			
			answer++;
		}
		
		System.out.println(answer);
	}
	
	private static void print() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(arr[i][j] == 0) {
					System.out.print("  ");
				} else {
					System.out.print(arr[i][j] + " ");
				}
			}
			System.out.println();
		}
		
		System.out.println("------------------");
	}

	private static void arrangeBowl2() {
		//이번에는 가운데를 중심으로 왼쪽 N/2개를 공중 부양시켜 전체를 시계 방향으로 180도 회전 시킨 다음, 오른쪽 N/2개의 위에 놓아야 한다.
		//이 작업은 두 번 반복
		
		//길이가 1/4로 줄어듦
		int[][] tmp = new int[4][N / 4];
		
		for(int i = 0; i < N / 4; i++) {
			tmp[3][i] = arr[N - 1 - i][N / 4 * 3 + i];
		}
		
		for(int i = N / 4 - 1; i >= 0 ; i--) {
			tmp[2][i] = arr[N - 1 - i][N / 4 * 0 + i];
		}
		
		for(int i = 0; i < N / 4; i++) {
			tmp[1][i] = arr[N - 1 - i][N / 4 * 1 + i];
		}
		
		for(int i = N / 4 - 1; i >= 0 ; i--) {
			tmp[0][i] = arr[N - 1 - i][N / 4 * 2 + i];
		}
		
		//초기화해서 옮기기
		for(int i = 0; i < N; i++) {
			Arrays.fill(arr[i], 0);
		}
		
		for(int i = 3; i >= 0; i++) {
			for(int j = 0; j < N / 4; j++) {
				arr[N + i - 4][j] = tmp[i][j];
			}
		}
	}

	private static void unrollBowl() {
		Queue<Integer> queue = new LinkedList<>();
		
		for(int i = 1; i < N; i++) {
			for(int j = N - 1; j >= 0; j--) {
				if(arr[j][i] == 0) continue;
				queue.add(arr[j][i]);
				arr[j][i] = 0;
			}
		}
		
		int idx = 0;
		while(!queue.isEmpty()) {
			arr[N - 1][idx++] = queue.poll();
		}
	}

	private static void controlFish() {
		int[][] tmp = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(arr[i][j] != 0) {
					for(int d = 0; d < 4; d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];
						
						if(arr[i][j] == 0) continue;
						if(nx < 0 || N <= nx || ny < 0 || N <= ny) continue;
						
						int diff = arr[i][j] - arr[nx][ny];
						
						if(diff > 0) {
							tmp[i][j] -= Math.abs(diff / 5);
							tmp[i][j] += Math.abs(diff / 5);
						}
					}
				}
			}
		}
		
		//값 원래 배열에 더하기
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				arr[i][j] += tmp[i][j];
			}
		}
	}

	private static void arrangeBowl1() {
		//가장 왼쪽에 있는 어항을 그 어항의 오른쪽에 있는 어항의 위에 올리기
		//2개 이상 쌓여있는 어항을 모두 공중 부양시킨 다음, 전체를 시계방향으로 90도 회전시킨다
		//이 작업은 공중 부양시킨 어항 중 가장 오른쪽에 있는 어항의 아래에 바닥에 있는 어항이 있을때까지 반복
		
		int idx = 0; //공중 부양 시작 인덱스
	    int h = 1; //어항 높이
	    
	    while(idx + h <= N) {

	        // 회전
	        

	        //쌓기

	        
	    }
	    
	}

	private static void addFish() {
		int min = Integer.MAX_VALUE;
		Queue<int[]> queue = new LinkedList<>();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(arr[i][j] != 0) {
					if(arr[i][j] < min) {
						queue = new LinkedList<>();
						queue.add(new int[]{i, j});
						min = arr[i][j];
					} else if(arr[i][j] == min) {
						queue.add(new int[]{i, j});
					}
				}
			}
		}
		
		while(!queue.isEmpty()) {
			int[] p = queue.poll();
			arr[p[0]][p[1]]++;
		}
	}

	private static int calDiff() {
		int min = Integer.MAX_VALUE;
		int max = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(arr[i][j] != 0) {
					min = Math.min(min, arr[i][j]);
					max = Math.max(max, arr[i][j]);
				}
			}
		}
		
		return max - min;
	}
	
}
