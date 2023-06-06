import java.util.*;

class Solution {
    static HashMap<Character, Integer> friendsMap; //카카오프랜즈
    static boolean[] visited; //순열 시 방문 체크
    static int[] result; //순열 결과 배열
    static int answer; //정답(=경우의 수)
    static String[] datas; //data 복사
    
    public int solution(int n, String[] data) { //조건의 개수, 각 프랜즈가 원하는 조건
        answer = 0;
        datas = data;
        
        //순열 결과 배열에 숫자로 저장하기 위해 0~7까지 번호 부여
        friendsMap = new HashMap<Character, Integer>();
        
        friendsMap.put('A', 0);
        friendsMap.put('C', 1);
        friendsMap.put('F', 2);
        friendsMap.put('J', 3);
        friendsMap.put('M', 4);
        friendsMap.put('N', 5);
        friendsMap.put('R', 6);
        friendsMap.put('T', 7);
        
        result = new int[8];
        visited = new boolean[8];
        
        permutation(0); //순열 : 가능한 모든 프렌즈 순서를 구함
        
        return answer;
    }
    
    public void permutation(int depth) {
        if(depth == 8) {
            if(isCorrect()) { //모든 조건에 맞는지 체크
                answer++;
            }
            return;
        }
        
        //순열 결과 생성
        for(int i = 0; i < 8; i++) { 
            if(!visited[i]) {
                visited[i] = true;
                result[depth] = i; //결과에 저장
                permutation(depth + 1);
                visited[i] = false;
            }
        }
    }
    
    public boolean isCorrect() {
        for(String data : datas) {
            int f1 = result[friendsMap.get(data.charAt(0))]; //순열로 나온 프렌즈1의 자리
            int f2 = result[friendsMap.get(data.charAt(2))]; //순열로 나온 프렌즈2의 자리
            char sign = data.charAt(3); //부호
            int d = data.charAt(4) - '0' + 1; //간격(두 프렌즈 사이에 있는 다른 프렌즈의 수)
            
            int distance = Math.abs(f1 - f2); //둘 사이의 간격 계산
            
            //순열의 경우가 둘 사이의 간격이 조건과 일치하는지 확인하기
            //조건과 안맞는 경우를 먼저 거름
            if(sign == '=') { //같음
                if(distance != d) {
                    return false;
                }
            } else if(sign == '<') { //미만
                if(distance >= d) {
                    return false;
                }
            } else if(sign == '>') { //초과
                if(distance <= d) {
                    return false;
                }
            }
        }
        
        return true;
    }
}