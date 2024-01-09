package day_240109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_1700_백자민 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		if(N>=K) {
			System.out.println(0);
			return;
		}
		
		List<Integer> list = new ArrayList();		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<K;i++) {
			list.add(Integer.parseInt(st.nextToken()));
		} //end input
		
		int ans=0;
		Set<Integer> plug = new HashSet<>();
		for(int k=0;k<K;k++) {
			int tmp = list.get(k);
			if(plug.contains(tmp)) continue; //이미 꽂힌 플러그
			
			if(plug.size()<N) { //꽂을 자리가 남은 경우 
				plug.add(tmp);
				continue;
			}
			
			//가장 나중에 사용될 플러그 뽑기
			int max=-1, idx=-1;
			List<Integer> last = list.subList(k+1, K);
			for(int i: plug) {
				int num=0;
				
				if(last.contains(i)) num = last.indexOf(i)+1;
				else num = K-k-1;
				
				if(num>max) {
					max = num;
					idx = i;
				}
			}
			plug.remove(idx);
			plug.add(tmp);
			ans++;
		}
		
		System.out.println(ans);
		
	}
}
