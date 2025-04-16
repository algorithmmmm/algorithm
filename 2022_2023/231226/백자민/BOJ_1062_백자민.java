package day1226;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1062_백자민 {
	
	static int N,K,max;
	
	static String[] wordlist;
	
	static void comb(int idx, int start, int tmp) {
		
		if(idx==K) {
			int cnt = 0;
			for(String word: wordlist) {
				boolean flag = true;
				
				for(int i=0,size=word.length();i<size;i++) {
					if((tmp & 1<<(word.charAt(i)-'a'))==0) {
						flag = false;
						break;
					}
				}
				
				if(flag)
					cnt++;
			}
			
			max = Math.max(max, cnt);
			return;
		}
		
		for(int i=start;i<26;i++) { //알파벳으로 조합 생성
			if((tmp & 1<<i)>0) continue; //이미 고른 알파벳
			
			comb(idx+1, i+1, tmp|1<<i);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		if(K<5) {
			System.out.println(0);
			return;
		}
		
		if(K==26) {
			System.out.println(N);
			return;
		}
		
		wordlist = new String[N];
		for(int i=0;i<N;i++) {
			String tmp = br.readLine();
			wordlist[i] = tmp.substring(4,tmp.length()-4);
		}
		
		max = 0;
		int temp = 0;
		
		//a,c,i,n,t 방문체크
		temp |= 1<<('a'-'a');
		temp |= 1<<('c'-'a');
		temp |= 1<<('i'-'a');
		temp |= 1<<('n'-'a');
		temp |= 1<<('t'-'a');
		K -= 5; //5개는 기본으로 알아야 함.
		
		comb(0,0,temp);
		System.out.println(max);
	}
}
