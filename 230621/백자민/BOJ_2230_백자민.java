package day0620;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2230_백자민 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] nums = new int[N];
		
		for(int i=0;i<N;i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(nums);
		
		int start=0, end=0, min=Integer.MAX_VALUE, temp=0;
		
		while(start<N) {
			
			temp = nums[start]-nums[end];
			
			if(temp<M) {
				start++;
				continue;
			}
			
			if(temp==M) {
				min = M;
				break;
			}
			
			min = Math.min(min, temp);
			end++;
		}
		
		System.out.println(min);
	}
}
