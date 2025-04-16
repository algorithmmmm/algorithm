import java.util.*;
import java.util.stream.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = Arrays.stream(numbers) //stream 생성
                        .mapToObj(String::valueOf) //String으로 변환
                        .sorted((o1, o2) -> (o2 + o1).compareTo(o1 + o2)) //내림차순 정렬 : 3 30 34 -> 34 3 30
                        .collect(Collectors.joining()); //문자를 이어붙임
        
        return answer.startsWith("0") ? "0" : answer; //000인 경우는 0으로 반환
    }
}