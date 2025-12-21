package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_쓰레기치우기 {
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        List<Integer>[] garbage = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            garbage[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    garbage[i].add(j);
                }
            }
        }
        
        int count = 0;
        for (int i = 0; i < N; i++) {
            if(garbage[i].isEmpty()) continue; // 쓰레기 없음
            count++; // 새 청소기 놓음
            
            int max = garbage[i].get(garbage[i].size() - 1); // 그 행의 가장 끝에 있는 쓰레기 인덱스
            for(int j = i + 1; j < N; j++) { // 다음 행 탐색
                if(garbage[j].isEmpty()) continue; // 쓰레기 없음

                // 위에서 가장 오른쪽에 있는 쓰레기 위치보다 작으면 스킵 
                if(garbage[j].get(garbage[j].size() - 1) < max) continue;

                // 쓰레기 제거
                int nextMax = garbage[j].get(garbage[j].size() - 1);
                while(!garbage[j].isEmpty() && garbage[j].get(garbage[j].size() - 1) >= max) {
                    garbage[j].remove(garbage[j].size() - 1);
                }

                // 오른쪽 끝 위치 갱신
                max = nextMax;
            }
        }

        System.out.println(count);
	}

}
