package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_대표선수2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 학급 수
		int M = Integer.parseInt(st.nextToken()); // 각 학급의 학생 수
		
		int[][] arr = new int[N][M]; // 능력치
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr[i]); // 오름차순 정렬
		}
		
		// 구현
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]); // 능력치, 학급명, 학급 내 번호
        int max = 0;
		int answer = 1_000_000_000; // 최대와 최소 차이
		
		for (int i = 0; i < N; i++) {
            pq.offer(new int[]{arr[i][0], i, 0});
            max = Math.max(max, arr[i][0]);
        }
		
		while (true) {
			// 능력치가 최소인 학생 뽑기
            int[] p = pq.poll();
            int min = p[0];
            int i = p[1];
            int j = p[2];

            // 갱신
            answer = Math.min(answer, max - min);
            j++;
            
            if(j == M) {
            	break;
            }
            
            // 같은 반 다음 학생 넣기
            pq.offer(new int[]{arr[i][j], i, j});
            max = Math.max(max, arr[i][j]);
        }
		
		// 출력
		System.out.println(answer);
	}

}
