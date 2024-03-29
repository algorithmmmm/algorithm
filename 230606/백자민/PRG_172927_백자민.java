package day0606;

import java.util.Arrays;
import java.util.Comparator;

public class PRG_172927_백자민 {
	class Solution {
	    public int solution(int[] picks, String[] minerals) {
	        int answer = 0;
	        int num = picks[0] + picks[1] + picks[2];
	        int[][] section = new int[minerals.length/5+1][3];

	        for(int i=0, size=minerals.length;i<size;i++) {
	        	
	        	if(num<=0) break;
	        	
	        	if(minerals[i].equals("diamond")) {
	        		section[i / 5][0] += 1;
                    section[i / 5][1] += 5;
                    section[i / 5][2] += 25;
	        	} else if(minerals[i].equals("iron")) {
	        		section[i / 5][0] += 1;
                    section[i / 5][1] += 1;
                    section[i / 5][2] += 5;
	        	} else {
	        		section[i / 5][0] += 1;
                    section[i / 5][1] += 1;
                    section[i / 5][2] += 1;
	        	}
	        	
	            if(i%5==4) num--;            
	        }
	        
	        Arrays.sort(section, new Comparator<int[]>() {
	            @Override
	            public int compare(int[] o1, int[] o2) {
	                if(o1[2] < o2[2]) 
	                    return 1;
	                else 
	                    return -1;
	            }
	        });
	        
	        for(int i=0, pick=0, size=section.length;i<size;i++) {
	            while(pick<3 && picks[pick]==0) 
	            	pick++;
	            
	            if(pick==3) break;
	            
	            picks[pick]--;
	            answer += section[i][pick];
	        }
	        return answer;
	    }
	}
}
