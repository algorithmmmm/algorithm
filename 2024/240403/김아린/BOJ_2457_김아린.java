package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_공주님의정원 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[N][2]; 
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			int month1 = Integer.parseInt(st.nextToken());
			int day1 = Integer.parseInt(st.nextToken());
			int month2 = Integer.parseInt(st.nextToken());
			int day2 = Integer.parseInt(st.nextToken());
			
			arr[i][0] = month1 * 100 + day1;
			arr[i][1] = month2 * 100 + day2;
		}
		
		Arrays.sort(arr, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
		
		int answer = 0;
		int now = 301; //현재 기준이 되는 날
		int endMax = 0; //최대로 늘릴 수 있는 종료일 
		boolean found = false; //꽃 심었는지 여부 
		
		for(int i = 0; i < N; i++) {
			int start = arr[i][0];
			int end = arr[i][1];
			
			if(start <= now && end > endMax) { //심을 수 있는 꽃 범위 늘려감 
				endMax = end;
				found = true;
			}
			
			if(start > now && end > endMax) { //새로 꽃 심을 수 있음 
				if(start > endMax) { //끊김 
					answer = 0;
					break;
				}
				now = endMax;
				endMax = end;
				answer++;
				found = false;
			}
			
			if(endMax > 1130) { //이미 11.30 지남 
				answer++;
				break;
			}
			
			if(i == N - 1 && !found) { //마지막 종료 
				if(end > 1130) {
					answer++;

				} else {
					answer = 0;
				}
			}
		}
		
		System.out.println(answer);
	}

}
