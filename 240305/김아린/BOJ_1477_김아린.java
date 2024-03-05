package algorithm;

import java.util.*;
import java.io.*;

public class BOJ_휴게소세우기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N + 2]; //고속도로 시작점과 끝지점 포함한 휴게소 배열
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		arr[N] = 0; //고속도로 시작점
		arr[N + 1] = L; //고속도로 끝지점
		
		Arrays.sort(arr); //이분탐색 위한 정렬
		
		int answer = binarySearch(arr, M, L); //타겟 : 설치할 휴게소 수
		
		System.out.println(answer);
	}

	public static int binarySearch(int[] arr, int m, int l) {
		int low = 1; //휴게소간 거리 최소값
		int high = l; //휴게소간 거리 최대값
		int answer = high; //정답 : 거리 최대값의 최소
		
		while(low <= high) {
			int mid = (low + high) / 2; //휴개소간 거리 기준
			int count = 0; //더 짓는 휴게소 개수
			
			//휴게소 설치하기
			for(int i = 1; i < arr.length; i++) {
				count += (arr[i] - arr[i - 1] - 1) / mid; //필요한만큼 더 지음
			}
			
			if(count <= m) { //휴게소 부족
				answer = mid; //최소 갱신
				high = mid - 1; //범위 줄여서 많이 설치하기
			} else {
				low = mid + 1; //범위 늘리기
			}
		}
		
		return answer;
	}

}
