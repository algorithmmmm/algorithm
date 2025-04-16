package day240708;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class BOJ_14238_백자민 {
	
	static int[] workers; //근무자 정보 저장, 1:A, 2:B, 3:C
	
	static char[] ans; //근무 정보를 저장할 배열

	static boolean[][][][][] visit; //A,B,C의 사용 횟수와 전전에 근무한 사람 번호, 직전에 근무한 사람 번호
	
	static boolean dfs(int a, int b, int c, int back2, int back1) { 
		
		if(visit[a][b][c][back2][back1]) return false; //이전에 같은 방식을 시도한 경우 = 실패
		
		if(a==workers[1] && b==workers[2] && c==workers[3]) { //조건에 맞게 모든 근무자를 사용한 경우=성공
			return true;
		}
		
		visit[a][b][c][back2][back1] = true; //중복방지
		
		// A를 사용할 수 있는 경우
		if(a+1<=workers[1]) { 
			ans[a+b+c] = 'A'; // A 사용
			//직전에 근무한 사람은 전전으로 밀리고, 직전 근무자가 A가 된다.
			if(dfs(a+1, b, c, back1, 1)) {
				return true;
			}
		}
		
		//B를 사용할 수 있고, 직전 근무자가 B가 아닌 경우
		if(b+1<=workers[2] && back1!=2) { 
			ans[a+b+c] = 'B';
			if(dfs(a, b+1, c, back1, 2)) {
				return true;
			}
		}
		
		//C를 사용할 수 있고, 직전 근무자와 전전 근무자가 C가 아닌 경우
		if(c+1<=workers[3] && back1!=3 && back2!=3) {
			ans[a+b+c] = 'C';
			if(dfs(a, b, c+1, back1, 3)) {
				return true;
			}
		}
		
		//더 이상 근무를 짤 수 없는 경우 = 불가능한 경우
		return false;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[] history = sc.next().toCharArray();
		
		workers = new int[4];
		//각 직원별로 몇 명이 있는지 저장
		for(char h: history) {
			workers[h-64]++;
		}
		
		visit = new boolean[51][51][51][4][4]; 
		ans = new char[history.length];
		
		StringBuilder sb = new StringBuilder();
		
		//근무일 생성에 성공할 경우 ans 배열의 값을, 실패할 경우 -1을 출력
		if(dfs(0,0,0,0,0)) {
			for(char c: ans) {
				sb.append(c);
			}
		} else {
			sb.append(-1);
		}

		System.out.println(sb.toString());
	}
}
