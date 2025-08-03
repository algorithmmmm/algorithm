package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_달력 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[367];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			arr[S] += 1;
			arr[E + 1] -= 1;
		}
		
		// 누적합
        for (int i = 1; i <= 366; i++) {
            arr[i] += arr[i - 1];
        }
		
		int answer = 0; // 정답: 면적
		int width = 0; // 가로 길이
		int height = 0; // 세로 길이
		
		for(int i = 1; i <= 366; i++) {
			if(arr[i] > 0) { // 일정 시작
				width++;
				height = Math.max(height, arr[i]);
			} else { // 일정 끝남
				answer += width * height;
				width = 0;
                height = 0;
			}
		}
		
		System.out.println(answer);
	}

}
