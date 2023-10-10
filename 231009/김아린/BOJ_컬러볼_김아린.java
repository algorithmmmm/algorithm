package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_컬러볼 {
	
	static class Ball {
		int idx, color, size;
		
		Ball(int idx, int color, int size) {
			this.idx = idx;
			this.color = color;
			this.size = size;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		Ball[] balls = new Ball[N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			balls[i] = new Ball(i, c, s);
		}
		
		//구현
		Arrays.sort(balls, (o1, o2) -> o1.size == o2.size ? o1.color - o2.color : o1.size - o2.size);
		
		int[] answer = new int[N];
		
		int totalSum = 0; //지금까지 모든 사이즈 총합
		int[] colorSum = new int[N + 1]; //색깔별 사이즈 총합
		int[] sizeSum = new int[2001]; //사이즈별 사이즈 총합
		
		for(int i = 0; i < N; i++) {
			int idx = balls[i].idx;
			int color = balls[i].color;
			int size = balls[i].size;
	        
			totalSum += size;
			colorSum[color] += size;
			sizeSum[size] += size;
			
			answer[idx] = totalSum - colorSum[color] - sizeSum[size] + size;
			
			if(i != 0 && color == balls[i - 1].color && size == balls[i - 1].size) { //공 색깔과 사이즈가 같음
				answer[idx] = answer[balls[i - 1].idx]; //이전
			}
		}
		
		
		//출력
		StringBuilder sb = new StringBuilder();
		for(int n : answer) {
			sb.append(n + "\n");
		}
		
		System.out.println(sb);
	}

}
