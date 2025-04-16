package day1128;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_2831_백자민 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] male = new int[N];
		int[] female = new int[N];
		
		StringTokenizer mst = new StringTokenizer(br.readLine());
		StringTokenizer fst = new StringTokenizer(br.readLine());
		
		for(int i=0;i<N;i++) {
			male[i] = Integer.parseInt(mst.nextToken());
			female[i] = Integer.parseInt(fst.nextToken());
		} //end input
		
		Arrays.sort(male);
		Arrays.sort(female);
		
		int m=0,f=N-1,ans=0;
		
		while(m<N && f>=0) {
			
			//부호가 같은 경우는 매칭될 수 없음
			if(male[m]<0 && female[f]<0) {
				m++; 
				continue;
			}
			if(male[m]>0 && female[f]>0) {
				f--;
				continue;
			}
			
			if(male[m]<0 && female[f]>0) {
				if(Math.abs(male[m])>female[f]) {
					ans++;
					f--;
					m++;
				} else {
					f--;
				}
			} else if(male[m]>0 && female[f]<0) {
				if(male[m] < Math.abs(female[f])) {
					ans++;
					f--;
					m++;
				} else {
					f--;
				}
			}
			
		}
		
		System.out.println(ans);
	}
}
