package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_달력 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] calendar = new int[367];
		
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			calendar[start] += 1;
			calendar[end + 1] += -1;
		}
		
		//탐색
		int area = 0; //정답: 코팅지 면적
		boolean opened = false; //구간 열렸는지
		int start = 0, end = 0;
		int sum = 0; //구간 합
		int max = 0; //구간 내의 최대 일정 수
		
		for(int i = 1; i <= 366; i++) {
			if(!opened && calendar[i] > 0) { //구간 열림
				start = i;
				opened = true;
			}
			
			if(opened) { //연속된 구간임
				sum += calendar[i];
				max = Math.max(max, sum);
				
				if(sum == 0) { //연속 구간 종료
					end = i;
					area += (end - start) * max; //면적 더함
					opened = false; //초기화
					sum = 0;
					max = 0;
				}
			}
		}
		
		System.out.println(area);
	}

}
