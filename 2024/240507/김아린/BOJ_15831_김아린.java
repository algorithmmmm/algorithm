package algorithm;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_준표의조약돌 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		char[] arr = br.readLine().toCharArray();
		
		int left = 0, right = 0;
		int bCount = 0, wCount = 0;
		int max = 0;
		
		while(right < N) {
			if(bCount > B) { //검정 개수 B 초과 
				if(arr[left] == 'B') {
					bCount--;
				} else {
					wCount--;
				}
				
				left++;
			} else { //하얀 개수 W 미만, 조건에 맞는 경우 
				if(arr[right] == 'B') {
					bCount++;
				} else {
					wCount++;
				}
				
				right++;
			}
			
			if(bCount <= B && wCount >= W) { //조건에 맞으면 최대 갱신 
				max = Math.max(max, right - left);
			}
		}
		
		System.out.println(max);
	}

}
