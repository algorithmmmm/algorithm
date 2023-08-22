import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0; //정답 : 가능한 스킬트리 개수
        
        for(int i = 0; i < skill_trees.length; i++) {
            if(isPossible(skill, skill_trees[i])) { //스킬트리 가능한지
                answer++;
            }
        }
        
        return answer;
    }
    
    public boolean isPossible(String skill, String skill_tree) {
        int order = 0; //스킬의 순서 저장
        
        for(int i = 0; i < skill_tree.length(); i++) {
            int idx = skill.indexOf(skill_tree.charAt(i)); //해당 스킬의 순서 가져옴
            
            if(idx == -1) continue;
            
            if(order < idx) { //선행되지 않으면
                return false; //불가능
            } else { //선행되었으면
                order++; //스킬 순서 증감
            }      
        }
        
        return true;
    }
}