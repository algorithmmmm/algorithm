package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_용액 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); //용액의 수
		int[] arr = new int[N]; //용액
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		//구현
		Arrays.sort(arr); //탐색을 위한 정렬
		
		int left = 0; //왼쪽 인덱스
		int right = N - 1; //오른쪽 인덱스
		int min = 2_000_000_000;
		
		int ans1 = arr[0]; //정답 : 용액1
		int ans2 = arr[N - 1]; //정답 : 용액2
		
		while(left < right) {
			int sum = arr[left] + arr[right]; //용액 혼합
			
			//목표 찾음
			if(Math.abs(sum) < min) { //특성값이 0에 가장 가까움 -> 갱신
				ans1 = arr[left];
				ans2 = arr[right];
				min = Math.abs(sum);
			}
			
			//포인터 이동
			if(sum > 0) { //오른쪽 절댓값이 더 큼
				right--; //오른쪽에서 좁히기
			} else { //왼쪽 절댓값이 더 큼
				left++; //왼쪽에서 좁히기
			}
		}
		
		//출력
		System.out.println(ans1 + " " + ans2);
	}

}
