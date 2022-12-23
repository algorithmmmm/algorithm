import java.util.*;
class Solution {
    static class Tuple{
        int idx, priority;
        public Tuple(int idx, int priority){
            this.idx = idx;
            this.priority = priority;
        }
    }
    public int solution(int[] priorities, int location) {

        Queue<Tuple> Q = new ArrayDeque<>();
        int[] count = new int[10];  // count[i] : 우선순위가 i인 작업의 개수
        int max = Integer.MIN_VALUE;    // max : 현재 가장 높은 우선순위
        for(int i = 0; i < priorities.length; i++){
            count[priorities[i]]++;
            Q.add(new Tuple(i, priorities[i]));
            max = Math.max(max, priorities[i]);
        }
        int order = 0;  // order : 몇 번째로 인쇄된 문서인지
        while(!Q.isEmpty()){
            Tuple tp = Q.poll();
            if(tp.priority == max){ // 현재 문서가 가장 우선순위가 높은 문서라면
                order++;
                if(tp.idx == location){ // 현재 문서가 내가 인쇄를 요청한 문서라면
                    break;
                }
                count[max]--;
                if(count[max] == 0){
                    while(true){
                        max--;
                        if(count[max] != 0){
                            break;
                        }
                    }
                }
            }else{
                Q.add(tp);
            }
        }
        return order;
    }
}