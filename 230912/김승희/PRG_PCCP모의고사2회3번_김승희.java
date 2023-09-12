import java.util.*;
class Solution {
    public int solution(int[] menu, int[] order, int k) {
        int time = 0;   // time : 시간
        int num = 1;    // num : 인원 수
        int answer = 1; // answer : 최대 인원 수
        int endT = menu[order[0]];   // endT : 음료 받는 시간
        Queue<Integer> Q = new ArrayDeque<>();    // 음료 받는 시간
        Q.add(endT);  // 첫 손님이 주문한 거 넣기
        for(int i = 1; i < order.length; i++){
            time += k;
            // 손님 나가기
            while(!Q.isEmpty()){
                if(Q.peek() > time) break;
                Q.poll();
                num--;
            }
            answer = Math.max(answer, num);
            // 손님 입장
            endT = Math.max(endT, time) + menu[order[i]];
            Q.add(endT);
            num++;
            answer = Math.max(answer, num);
        }
        return answer;
    }
}