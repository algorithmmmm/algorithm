package algorithm;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_코딩은예쁘게 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int target = Integer.parseInt(st.nextToken());
			arr[i] -= target;
		}
		
		//구현
		int answer = 0;
		while(!isAllZero(arr)) {
			int pre_value = arr[0];
			int pre_idx = 0;
			int min = Math.abs(pre_value);
			
			for(int i = 0; i < N; i++) {
				// 연속 불가능
				if(arr[i] == 0 || (pre_value * arr[i] <= 0)) {
					answer += Math.abs(min);
					
					for(int j = pre_idx; j < i; j++) {
						if(arr[j] > 0) {
							arr[j] -= min;
						} else {
							arr[j] += Math.abs(min);
						}
						
					}
					
					min = arr[i];
					pre_value = arr[i];
					pre_idx = i;
					
					if(i == N - 1) {
						answer += Math.abs(min);
						if(arr[i] > 0) {
							arr[i] -= min;
						} else {
							arr[i] += Math.abs(min);
						}
					}
					continue;
				}
				
				min = Math.min(min, Math.abs(arr[i]));
				
				if(i == N - 1) {
					answer += Math.abs(min);
					for(int j = pre_idx; j < N; j++) {
						if(arr[j] > 0) {
							arr[j] -= min;
						} else {
							arr[j] += Math.abs(min);
						}
					}
				}
			}
		}
		
		//출력
		System.out.println(answer);
	}

	public static boolean isAllZero(int[] arr) {
		for(int a : arr) {
			if(a != 0) return false;
		}
		return true;
	}

}
