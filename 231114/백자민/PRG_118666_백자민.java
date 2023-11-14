package day1114;
import java.util.*;

public class PRG_118666_백자민 {

	class Solution {
	    public String solution(String[] survey, int[] choices) {
	        Map<Character, Integer> map = new HashMap<>();
	        
	        for(int i=0,size=survey.length;i<size;i++) {
	            int tmp = choices[i];
	            char category = '.';
	            
	            if(tmp==4) continue;
	            
	            if(tmp<4) {
	                category = survey[i].charAt(0);
	            } else {
	                category = survey[i].charAt(1);
	            }
	            map.put(category, map.getOrDefault(category, 0)+Math.abs(4-tmp));
	        }
	        
	        StringBuilder sb = new StringBuilder();
	        sb.append(map.getOrDefault('R', 0) >= map.getOrDefault('T', 0) ? 'R' : 'T')
	            .append(map.getOrDefault('C', 0) >= map.getOrDefault('F', 0) ? 'C' : 'F')
	            .append(map.getOrDefault('J', 0) >= map.getOrDefault('M', 0) ? 'J' : 'M')
	            .append(map.getOrDefault('A', 0) >= map.getOrDefault('N', 0) ? 'A' : 'N');
	        
	        return sb.toString();
	    }
	}
}
