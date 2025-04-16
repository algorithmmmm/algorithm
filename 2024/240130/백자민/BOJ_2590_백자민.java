package day240130;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
반례
100
1
1
1
2
1

정답: 6
오답: 7
 
*/

public class BOJ_2590_백자민 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int piece[] = new int[7];
		
		for(int i=1;i<7;i++)
			piece[i] = Integer.parseInt(br.readLine());
		//end input
		
		int ans=0;
		//큰 조각 먼저 붙이기(6번,5번,4번)
		ans += (piece[6]+piece[5]+piece[4]); 
		
		//큰조각과 함께 사용될 작은 조각
		//5번 
		int fiveOne = piece[5]*11;
		piece[1] = Math.max(piece[1]-fiveOne, 0);
		
		//4번
		int fourTwo = piece[4]*5;
		piece[2] -= fourTwo;
			//반례: 4번+2번+1번
		if(piece[2]<0) {
			piece[1] += (piece[2]*4);
		}
		piece[2] = Math.max(piece[2], 0);
		piece[1] = Math.max(piece[1], 0);
		
		//3번 조각 붙이기
		ans += (piece[3]/4); 
		piece[3]%=4;
		if(piece[3]>0) { //추가 판 필요
			ans++; 
			if(piece[2]>0) {
				piece[2] -= (7-piece[3]*2);
				piece[1] -= (8-piece[3]);				
			} else {
				piece[1] -= (36-piece[3]*9);
			}
		}
		piece[2] = Math.max(piece[2], 0);
		piece[1] = Math.max(piece[1], 0);
		
		//2번 붙이기
		ans += (piece[2]/9);
		piece[2]%=9;
		if(piece[2]>0) { //추가 판 필요
			ans++;
			piece[1] -= (9-piece[2])*4; //남는 부분은 조각1로 채우기 
			piece[1] = Math.max(piece[1], 0);
		}

		//1번 붙이기
		ans += (piece[1]/36);
		if(piece[1]%36>0)
			ans++;

		
		System.out.println(ans);
	}
}
