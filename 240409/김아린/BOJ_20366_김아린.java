package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_같이눈사람만들래 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr); //오름 차순 정렬 
		
		boolean flag = false; //최솟값이 0인거 이미 찾았는지 
		int answer = Integer.MAX_VALUE; //정답 : 차이 최솟값 
		
		for(int i = 0; i < N - 3; i++) { //엘사의 눈덩의 좌표 2개 고정 
			for(int j = i + 3; j < N; j++) {
				int elsa = arr[i] + arr[j]; //엘사 눈사람 키 
				
				int left = i + 1; //안나 눈사람 좌표 2개 투포인터로 움직임 
				int right = j - 1;
				while(left < right) {
					int anna = arr[left] + arr[right]; //안나 눈사람 키 
					
					int diff = elsa - anna;					
					answer = Math.min(answer, Math.abs(diff)); //최소 갱신 
					
					if(diff == 0) { //차이가 0이면 중단 
						flag = true;
						break;
					} else if(elsa > anna) { //엘사가 크면 안나 합 늘리기 위해 왼쪽 포인터 증가 
						left++;
					} else { //안나가 크면 합 줄이기 위해 오른쪽 포인터 감소 
						right--;
					}
				}
				
				if(flag) break;
			}
		}
		
		System.out.println(answer);
	}

}
