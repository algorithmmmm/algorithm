package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_팀빌딩 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int left = 0;
		int right = N - 1; //양 끝에 포인터
		int answer = 0;
		while(left < right) { //만나기 전까지 옮긴다
			answer = Math.max(answer, (right - left - 1) * Math.min(arr[left], arr[right]));
			
			if(arr[left] < arr[right]) { //최소인 포인터를 옮긴다
				left++;
			} else {
				right--;
			}
		}
		
		System.out.println(answer);
	}

}
