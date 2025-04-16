package day240521;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_6209_백자민 {
	
	static int D,N,M;
	
	static int[] stones;
	
	static int calc(int mid) {
		int sum=0,tmp=0;//sum=제거한 돌 개수, tmp=거리를 살펴볼 기준점이 되는 돌 번호
		
		for(int i=1;i<=N+1;i++) {
			if(stones[i]-stones[tmp]<mid) { //현재 돌 사이 거리가 mid보다 작다면 mid가 최소거리가 될 수 없으므로 돌 제거
				sum++;
			} else { //최소 거리를 지켰다면 기준점 옮김
				tmp=i;
			}
		}
		
		return sum;
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        D = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        stones = new int[N+2]; //시작점과 도착점도 고려해줘야 함
        stones[0] = 0;
        stones[N+1] = D;
        for(int i=1;i<=N;i++) {
        	stones[i] = Integer.parseInt(br.readLine());
        } //end input
        Arrays.sort(stones); //오름차순 정렬
        
        int l=0,r=D;
        //0~D 범위 내에서 최소 거리를 가정한 후
        //해당 최소 거리를 지키며 돌을 뺐을 때 제거한 돌의 개수 조건에 따라 포인터를 이동
        while(l<=r) {
        	int mid = (l+r)/2; //최소 거리 가정
        	
        	int removeCnt = calc(mid);//최소 거리가 mid일 때 제거하는 돌의 개수
        	if(removeCnt>M) { //제거한 돌이 M개보다 많을 경우엔 최소 거리가 더 작아야 함(더 적은 돌을 제거하기 위함).
        		r = mid-1;
        	} else { 
        		l = mid+1;
        	}
        }
        
        System.out.println(r);
    }
}