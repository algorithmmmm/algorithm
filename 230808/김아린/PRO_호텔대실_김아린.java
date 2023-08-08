import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        //분단위로 바꾸기
        int[][] times = new int[book_time.length][2];
        
        for(int i = 0; i < book_time.length; i++) {
            for(int j = 0; j < 2; j++) {
                times[i][j] = timeToInt(book_time[i][j]); 
            }
        }
        
        //시작 시간 기준으로 오름차순 정렬
        Arrays.sort(times, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]); 
        
        //끝나는 시간 + 10(청소시간) > 다음 시작 시간 -> 새로운 방
        int answer = 1;
        PriorityQueue<Integer> queue = new PriorityQueue<>(); //대실 중
        queue.add(times[0][1]);
        
        for(int i = 1; i < times.length; i++) {
            if(queue.peek() + 10 > times[i][0]) {
                answer++;
            } else {
                queue.remove();
            }
            queue.add(times[i][1]);
        }
        
        return answer;
    }
    
    public int timeToInt(String time) {
        String[] str = time.split(":");
        return 60 * Integer.parseInt(str[0]) + Integer.parseInt(str[1]);
    }
}