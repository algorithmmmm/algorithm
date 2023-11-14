package day1114;
import java.util.*;

public class PRG_92341_백자민 {

	class Solution {
	    
	    static int calc(int time, int[] fees) {
	        if(time<=fees[0]) {
	            return fees[1];  
	        } else {
	            return fees[1] + (int) Math.ceil((time - fees[0]) / (double)fees[2]) * fees[3];
	        }
	    }
	    
	    static int changeTime(String s) {
	        String[] time = s.split(":");
	        return Integer.parseInt(time[0])*60 + Integer.parseInt(time[1]);
	    }
	    
	    public int[] solution(int[] fees, String[] records) {
	        Map<String, Integer> parking = new HashMap<>(); //key: 차량번호, value: 입차시간
	        Map<String, Integer> totalFee = new HashMap<>(); //key: 차량번호, value: 누적시간(->누적금액)
	        
	        for(String r: records) {
	            String[] tmp = r.split(" "); //0:시간, 1:번호, 2:출차여부    
	            int tmpTime = changeTime(tmp[0]);
	            
	            if(tmp[2].equals("IN")) {
	                parking.put(tmp[1], tmpTime);
	            } else {
	                totalFee.put(tmp[1], totalFee.getOrDefault(tmp[1],0)+(tmpTime-parking.get(tmp[1])));
	                parking.remove(tmp[1]);
	            }
	        }
	        
	        if (!parking.isEmpty()) {
	            int finalT = 23*60+59;
	            for(String carnum: parking.keySet()) {
	                totalFee.put(carnum, totalFee.getOrDefault(carnum, 0)+(finalT-parking.get(carnum)));
	            }
	        } //미출차 차량까지 누적시간 계산 완료 
	        
	        //누적시간->누적금액으로 변경
	        for(String carnum: totalFee.keySet()) {
	            int cost = calc(totalFee.get(carnum), fees);
	            totalFee.put(carnum, cost);
	        }
	        
	        //오름차순으로 정렬해서 출력
	        List<String> keyset = new ArrayList<>(totalFee.keySet());
	        Collections.sort(keyset);
	        int[] answer = new int[keyset.size()];
	        
	        for(int i=0,size=answer.length;i<size;i++) {
	            answer[i] = totalFee.get(keyset.get(i));
	        }
	        
	        return answer;
	    }
	}
}
