import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        Stack<Integer> stack = new Stack<>(); //주식 가격 인덱스 저장
        int[] answer = new int[prices.length];
        
        for(int i = 0; i < prices.length; i++) {
            while(!stack.isEmpty() && prices[i] < prices[stack.peek()]) { //새로 들어오는 가격이 상단에 있는 가격보다 작음
                int idx = stack.pop(); //스택에서 빼서
                answer[idx] = i - idx; //초 계산해서 정답에 저장
            }
            
            stack.push(i); //스택에 넣기
        }
        
        //끝나고 나면 스택에 남아있는 게 있음
        for(int idx : stack) {
            answer[idx] = prices.length - 1 - idx; //초 계산해서 정답에 저장
        }
        
        return answer;
    }
}