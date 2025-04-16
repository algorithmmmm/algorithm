import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); //지금까지 막은 병사 수 저장
        
        int answer = 0; //정답 : 몇 라운드까지 막을 수 있는지
        for(int e : enemy) {
            n -= e; //병사로 막음
            maxHeap.add(e); //막은 병사에 저장
            
            if(n < 0) { //남은 병사가 없고
                if(k <= 0) { //무적권이 없으면
                    break; //라운드 종료
                }
                
                //아직 무적권이 있으면
                n += maxHeap.poll(); //지나온 라운드 중 가장 많은 적을 다시 부활시키기
                k--; //무적권 사용
            }
            
            answer++; //라운드 지남
        }
        
        return answer;
    }
}