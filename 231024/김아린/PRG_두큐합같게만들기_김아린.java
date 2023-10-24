import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Deque<Integer> deque1 = new LinkedList<>();
        Deque<Integer> deque2 = new LinkedList<>();
        
        //덱에 원소 넣기
        long sum1 = 0; //1의 총합
        for(int n : queue1) {
            deque1.add(n);
            sum1 += n;
        }
        
        long sum2 = 0; //1의 총합
        for(int n : queue2) {
            deque2.add(n);
            sum2 += n;
        }
        
        //합이 홀수면 불가능
        if((sum1 + sum2) % 2 == 1) {
            return -1;
        }
        
        //계산
        int length = (queue1.length + queue2.length) * 2; //두 큐의 길이 합
        
        int answer = 0;
        while(true) {
            //종료 조건
            if(answer >= length) return -1; //길이 넘어가면 불가능
            if(sum1 == sum2) break; //각 큐의 원소 합 같음
            
            //합 비교 후, 큰 쪽에서 빼서 작은 쪽에서 넣기
            if(sum1 > sum2) {
                int p = deque1.pollFirst();
                sum1 -= p;
                sum2 += p;
                deque2.addLast(p);
            } else {
                int p = deque2.pollFirst();
                sum2 -= p;
                sum1 += p;
                deque1.addLast(p);
            }
            
            answer++;
        }
        
        return answer;
    }
}