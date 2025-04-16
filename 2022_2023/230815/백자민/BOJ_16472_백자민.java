package day0815;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class BOJ_16472_백자민 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		char[] str = br.readLine().toCharArray();
		
		int l=0,r=1,len=str.length,max=0;
		
		if(N>=len) {
			System.out.println(len);
			return;
		}
		
		HashMap<Character, Integer> map = new HashMap<>();
		
		map.put(str[0], 1); //첫 번재 글자 넣고 시작 
		int prev=0;
		while(l<=r && r<len) {
			
			if(l!=r && prev==l)
				map.put(str[r], map.getOrDefault(str[r], 0)+1);
			
			if(map.size()<=N) { //알파벳을 더 쓸 수 있는 경우 최대 길이 갱신 
				max = Math.max(max, r-l+1);
				r++;
				prev = l;
			} else {
				int tmp = map.get(str[l]);
				if(tmp==1)
					map.remove(str[l]);
				else
					map.put(str[l], tmp-1);
				l++;
			}
		}
		
		System.out.println(max);
	}
}
