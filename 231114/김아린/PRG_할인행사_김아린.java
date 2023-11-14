import java.util.*;

class Solution {
    static HashMap<String, Integer> map; //제품, 제품 수량
    
    public int solution(String[] want, int[] number, String[] discount) {
        map = new HashMap<>();
        int answer = 0; //정답 : 할인 받을 수 있는 회원 등록 날짜
        
        for(int i = 0; i < want.length; i++) { //맵에 제품별 수량 저장
            map.put(want[i], number[i]);
        }
        
        //초기 구간값 세팅
        for(int i = 0; i < 10; i++) {
            if(map.containsKey(discount[i])) { //맵에 있으면 수량 줄이기
                map.put(discount[i], map.get(discount[i]) - 1);
            }
        }
        
        if(isPossible()) answer++;
        
        for(int i = 1; i <= discount.length - 10; i++) { //구간 하나씩 이동하면서
            //이전값 수량 플러스
            if(map.containsKey(discount[i - 1])) {
                map.put(discount[i - 1], map.get(discount[i - 1]) + 1);
            }
            //이전값 수량 마이너스
            if(map.containsKey(discount[i + 9])) {
                map.put(discount[i + 9], map.get(discount[i + 9]) - 1);
            }
            
            if(isPossible()) answer++;
         }
        
        return answer;
    }
    
    public boolean isPossible() { //제품별 수량 맞는지 확인
        List<Integer> valueList = new ArrayList<>(map.values());
        
        for(int n : valueList) {
            if(n > 0) { //제품이 원하는 수만큼 없음
                return false;
            }
        }
        
        return true;
    }
}