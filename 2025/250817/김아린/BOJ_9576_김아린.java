package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_책나눠주기 {

	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			int[][] arr = new int[M][2];
			for(int j = 0; j < M; j++) {
				st = new StringTokenizer(br.readLine());
				arr[j][0] = Integer.parseInt(st.nextToken());
				arr[j][1] = Integer.parseInt(st.nextToken());
			}
			
			// b 오름차순 정렬, 같으면 a 오름차순 정렬
			Arrays.sort(arr, (o1, o2) -> o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1]);
			
			//
			boolean[] visited = new boolean[N + 1];
			int count = 0;
			
			for(int j = 0; j < M; j++) {
				int a = arr[j][0];
				int b = arr[j][1];
				
				for(int k = a; k <= b; k++) {
					if(!visited[k]) {
						visited[k] = true;
						count++;
						break;
					}
				}
			}
			
			sb.append(count + "\n");
		}
		System.out.println(sb);
	}

}
