package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_합이0인네정수 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
		int n = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[n][4];
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken()); //A
			arr[i][1] = Integer.parseInt(st.nextToken()); //B
			arr[i][2] = Integer.parseInt(st.nextToken()); //C
			arr[i][3] = Integer.parseInt(st.nextToken()); //D
		}
		
		int[] ab = new int[n * n]; //ab쌍
		int[] cd = new int[n * n]; //cd쌍
		
		int idx = 0;
		for(int i = 0; i < n; i++) { //ab쌍, cd쌍 구하기
			for(int j = 0; j < n; j++) {
				ab[idx] = arr[i][0] + arr[j][1];
				cd[idx] = arr[i][2] + arr[j][3];
				idx++;
			}
		}
		
		//정렬
		Arrays.sort(ab);
		Arrays.sort(cd);
		
		//투포인터로 탐색
		long answer = 0;
		int left = 0, right = n * n - 1;
		while(left < n * n && right >= 0) {
			int sum = ab[left] + cd[right];
			int abNum = ab[left];
			int cdNum = cd[right];
			
			if(sum > 0) {
				right--;
			} else if(sum < 0) {
				left++;
			} else { //합이 0
				long abCount = 0, cdCount = 0;
				
				//달라질때까지 이동 
				while (left < n * n && abNum == ab[left]) {
					abCount++;
					left++;
				}
				while (right >= 0 && cdNum == cd[right]) {
					cdCount++;
					right--;
				}
				
				//같아지는 모든 조합 개수 더하기 
				answer += abCount * cdCount;
			}
		}
		
		System.out.println(answer);
	}

}
