package day0725;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_14658_백자민 {
	
static int N,M,L,K;
	
	static List<int[]> star;
	
	static int countStar(int x, int y) {
		int cnt = 0;
		for(int[] s: star)
			if(x<=s[0] && x+L>=s[0] && y<=s[1] && y+L>=s[1])
				cnt++;
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		star = new ArrayList<int[]>();
		for(int k=0;k<K;k++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			star.add(new int[] {x,y});
		}
		
		int ans = Integer.MIN_VALUE;
		for(int x=0;x<K;x++) {
			for(int y=0;y<K;y++) {
				int sx = star.get(x)[0]; //별똥별 하나에서 x 가져오고 
				int sy = star.get(y)[1]; //다른 하나에서 y 가져옴 
				
				ans = Math.max(ans, countStar(sx, sy));
			}
		}
		System.out.println(K-ans);
		
		
	}
}
