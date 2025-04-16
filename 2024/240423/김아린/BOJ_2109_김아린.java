package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_순회강연 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[n][2];
		
		int dayMax = 0;
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			
			dayMax = Math.max(dayMax, arr[i][1]);
		}
		
		Arrays.sort(arr, (o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o2[0] - o1[0]); //강연료, 기간 내림차순 
		
		boolean[] checked = new boolean[dayMax + 1]; //해당 날에 강연했는지 
		int sum = 0; //정답 : 최대로 벌 수 있는 돈 
		
		for(int[] a : arr){
			int p = a[0];
			int d = a[1];
			
			for(int i = d; i >= 1; i--) { //최대한 가까운 날로 정하기 
	    		if(!checked[i]) {
			    	sum += p;
			    	checked[i] = true;
			    	break;
			    }
	    	}
		}
		
		System.out.println(sum);
	}

}
