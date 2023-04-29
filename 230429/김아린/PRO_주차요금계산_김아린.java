import java.util.*;

class Solution {
    static HashMap<String, Integer> timeMap; //key: 차량번호, value: 누적 주차 시간
    
    public int[] solution(int[] fees, String[] records) {
        //records
        HashMap<String, String> recordMap = new HashMap<>(); //key: 차량번호, value: 시각
        timeMap = new HashMap<>();
        
        for(int i = 0; i < records.length; i++) {
            String[] strs = records[i].split(" ");
            String time = strs[0];
            String num = strs[1];
            String status = strs[2];
            
            if(status.equals("IN")) { //입차
                recordMap.put(num, time); //맵에 넣기
            } else { //출차
                String inTime = recordMap.get(num);
                recordMap.remove(num);
                
                //주차시간 계산
                int parkTime = getParkTime(inTime, time); //입차시간, 출차시간
                
                //누적 주차 시간 기록
                if(timeMap.containsKey(num)) { //누적 주차 시간 기록에 있으면
                    int minSum = timeMap.get(num);
                    timeMap.put(num, minSum + parkTime); //요금 누적시키기
                } else { //시간 기록에 없으면
                    timeMap.put(num, parkTime); //새로 넣기
                }
            }
        }
        
        //출차된 내역이 없음
        for(String key : recordMap.keySet()) {
            String inTime = recordMap.get(key);
            
            //주차시간 계산
            int parkTime = getParkTime(inTime, "23:59"); //입차시간, 출차시간
                
            //누적 주차 시간 기록
            if(timeMap.containsKey(key)) { //요금 기록에 있으면
                int minSum = timeMap.get(key);
                timeMap.put(key, minSum + parkTime); //요금 누적시키기
            } else { //요금 기록에 없으면
                timeMap.put(key, parkTime); //새로 넣기
            }
        }
        
        //차량 번호 작은 자동차 순으로 정렬
        Map<String, Integer> sortedMap = new TreeMap<>(timeMap);
        
        //요금 계산하기
        int[] answer = new int[sortedMap.size()];
        int idx = 0;
        for(String key : sortedMap.keySet()) {
            int minSum = sortedMap.get(key);
            
            System.out.println(minSum);
            
            int fee = calculateFee(fees, minSum);
            answer[idx] = fee;
            idx++;
        }
        
        return answer;
    }
    
    public int calculateFee(int[] fees, int minSum) { //요금표, 누적 주차 시간
        //fees
        int standardTime = fees[0];
        int standardFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];
        
        int fee = 0;
        if(minSum <= standardTime) { //기본 시간이하
            fee = standardFee;
        } else { //기본 시간초과
            fee = standardFee + (int)Math.ceil((minSum - standardTime) / (double)unitTime) * unitFee;
        }
        
        return fee;
    }
    
    public int getParkTime(String inTime, String outTime) { //입차시간, 출차시간
        String[] inTimeStr = inTime.split(":");
        String[] outTimeStr = outTime.split(":");
        
        int inMinute = Integer.parseInt(inTimeStr[0]) * 60 + Integer.parseInt(inTimeStr[1]);
        int outMinute = Integer.parseInt(outTimeStr[0]) * 60 + Integer.parseInt(outTimeStr[1]);
        
        return outMinute - inMinute;
    }
}