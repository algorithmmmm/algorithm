import java.util.*;

class PRG_프린터_김아린 {
    public int solution(int[] priorities, int location) {
        Queue<Integer> queue = new LinkedList<>();

        for(int i = 0; i < priorities.length; i++) { //인쇄 대기 목록에 순서대로 넣기
            queue.add(i);
        }

        int answer = 1; //인쇄 순서 : 1번째부터 시작
        while(true) {
            int j = queue.poll(); //가장 앞에 있는 문서(J)를 대기목록에서 꺼냄

            boolean isOkay = true;
            for(int q : queue) {
                if(priorities[j] < priorities[q]) { //나머지 인쇄 대기목록에서 J보다 중요도가 높은 문서가 한 개라도 존재하면
                    queue.add(j); //J를 대기목록의 가장 마지막에 넣음
                    isOkay = false;
                    break;
                }
            }

            if(isOkay) { //인쇄됨
                if(j == location) { //요청한 문서이면, 정답
                    return answer;
                }

                answer++; //아니면 순서 증가
            }
        }
    }
}