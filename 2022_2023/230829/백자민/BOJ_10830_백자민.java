package day0829;

import java.util.Scanner;

public class BOJ_10830_백자민 {
	
	static int N;
	
	static int MOD = 1000;
	
	static int[][] matrix;
	
	static int[][] multiply(int[][] a, int[][] b) {
		int[][] result = new int[N][N];
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				for(int k=0;k<N;k++) {
					result[i][j] += a[i][k] * b[k][j];
				}
				result[i][j] %= MOD;
			}
		}
		
		return result;
	}
	
	static int[][] pow(long b) {
		if(b==1)
			return matrix;
		
		int[][] tmp = pow(b/2); //분할정복으로 제곱 연산하기 
		int[][] calcMatrix = multiply(tmp, tmp); //행렬곱셈 연산 
		
		if(b%2==1)
			return multiply(calcMatrix, matrix); //홀수면 한 번 더 곱해줘야 함
		
		return calcMatrix;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		long B = sc.nextLong();
		
		matrix = new int[N][N];
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				matrix[i][j] = sc.nextInt();
				matrix[i][j] %= MOD;
			}
		}
		
		int[][] answer = pow(B);
		
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				sb.append(answer[i][j]+" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
