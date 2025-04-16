package day0620;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2531_백자민 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int max=0;
        
        st = new StringTokenizer(br.readLine());
 
        int N = Integer.parseInt(st.nextToken()); 
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        
        int [] sushi = new int[N];
        for(int i=0;i<N;i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }
        
        int [] visit = new int[d+1];
        int count=0;
        
        for(int i=0;i<k;i++) {
            if(visit[sushi[i]]==0) count++;
            visit[sushi[i]]++;
        }
        
        max = count;
        int start=1, end=k;
        while(true) {
            
            if(visit[sushi[start-1]]==1) count--;
            visit[sushi[start-1]]--;
            
            if(visit[sushi[end]]==0) count++;
            visit[sushi[end]]++;
            
            if(visit[c]==0) max = Math.max(max, count+1);
            else max = Math.max(max, count);
            
            start++; end++;
            if(end==N) end=0;
            if(start==N) break;
        }
        System.out.println(max);
	}
}
