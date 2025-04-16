import java.util.*;

class Solution {
    PriorityQueue<Integer> pq_min;
    PriorityQueue<Integer> pq_max;
    
    public int[] solution(String[] operations) {
        pq_min = new PriorityQueue<>(); //낮은 숫자 우선인 큐
        pq_max = new PriorityQueue<>(Collections.reverseOrder()); //높은 숫자 우선인 큐
        
        for(String str : operations) {
            String[] strArr = str.split(" ");
            
            String op = strArr[0];
            int data = Integer.parseInt(strArr[1]);
            
            operate(op, data);
        }
        
        if(pq_min.isEmpty()) { //비어있음
            return new int[] {0, 0};
        } else { //비어있지X
            return new int[] {pq_max.peek(), pq_min.peek()};
        }
    }
    
    public void operate(String op, int data) {
        if(op.equals("I")) { //삽입
            pq_min.add(data);
            pq_max.add(data);
        } else if(op.equals("D")) {
            if(data == 1) { //최댓값 삭제
                if(!pq_max.isEmpty()) {
                    int n = pq_max.poll();
                    pq_min.remove(n);
                }
            } else if(data == -1) { //최솟값 삭제
                if(!pq_min.isEmpty()) {
                    int n = pq_min.poll();
                    pq_max.remove(n);
                }
            }
        }
    }
}