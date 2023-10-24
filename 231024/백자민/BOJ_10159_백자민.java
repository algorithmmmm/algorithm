package day1024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10159_백자민 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		boolean[][] matrix1 = new boolean[N+1][N+1];
		boolean[][] matrix2 = new boolean[N+1][N+1];
		
		StringTokenizer st;
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			matrix1[a][b] = true;
			matrix2[b][a] = true;
		}
		
		for(int k=1;k<=N;k++) {
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=N;j++) {
					if(matrix1[i][k] && matrix1[k][j])
						matrix1[i][j] = true;
					if(matrix2[i][k] && matrix2[k][j])
						matrix2[i][j] = true;
				}
			}
		}
		
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				if(matrix2[i][j])
					matrix1[i][j] = matrix2[i][j];
			}
		}
		

		
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<=N;i++) {
			int cnt = 0;
			for(int j=1;j<=N;j++) {
				
				if(i==j) continue;
				
				if(!matrix1[i][j]) cnt++;
			}
			sb.append(cnt+"\n");
		}
		
		System.out.println(sb.toString());
	}
}
