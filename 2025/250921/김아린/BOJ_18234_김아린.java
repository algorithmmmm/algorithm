package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_당근훔쳐먹기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 당근 종류
		int T = Integer.parseInt(st.nextToken()); // 일
		
		int[][] arr = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken()); // w : 초기 맛
			arr[i][1] = Integer.parseInt(st.nextToken()); // p : 영양제
		}
		
		// 구현
		Arrays.sort(arr, (o1, o2) -> o1[1] == o2[1] ? o2[0] - o1[0] : o1[1] - o2[1]); // 영양제 오름차순 정렬
		
		long answer = 0;
		for(int i = 0; i < N; i++) {
			answer += (long)arr[i][0] + (long)(i + T - N) * arr[i][1];
		}
		
		// 출력
		System.out.println(answer);
		
	}

}
