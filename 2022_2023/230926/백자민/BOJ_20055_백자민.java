package day0926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20055_백자민 {
	
	static int N,K;
	
	static int[] A;
	
	static boolean[] belt;
	
	static boolean check() { //내구도가 0인 칸 세기
		int cnt=0;
		for(int i: A) {
			if(i==0) cnt++;
			
			if(cnt>=K) return false;
		}
		
		return true;
	}
	
	static int move() {
		int step = 0;
		
		while(check()) {
			
			step++;
			
			//1번: 벨트 회전
			int temp = A[2*N-1]; //마지막꺼
			for(int i=2*N-1;i>0;i--)
				A[i] = A[i-1];
			A[0] = temp;	
			
			for(int i=N-1;i>0;i--) {
				belt[i] = belt[i-1];
			}
			
			belt[0] = false;
			belt[N-1] = false;
			
			//2번: 로봇 이동
			for(int i=N-1;i>0;i--) {
				if(!belt[i-1] || belt[i] || A[i]<1) continue;
				A[i]--;
				belt[i] = true;
				belt[i-1] = false;
			}
			
			//3번: 올리기
			if(A[0]<=0) continue;
			belt[0] = true;
			A[0]--;
		}
		
		return step;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		A = new int[2*N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0,size=2*N;i<size;i++) {
			A[i] = Integer.parseInt(st.nextToken());
		} //end input
		
		belt = new boolean[N];
		
		System.out.println(move());
	}
}
