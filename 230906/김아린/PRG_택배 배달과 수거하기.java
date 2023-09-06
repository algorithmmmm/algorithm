import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        Stack<Integer> stack_deliveries = new Stack<>();
        Stack<Integer> stack_pickups = new Stack<>();
        
        //1. 스택에 배달/수거해야 하는 집 번호 넣기
        for(int i = 0; i < n; i++) {
            if(deliveries[i] != 0) {
                stack_deliveries.add(i);
            }
            
            if(pickups[i] != 0) {
                stack_pickups.add(i);
            }
        }
        
        //2. 양쪽 중 먼거리를 정답에 누적, cap 비교하며 빼기
        long answer = 0;
        
        while(!stack_deliveries.isEmpty() || !stack_pickups.isEmpty()) {
            int top_delivery = 0;
            int top_pickup = 0;
            
            if(!stack_deliveries.isEmpty()) {
                top_delivery = stack_deliveries.peek();
            }
            
            if(!stack_pickups.isEmpty()) {
                top_pickup = stack_pickups.peek();
            }
            
            answer += 2 * Math.max(top_delivery + 1, top_pickup + 1);
            
            int count_delivery = cap; //남은 배달 가능 개수
            int count_pickup = cap; //남은 수거 가능 개수
            
            //배달하기
            while(count_delivery > 0 && !stack_deliveries.isEmpty()) {
                int idx = stack_deliveries.pop();
                
                if(deliveries[idx] <= count_delivery) {
                      count_delivery -= deliveries[idx];
                } else {
                    deliveries[idx] -= count_delivery;
                    count_delivery = 0;
                    stack_deliveries.add(idx);
                }
            }
            
            //수거하기
            while(count_pickup > 0 && !stack_pickups.isEmpty()) {
                int idx = stack_pickups.pop();
                
                if(pickups[idx] <= count_pickup) {
                      count_pickup -= pickups[idx];
                } else {
                    pickups[idx] -= count_pickup;
                    count_pickup = 0;
                    stack_pickups.add(idx);
                }
            }
        }
        
        return answer;
    }
}