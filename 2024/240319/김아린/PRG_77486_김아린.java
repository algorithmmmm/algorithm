import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        HashMap<String, String> referralMap = new HashMap<>(); //나, 추천인
        
        for(int i = 0; i < enroll.length; i++) {
            referralMap.put(enroll[i], referral[i]);
        }
                
        HashMap<String, Integer> recordMap = new HashMap<>(); //판매원, 이익금 합
        
        for(int i = 0; i < enroll.length; i++) {
            recordMap.put(enroll[i], 0);
        }
        
        //분배하기
        for(int i = 0; i < seller.length; i++) {
            String person = seller[i];
            int profit = amount[i] * 100; //판매 수익
            
            int others = profit / 10; //다른 사람에게 주는 금액
            int mine = profit - others; //내가 가질 금액
            
            recordMap.put(person, recordMap.get(person) + mine);
            
            while(!referralMap.get(person).equals("-")) {
                person = referralMap.get(person);
                profit = others;
                
                if(profit < 1) break; //1원 미만이면 그만 배분
                
                others = profit / 10;
                mine = profit - others;
                
                recordMap.put(person, recordMap.get(person) + mine);
            }
        }
        
        int[] answer = new int[enroll.length];
        for(int i = 0; i < enroll.length; i++) {
            answer[i] = recordMap.get(enroll[i]);
        }
        
        return answer;
    }
}