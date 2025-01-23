package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_좌표압축 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N]; //원본 배열
		int[] sortedArr = new int[N]; //정렬된 배열
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = sortedArr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(sortedArr); //오름차순 정렬
		
		HashMap<Integer, Integer> map = new HashMap<>(); //숫자, 순위
		int count = 0; //순위
		for(int x : sortedArr) {
			if(!map.containsKey(x)) {
				map.put(x, count);
				count++;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int x : arr) {
			int rank = map.get(x);
			sb.append(rank + " ");
		}
		
		System.out.println(sb);
	}

}
