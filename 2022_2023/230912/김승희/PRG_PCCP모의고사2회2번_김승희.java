import java.util.*;
class Solution {
    public int solution(int[] ability, int number) {
        PriorityQueue<Integer> PQ = new PriorityQueue<>();
        int answer = 0;
        for(int i = 0; i < ability.length; i++){
            PQ.add(ability[i]);
            answer += ability[i];
        }
        
        for(int i = 0; i < number; i++){
            int temp = PQ.poll() + PQ.poll();
            answer += temp;
            PQ.add(temp);
            PQ.add(temp);
        }
        
        return answer;
    }
}