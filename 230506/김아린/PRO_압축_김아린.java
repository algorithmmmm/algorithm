import java.util.*;

class Solution {
    public int[] solution(String msg) {
        HashMap<String, Integer> dictionary = new HashMap<>(); //사전 : key(문자), v(인덱스)
        List<Integer> list = new ArrayList<>(); //정답 리스트
        
        //1. 사전 초기화
        for(int i = 1; i <= 26; i++) {
            dictionary.put(String.valueOf((char)(i + 64)), i);
        }
        
        //문장 압축 진행
        boolean isDone = false; //완료 여부
        for(int i = 0; i < msg.length(); i++) {
        	if(isDone) break; //완료 여부 확인
            
            if(msg.length() == 1) {
        		list.add(dictionary.get(msg));
        	}
        	
           //2. 사전에서 현재 입력과 일치하는 가장 긴 문자열(w) 찾기
            for(int j = 1; j < msg.length(); j++) {
                String wc = msg.substring(i, i + j);
            
                if(!dictionary.containsKey(wc)) { //사전에 없으면
                    dictionary.put(wc, dictionary.size() + 1); //사전에 등록하기
                    String w = msg.substring(i, i + j - 1);
                    list.add(dictionary.get(w)); //색인 번호 정답에 추가
                    
                    if(w.length() >= 2) { //w가 2글자 이상이면
                    	i += w.length() - 1; //그만큼 i 더해주기
                    }
                    
                    break;
                } else { //사전에 있으면 그냥 넘어가지만,
                    if (i + j - 1 == msg.length() - 1) { //마지막 문자라면 색인 번호 추가
                        String w = msg.substring(i, i + j);
                        list.add(dictionary.get(w));
                        
                        isDone = true; //압축 완료
                        break;
                    }
                }
            }
            
        }
        
        //정답
        int[] answer = new int[list.size()];
        
        int i = 0;
        for(int n : list) {
        	answer[i] = n;
        	i++;
        }
        
        return answer;
    }
}