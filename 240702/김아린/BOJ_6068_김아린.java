package algorithm;

import java.util.*;
import java.io.*;

public class BOJ_시간관리하기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr, (o1, o2) -> o1[1] - o2[1]); //끝내야 하는 시간 오름차순 
		
		int answer = 1_000_001; //존이 가장 늦게 일어나도 되는 시간
		int t = 0; //시작 시간 
		
		for(int i = 0; i < N; i++) {
			t += arr[i][0];
			
			if(t > arr[i][1]) { //끝내야 하는 시간 초과 
				answer = -1;
				break;
			}
			
			answer = Math.min(answer, arr[i][1] - t); //늦게 일어나도 되는 최소 시간 갱신 
		}
		
		System.out.println(answer);
	}

}
