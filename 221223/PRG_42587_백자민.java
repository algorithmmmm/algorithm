package day221223;
import java.util.*;

/*
 * 우선순위 큐를 활용하여 중요도 순으로 넣는다. 이 때 숫자가 클수록 중요하기 때문에 순서를 뒤집어준다.
 * 큐에서 하나씩 빼면서 location 문서가 인쇄되는 번호를 출력한다. 이 때 location은 priorities 배열의 인덱스가 된다. 
 * 
 * */

public class PRG_42587_백자민 {
    public int solution(int[] priorities, int location) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        
        for(int e : priorities) queue.offer(e);
        
        int answer = 1;
        while(!queue.isEmpty()) { //큐에 있는 모든 문서 검사 
            for(int i=0;i<priorities.length;i++){ 
                if(priorities[i]==queue.peek()){ //배열에 있는 문서 중 지금 인쇄될 차례인 문서 발견
                    if(location==i) return answer; //내가 찾는 문서면 현재 순서 리턴 
                    answer++;
                    queue.poll(); //다음 순서로 넘어가기 
                }
            }
        }
        return answer;
    }
}