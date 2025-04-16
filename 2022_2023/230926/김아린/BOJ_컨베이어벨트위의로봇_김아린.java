import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] belt;
	static boolean[] hasRobot;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		belt = new int[2 * N]; //컨베이어 벨트
		hasRobot = new boolean[N]; //로봇이 있는지
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 2 * N; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}
		
		int answer = 1;
		while(true) {
			//1. 한 칸 회전
			rotateBelt();
			//2. 한 칸 이동할 수 있다면 이동
			moveRobot();
			//3. 로봇 올리기
			putRobot();
			
			if(check() >= K) break;
			
			answer++;
		}
		
		System.out.println(answer);
	}

	public static int check() {
		int count = 0;
		for(int n : belt) {
			if(n == 0) count++;
		}
		
		return count;
	}
	
	public static void rotateBelt() {
		//벨트 회전
		int tmp_belt = belt[2 * N - 1];
		
		for(int i = 2 * N - 1; i > 0; i--) {
			belt[i] = belt[i - 1];
		}
		belt[0] = tmp_belt;

		//로봇 회전
		for(int i = N - 1; i > 0; i--) {
			hasRobot[i] = hasRobot[i - 1];
		}
		hasRobot[0] = false;
	}
	
	public static void moveRobot() {
		hasRobot[N - 1] = false; //로봇 내리기
		
		for(int i = N - 2; i >= 0; i--) {
			if(hasRobot[i]) {
				if(!hasRobot[i + 1] && belt[i + 1] >= 1) { //이동하려는 칸에 로봇이 없으며, 내구도가 1 이상 남아있음
					belt[i + 1]--; //내구도 감소
					hasRobot[i + 1] = true; //로봇 이동
					hasRobot[i] = false;
				}
			}
		}
	}
	
	public static void putRobot() {
		if(belt[0] > 0) {
			belt[0]--; //내구도 감소
			hasRobot[0] = true;
		}
	}

}