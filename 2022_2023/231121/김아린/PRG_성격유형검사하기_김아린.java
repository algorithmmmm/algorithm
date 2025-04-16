import java.util.*;

class Solution {
    HashMap<Character, Integer> map;
    
    public String solution(String[] survey, int[] choices) {
        map = new HashMap<>();
        
        for(int i = 0; i < survey.length; i++) {
            String question = survey[i];
            int choice = choices[i];
            
            if(1 <= choice && choice <= 3) { //비동의
                char type = question.charAt(0);
                map.put(type, map.getOrDefault(type, 0) + (4 - choice));
            } else if(5 <= choice && choice <= 7) { //동의
                char type = question.charAt(1);
                map.put(type, map.getOrDefault(type, 0) + (choice - 4));
            }
        }
        
        //유형 정하기
        String answer = "";
        
        answer += getResult('R', 'T');
        answer += getResult('C', 'F');
        answer += getResult('J', 'M');
        answer += getResult('A', 'N');
        
        return answer;
    }
    
    public String getResult(Character t1, Character t2) {
        int v1 = map.getOrDefault(t1, 0);
        int v2 = map.getOrDefault(t2, 0);
        
        return (v1 >= v2) ? String.valueOf(t1) : String.valueOf(t2);
    }
    
}