package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_멀티버스2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        
        int[][] arr = new int[M][N];
        
        for(int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	
        	for(int j = 0; j < N; j++) {
        		arr[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        // 좌표압축
        for(int i = 0; i < M; i++) {
        	int[] sorted = Arrays.stream(arr[i]).sorted().distinct().toArray();
        	
        	for(int j = 0; j < N; j++) {
        		// 파라미터로 넘겨받은 배열안에 있는 key 찾아 인덱스 반환
        		arr[i][j] = Arrays.binarySearch(sorted, arr[i][j]);
        	}
        }
        
        // 균등한지 판단
        int answer = 0;
        for(int i = 0; i < M; i++) {
        	for(int j = i + 1; j < M; j++) {
        		if(Arrays.equals(arr[i], arr[j])) {
        			answer++;
        		}
        	}
        }
        
        // 출력
        System.out.println(answer);
	}

}
