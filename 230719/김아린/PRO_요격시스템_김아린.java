import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        //1. 미사일 종료지점(e)을 기준으로 오름차순 정렬
        Arrays.sort(targets, (o1, o2) -> o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1]);
        
        int prev_end = 0; //기준 : 미사일 종료시점(e)
        int answer = 0; //필요한 요격 미사일 수
        
        //2. 미사일 시작지점이 기준보다 같거나 크면 갱신
        for(int i = 0; i < targets.length; i++) {
            if(targets[i][0] >= prev_end) {
                prev_end = targets[i][1];
                answer++;
            }
        }
        
        return answer;
    }
}