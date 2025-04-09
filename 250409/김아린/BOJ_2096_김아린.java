package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_내려가기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[N][3];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] dp_min = new int[N][3];
		int[][] dp_max = new int[N][3];
		
		for(int i = 0; i < 3; i++) {
			dp_min[0][i] = arr[0][i];
			dp_max[0][i] = arr[0][i];
		}
		
		for(int i = 1; i < N; i++) {
			dp_min[i][0] = Math.min(dp_min[i - 1][0], dp_min[i - 1][1]) + arr[i][0];
			dp_min[i][1] = Math.min(dp_min[i - 1][0], Math.min(dp_min[i - 1][1], dp_min[i - 1][2])) + arr[i][1];
			dp_min[i][2] = Math.min(dp_min[i - 1][1], dp_min[i - 1][2]) + arr[i][2];
		
			dp_max[i][0] = Math.max(dp_max[i - 1][0], dp_max[i - 1][1]) + arr[i][0];
			dp_max[i][1] = Math.max(dp_max[i - 1][0], Math.max(dp_max[i - 1][1], dp_max[i - 1][2])) + arr[i][1];
			dp_max[i][2] = Math.max(dp_max[i - 1][1], dp_max[i - 1][2]) + arr[i][2];
		}
		
		int max = Math.max(dp_max[N - 1][0], Math.max(dp_max[N - 1][1], dp_max[N - 1][2]));
		int min = Math.min(dp_min[N - 1][0], Math.min(dp_min[N - 1][1], dp_min[N - 1][2]));
		
		System.out.println(max + " " + min);
	}

}
