import java.util.*;
class Solution {
    public int solution(int k, int[] tangerine) {
        HashMap<Integer, Integer> tSize = new HashMap<>();
        for(int t : tangerine){
            int temp = tSize.getOrDefault(t, 0);
            tSize.put(t, temp+1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{
            return -a.compareTo(b);
        });
        for(Integer e: tSize.values()){
            pq.add(e);
        }
        int cnt = 0;
        while(!pq.isEmpty()){
            k -= pq.poll();
            cnt++;
            if(k <= 0){
                break;
            }
        }
        
        return cnt;
    }
}