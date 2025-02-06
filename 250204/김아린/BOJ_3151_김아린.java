package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_합이0 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		HashMap<Integer, Integer> countMap = new HashMap<>(); //숫자 카운트 맵
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			countMap.put(arr[i], countMap.getOrDefault(arr[i], 0) + 1);
		}
		
		Arrays.sort(arr); //정렬
		
		long answer = 0;
		for(int i = 0; i < N - 2; i++) { //포인터 하나 고정
			int left = i + 1;
			int right = N - 1;
			
			while(left < right) {
				int sum = arr[i] + arr[left] + arr[right];
				
				if(sum == 0) {
					if(arr[left] == arr[right]) {
						answer += right - left;
					} else {
						answer += countMap.get(arr[right]);
					}
				}
				
				if(sum <= 0) { //합이 더 작음
					left++;
				} else { //합이 더 큼
					right--;
				}
			}
		}
		
		System.out.println(answer);
	}

}
