package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_우체국 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[N][2]; //0: 좌표, 1: 사람 수 
		long sum = 0; //사람 총합
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			sum += arr[i][1];
		}
		
		Arrays.sort(arr, (o1, o2) -> o1[0] - o2[0]); //마을 좌표 오름차순 정렬
		
		long mid = (sum + 1) / 2; //중앙값 구함(올림한 값)
		
		int answer = 0; //정답 : 우체국 위치
		long total = 0; //지금까지 총 합
		for(int[] a : arr) {
			total += a[1];
			
			if(total >= mid) { //중앙값 이상 찾음
				answer = a[0];
				break;
			}
		}
		
		System.out.println(answer);
	}

}
