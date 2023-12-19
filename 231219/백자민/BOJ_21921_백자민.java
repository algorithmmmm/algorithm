package day1219;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_21921_백자민 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++)
			arr[i] = Integer.parseInt(st.nextToken());
		//end input
		
		int sum = 0;
		for(int i=0;i<X;i++) {
			sum += arr[i];
		}
		
		int max = sum;
		int cnt=1;
		
		for(int i=X;i<N;i++) {
			sum += arr[i];
			sum -= arr[i-X];
			
			if(max<sum) {
				max = sum;
				cnt = 1;
			} else if(max==sum) {
				cnt++;
			}
		}
		
		if(max==0)
			System.out.println("SAD");
		else
			System.out.println(max+"\n"+cnt);
		
	}
}
