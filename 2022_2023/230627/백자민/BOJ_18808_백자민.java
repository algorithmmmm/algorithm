package day0627;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_18808_백자민 {
	static int N, M, K, ans;
	static int[][] board;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		ans = 0;
		
		for(int k=0;k<K;k++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			int[][] sticker = new int[n][m];
			
			for(int i=0;i<n;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<m;j++) {
					sticker[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			find(sticker, n, m);
		}
		
		System.out.println(ans);
	}
	
	static void find(int[][] sticker, int n, int m) {
		
		for(int d=0;d<4;d++) {
			if(d>0)
				sticker = rotate(sticker, n, m);
			
			n = sticker.length;
			m = sticker[0].length;
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) { //(0,0)부터 탐색 시작: 가작 위쪽과 왼쪽에 스티커를 붙이고 싶어하니까 
					
					if(i+n>N || j+m>M) //범위를 벗어나는 경우 패스 
						break;
					
					if(put(sticker,n,m,i,j))
						return; //가능한 범위 내에서 가장 위쪽이고 가장 왼쪽인 데에 붙였으니까 다음 스티커로 넘어가
				}
			}
		}
	}
	
	static int[][] rotate(int[][] sticker, int n, int m) {
		int[][] copy = new int[m][n];
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				copy[j][n-i-1] = sticker[i][j];
			}
		}
		
		return copy;
	}
	
	static boolean put(int[][] sticker, int n, int m, int ti, int tj) {
		//일단 붙일 수 있는지 검사 먼저
		for(int i=ti;i<ti+n;i++) {
			for(int j=tj;j<tj+m;j++) {
				if(board[i][j]==1 && sticker[i-ti][j-tj]==1) //붙일 자리에 이미 스티커를 붙인 경우 
					return false;
			}
		}
		
		//진짜 붙이기 
		for(int i=ti;i<ti+n;i++) {
			for(int j=tj;j<tj+m;j++) {
				if(sticker[i-ti][j-tj]==1) {
					board[i][j] = 1;
					ans++;
				}
			}
		}
		
		return true;
	}
}
