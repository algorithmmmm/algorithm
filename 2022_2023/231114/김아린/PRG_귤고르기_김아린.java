import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for(int t : tangerine) {
            // if(!map.containsKey(t)) {
            //     map.put(t, 1);
            // } else {
            //     int value = map.get(t);
            //     map.put(t, value + 1);
            // }
            
            map.put(t, map.getOrDefault(t, 0) + 1);
        }
        
        List<Integer> valueList = new ArrayList<>(map.values());
        Collections.sort(valueList, (o1, o2) -> o2 - o1); //내림 차순 정렬
        
        int answer = 0; //종류 수
        int count = 0;
        for(int value : valueList) {
            count += value;
            answer++;
            
            if(count >= k) break;
        }
        
        return answer;
    }
}