package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_사냥꾼 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[M];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < M; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr); //이분탐색 위한 정렬 
		
		int count = 0; //정답 : 잡은 동물 수 
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(b > L) continue; //사정거리보다 y 좌표값이 높음 
			
			//이분 탐색 
			int left = 0; //사대 인덱스의 최소
			int right = M - 1; //사대 인덱스의 최대 
			
			while(left <= right) {
				int mid = (left + right) / 2;
				
				if(a + b - L <= arr[mid] && arr[mid] <= a - b + L) { //사대가 조건 안에 들음  
					count++;
					break;
				} else if(arr[mid] < a + b - L) { //조건보다 작음 
					left = mid + 1;
				} else { //조건보다 큼 
					right = mid - 1;
				}
			}
		}
		
		System.out.println(count);
	}

}
