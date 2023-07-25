import java.util.*;

class Solution {
    public class Plan {
        String name;
        int start, playtime;
        
        public Plan(String name, int start, int playtime) {
            this.name = name;
            this.start = start;
            this.playtime = playtime;
        }
        
        public int getStart() {
            return start;
        }
    }
    
    
    public String[] solution(String[][] plans) {
        List<Plan> planList = new ArrayList<>(); //Plan 클래스로 만들어 리스트로 저장
        
        for(String[] plan : plans) {
            planList.add(new Plan(plan[0], startToMin(plan[1]), Integer.parseInt(plan[2]) ));
        }
        
        //시작순으로 오름차순 정렬 후 시작
        Collections.sort(planList, (p1, p2) -> p1.getStart() - p2.getStart());
        
        Stack<Plan> stack = new Stack<>(); //과제 대기열
        List<String> result = new ArrayList<>(); //결과
        
        for(int i = 0; i < planList.size(); i++) {
            int now = planList.get(i).start;
            int next = 0;
            if(i != planList.size() - 1)  {
                next = planList.get(i + 1).start;
            }
            
            stack.add(planList.get(i));
            
            while(!stack.isEmpty()) {
                Plan p = stack.pop();
                
                if(now + p.playtime <= next) {
                    now += p.playtime; //현재 시각 갱신
                    result.add(p.name); //과제 마침
                } else { //과재 못마침
                    stack.add(new Plan(p.name, p.start, now + p.playtime - next)); //중단하고 과제 대기열
                    // now = next;
                    break;
                }
            }
        }
        
        //잠시 멈춘 과제 모두 다시 시작
        while (!stack.isEmpty()) {
            result.add(stack.pop().name);
        }
        
        //List -> 배열 변환
        return result.toArray(new String[0]);
    }
    
    public int startToMin(String start) { //시작시간을 분으로 변환
        String[] time = start.split(":");
        
        return Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
    }
}