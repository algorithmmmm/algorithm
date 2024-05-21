package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_제자리멀리뛰기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int d = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] stones = new int[n + 2];
		stones[0] = 0; //시작점 
		stones[n + 1] = d; //끝지점 
		
		for(int i= 1; i < n + 1; i++) {
			stones[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(stones); //오름차순 정렬 
		
		System.out.println(binarySearch(stones, d, n, m));
	}

	public static int binarySearch(int[] stones, int d, int n, int m) {
		int answer = 0;
		int left = 0;
		int right = d;
		
		while(left <= right) {
			int mid = (left + right) / 2;
			
			//mid 길이로 건널 때 제거된 돌섬 수 구하기
			int target = 0; //제거된 돌섬 수
			int prev = 0;
			
			for(int i = 1; i < n + 2; i++) {
				if(stones[i] - prev < mid) { //건너뛸 거리보다 작음 
					if(i != n + 1) {
						target++; //돌섬 제거 
					}
				} else { //건너뛸 거리보다 큼 
					prev = stones[i]; //기준 초기화 
				}
			}
			
			if(target > m) { //제거된 돌섬 수가 목표보다 더 많으면 
				right = mid - 1; //점프 길이 줄여서 더 적은 돌섬 제거하기(많이 밟기)
        	} else { //목표보다 적으면  
        		left = mid + 1; //점프 길이 늘려서 더 많은 돌섬 제거하기(적게 밟기)
        		answer = mid;
        	}
		}
		
		return answer;
	}

}
