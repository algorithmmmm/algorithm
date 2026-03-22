package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_호숫가의개미굴 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		long[] arr = new long[N];
		long sum = 0; // 쪽방 합
	
		StringTokenizer st = new StringTokenizer("");
		for(int i = 0; i < N; i++) {
			while (!st.hasMoreTokens()) {
		        st = new StringTokenizer(br.readLine());
		    }
			arr[i] = Long.parseLong(st.nextToken());
			// 쪽방에 개미를 넣는 것이 최대
			sum += arr[i];
		}
		
		// 구현
		// 쪽방에 개미가 없는 경우
		if(sum == 0){
            System.out.println(N / 2);
            return;
        }
		
		// 나머지 경우
		long answer = sum;

        // 0이 아닌 구간 찾기
        int start = 0;
        for(int i = 0; i < N; i++) {
            if(arr[i] > 0) {
                start = i;
                break;
            }
        }
        
        int len = 0; // 연속된 0 구간 길이
        for(int i = 1; i <= N; i++) {
            int idx = (start + i) % N; // 원형 처리
            if(arr[idx] == 0) {
                len++;
            }else {
                if(len > 0) {
                    answer += (len + 1) / 2;
                    len = 0;
                }
            }
        }

        System.out.println(answer);
	}

}
