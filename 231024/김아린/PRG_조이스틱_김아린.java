class Solution {
    public int solution(String name) {
        int answer = 0; //정답 : 조작 횟수
        int length = name.length();
        int move = length - 1; //좌우 움직임 수 : 최대는 길이 - 1
        
        for(int start = 0; start < length; start++) {
            //상하 이동 횟수 구하기
            char ch = name.charAt(start);
            int up = ch - 'A'; //위로 올렸을 때 횟수
            int down = 'Z' - ch + 1; //아래로 내렸을 때 횟수
            
            answer += Math.min(up, down);
            
            //좌우 이동 횟수 구하기
            int end = start + 1; //다음 A가 아닌 문자의 인덱스
            
            while(end < length && name.charAt(end) == 'A'){ //A가 아닌 문자나오기 전까지 탐색
                end++;
            }
            
            int l1 = start; //앞 부분의 길이
            int l2 = length - end; //뒷 부분의 길이

            //정방향으로 갔을 때와
            //정방향 갔다가 역방뱡 가는 경우, 역방향 갔다가 정방향 가는 경우 비교
            move = Math.min(move, l1 + l2 + Math.min(l1, l2));      
        }
        
        return answer + move;
    }
}