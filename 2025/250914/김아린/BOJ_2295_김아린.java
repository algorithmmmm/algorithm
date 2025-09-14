package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_세수의합 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		// 구현
		Arrays.sort(arr); // 오름차순 정렬
		
		// x + y + z = k -> x + y = k - z
		// x + y = target
        for(int k = N - 1; k >= 0; k--){ // k
            for(int z = 0; z <= k; z++){ // z
                int target = arr[k] - arr[z];

                // 투포인터로 구하기
                int left = 0, right = k;
                while(left <= right){
                    int sum = arr[left] + arr[right];
                    if(sum == target){
                    	System.out.println(arr[k]);
                        return;
                    } else if(sum < target){
                        left++;
                    } else{
                        right--;
                    }
                }
            }
        }
	}

}
