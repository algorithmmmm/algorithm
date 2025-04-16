import java.util.*;

class Solution {
    public int[] solution(String s) {
        int count_trans = 0;
        int count_zero = 0;

        while(!s.equals("1")) {
            //0 제거(0의 개수 세기)
            int zero = 0;
            for(int i = 0; i < s.length(); i++) {
                if(s.charAt(i) == '0') {
                    zero++;
                }
            }

            //1만 남은 문자열 길이 얻기
            int one = s.length() - zero;

            //이진수 변환
            s = Integer.toString(one, 2);

            //카운트
            count_trans++;
            count_zero += zero;
        }

        return new int[] {count_trans, count_zero};
    }
}