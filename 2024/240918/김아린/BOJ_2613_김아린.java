package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_숫자구슬 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		
		int low = 0, high = 0;
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			low = Math.max(low, arr[i]);
			high += arr[i];
		}
		
		//이분탐색
		while(low <= high) {
			int mid = (low + high) / 2;
			
			int sum = 0;
			int groupCount = 1;
			
			//그룹 개수 카운트
			for(int i = 0; i < N; i++) {
				sum += arr[i];
				
				if(sum > mid) {
					sum = arr[i];
					groupCount++;
				}
			}
			
			if(groupCount <= M) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		
		//low에 해당하는 값으로 그룹을 나누기
		int sum = 0;
		int marbleCount = 0;
		
		List<Integer> list = new ArrayList<>();
		
	    for(int i = 0; i < N; i++) {
	        sum += arr[i];
	        
	        if (sum > low) {
	        	M--;
	            sum = arr[i];
	            list.add(marbleCount);
	            marbleCount = 0;
	        }
	        marbleCount++;
	        
	        if(M == N - i) break;
	    }
	    
	    while(M-- > 0){
	    	list.add(marbleCount);
	    	marbleCount = 1;
	    }
	    
	  //정답 출력
	  System.out.println(low); //최댓값
	  		
	  StringBuilder sb = new StringBuilder(); //각 그룹 구슬 개수
	  for(int n : list) {
	  		sb.append(n + " ");
	  }
	  System.out.println(sb);
	}

}
