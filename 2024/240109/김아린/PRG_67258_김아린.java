import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        HashSet<String> set = new HashSet<>(); //보석의 종류
        for(String gem : gems) {
            set.add(gem);
        }
        
        int left = 0;
        int target = set.size(); //모든 보석 개수
        int min = 100000; //가장 짧은 구간
        int[] answer = new int[2]; //시작번호, 끝번호
        
        HashMap<String, Integer> map = new HashMap<>(); //보석, 개수
        
        for(int right = 0; right < gems.length; right++) { //right 포인터 끝까지 탐색
            map.put(gems[right], map.getOrDefault(gems[right], 0) + 1); //right 해당 보석 개수 증가
            
            while(map.getOrDefault(gems[left], 0) > 1) { //left 해당 보석이 1개 초과할때까지 포인터 증가
                map.put(gems[left], map.get(gems[left]) - 1); //해당 보석 개수 감소
                left++;
            }
            
            if (map.size() == target && right - left < min) { //모든 보석 포함 & 가장 짧은 구간 발견
                min = right - left;
                answer[0] = left + 1;
                answer[1] = right + 1;
            }
        }
        
        return answer;
    }
}