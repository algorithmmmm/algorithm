package day0527;

import java.util.HashMap;

public class PRG_42578_백자민 {
	class Solution {
		static int answer = 1;
	    public int solution(String[][] clothes) {
	        HashMap<String, Integer> map = new HashMap<>();
	        
	        //옷 저장 
	        for(String[] cloth: clothes) {
	        	map.put(cloth[1], map.getOrDefault(cloth[1], 0)+1);
	        }
	        
	        // 경우의 수 계산
	        map.forEach((key, val) -> {
	        	answer *= (val+1);
	        });
	        
	        return answer-1;
	    }
	}
}
