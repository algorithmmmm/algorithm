import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int[][] tiredness = {{1, 1, 1}, {5, 1, 1}, {25, 5, 1}}; //곡괭이-광물별 피로도 표
        final int diamond = 0;
        final int iron = 1;
        final int stone = 2;
        
        //1. 미네랄 배열을 5개씩 끊어서 ‘캘 수 있는’ 가중치의 합을 구함
        int sum_pick = 0;
        for(int pick : picks) {
            sum_pick += pick;
        }
        
        int need_pick = (int)Math.ceil(minerals.length / (double)5); //광물을 다 캐기 위해 필요한 곡괭이 수
        int pick_length = (sum_pick < need_pick) ? sum_pick : need_pick; //작업을 위해 쓸 곡괭이 수
        int required_length = (sum_pick < need_pick) ? sum_pick * 5 : minerals.length; //캘 광물의 수
        
        int[][] weights = new int[need_pick][3]; //곡괭이 별 가중치의 합 배열 : 묶음번호-곡괭이
        
        //가중치의 합 구하기
        int idx = -1;
        
        for(int i = 0; i < minerals.length; i++) {
            if(i % 5 == 0) { //5개씩 묶음
                idx++;
            }
            weights[idx][0] += tiredness[diamond][mineralToInt(minerals[i])];
            weights[idx][1] += tiredness[iron][mineralToInt(minerals[i])];
            weights[idx][2] += tiredness[stone][mineralToInt(minerals[i])];
            
            if(i == required_length) break; //필요한 광물수만큼 계산했으면 종료
        }
        
        //2. 돌 곡괭이 기준 가중치의 합 내림차순으로 정렬함
        Arrays.sort(weights, (o1, o2) -> o2[2] - o1[2]);
        
        //3. 다이아부터 써서 피로도를 구함
        int answer = 0; //정답 : 피로도 합
        int pick_count = 0; //쓴 곡괭이 수
        boolean isDone = false; //종료 여부
        for(int i = 0; i < picks.length; i++) {
            for(int j = 0; j < picks[i]; j++) {
                //해당 곡괭이의 피로도 합 더함
                answer += weights[pick_count][i];
                pick_count++;
                
                if(pick_count == need_pick) { //곡괭이 다 썼으면 종료
                    isDone = true;
                    break;
                }
            }
            
            if(isDone) break;
        }
        
        return answer; 
    }
    
    public int mineralToInt(String mineral) { //String으로 들어오는 광물명을 숫자로 변환
        switch(mineral) {
            case "diamond" : 
                return 0;
            case "iron" : 
                return 1;
            case "stone" :
                return 2;
        }
        return 0;
    }
    
}