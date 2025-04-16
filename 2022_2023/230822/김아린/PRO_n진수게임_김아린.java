import java.util.*;

class Solution {
    static char[] code = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    
    public String solution(int n, int t, int m, int p) {
        StringBuilder wholeStr = new StringBuilder(); //말할 문자열 전체
        
        for(int i = 0; i < m * t; i++) { //게임동안 나올 말할 숫자 구하기
            wholeStr.append(makeNumStr(n, i)); //1. 몫, 나머지 연산 활용
            // wholeStr.append(Integer.toString(i, n).toUpperCase()); //2. 자바 메소드 사용
        }
        
        StringBuilder answer = new StringBuilder(); //정답
        
        for(int i = p - 1; i < m * t; i += m) { //튜브가 말할 문자 구하기
            answer.append(wholeStr.charAt(i));
        }
        
        return answer.toString();
    }
    
    public String makeNumStr(int n, int i) {
        StringBuilder numStr = new StringBuilder();
        
        int q = i; //몫
        while(q >= n) { //몫이 n 이하가 될 때까지
            int r = q % n; //나머지 구함
            q /= n; //몫 갱신
            numStr.append(code[r]);
        }
        numStr.append(code[q]);
        
        return numStr.reverse().toString();
    }
}