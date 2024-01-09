package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_부분합 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken()); //배열의 길이
		int S = Integer.parseInt(st.nextToken()); //구해야 하는 부분합
		
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		//구현		
		int left = 0;
		int sum = 0; //부분합 저장
		int min = 100001; //정답: 부분합의 최소 길이
		
		for(int right = 0; right < N; right++) { //오른쪽 포인터 하나씩 옮기기
			sum += arr[right]; //합에 오른쪽 원소 더하기
			
			while(left <= right && sum >= S) { //left가 right를 넘어가지 않을때까지, 합이 S이상일때까지
				min = Math.min(min, right + 1 - left); //최솟값 갱신
				sum -= arr[left];
				left++; //왼쪽 포인터 증가
			}
		}
		
		//출력
		if(min == 100001) { //합을 만드는 것이 불가능
			System.out.println(0);
		} else { //가능
			System.out.println(min);
		}
	}

}
