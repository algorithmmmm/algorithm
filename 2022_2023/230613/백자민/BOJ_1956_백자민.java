package day0613;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1956_백자민 {
	
	static final int INF = 987654321;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		int[][] track = new int[V+1][V+1];
		
		for (int i=1;i<=V;i++) {
            for (int j=1;j<=V;j++) {
                if (i!=j) {
                	track[i][j] = INF;
                }
            }
        }
		
		
		for (int e=0; e<E; e++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
 
            track[a][b] = c;
        }//end input
		
		for(int k=1;k<=V;k++) {
			for(int i=1;i<=V;i++) {
				for(int j=1;j<=V;j++) {
					if(i==j) continue;
					
					if(track[i][j] > track[i][k]+track[k][j])
						track[i][j] = track[i][k]+track[k][j];
				}
			}
		}
		
		int ans = INF;
		for(int i=1;i<=V;i++) {
			for(int j=1;j<=V;j++) {
				if(i==j) continue;
				
				if(track[i][j]!=INF && track[j][i]!=INF)
					ans = Math.min(ans, track[i][j]+track[j][i]);
			}
		}
		
		if(ans==INF) 
			System.out.println(-1);
		else 
			System.out.println(ans);
	}
}
