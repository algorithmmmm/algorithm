package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_정육점 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr, (o1, o2) -> o1[1] == o2[1] ? o2[0] - o1[0] : o1[1] - o2[1]); // 가격 낮은 순, 무게 높은 순 정렬
		
		int sum = 0; // 무게의 합
		int prev_c = 0; // 이전 비용 저장
		int answer = Integer.MAX_VALUE; // 정답: 최소 비용
		int tmp = 0; // 비용 저장
		
		for(int i = 0; i < N; i++) {
			sum += arr[i][0];
			
			if(prev_c == arr[i][1]) { // 직전과 같은 무게이면
				tmp += arr[i][1]; // 비용 누적
			} else {
				tmp = arr[i][1];
			}
			
			if(sum >= M) { // 최소 갱신
				answer = Math.min(answer, tmp);
			}
			
			prev_c = arr[i][1];
		}
		
		if(sum < M) answer = -1; // 불가능한 경우
		
		System.out.println(answer);
	}

}
