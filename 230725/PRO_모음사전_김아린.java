import java.util.*;

class Solution {
    static String[] vowels = {"A", "E", "I", "O", "U"};
    static String[] result;
    static List<String> list;
    
    public int solution(String word) {
        list = new ArrayList<>();
        
        for(int i = 1; i <= 5; i++) {
            result = new String[i];
            puermutation(0, i, word);
        }
        
        Collections.sort(list); //정렬

        //이 단어가 사전에서 몇 번째 단어인지 카운팅
        int answer = 1;
        for(String str : list) {
            if(str.equals(word)) {
                break;
            }
            answer++;
        }
        
        return answer;
    }
    
    public void puermutation(int depth, int num, String word) {
        if(depth == num) {
            //완성된 단어 저장
            String voca = "";
            for(String str : result) {
                voca += str;
            }
            
            list.add(voca);
            
            return;
        }
        
        for(int i = 0; i < 5; i++) {
            result[depth] = vowels[i];
            puermutation(depth + 1, num, word);
        }
    }
}