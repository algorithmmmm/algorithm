package algorithm;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BOJ_부품대여장 {
	static int L;
	static int F;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken()); // 정보의 개수
		String str = st.nextToken(); // 대여기간 : DDD/hh:mm
        
		// 대여기간 분으로 환산 
        String[] dayHourMinute = str.split("[/:]");
        int days = Integer.parseInt(dayHourMinute[0]);
        int hours = Integer.parseInt(dayHourMinute[1]);
        int minutes = Integer.parseInt(dayHourMinute[2]);
		
        L = days * 24 * 60 + hours * 60 + minutes;
        
        F = Integer.parseInt(st.nextToken()); // 벌금
        
        HashMap<String, String> recordMap = new HashMap<>(); // 부품+회원닉네임, 시각 
        HashMap<String, Long> fineMap = new HashMap<>(); // 이름, 내야하는 벌금 
        
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine(), " ");
        	
        	String dateTime = st.nextToken() + " " + st.nextToken(); // 날짜와 시간
            String P = st.nextToken(); // 부품 이름 
            String M = st.nextToken(); // 회원 닉네임
            
            String key = P + M;
            
            if(!recordMap.containsKey(key)) {
            	recordMap.put(key, dateTime);
            } else {
            	String value = recordMap.remove(key); // 반납 
            	
            	long fine = getFine(value, dateTime); //벌금 계산 
            	fineMap.put(M, fineMap.getOrDefault(M, (long) 0) + fine); // 벌금에 추가
            }
        }
        
        List<String> keySet = new ArrayList<>(fineMap.keySet());
        Collections.sort(keySet);
        
        StringBuilder sb = new StringBuilder();
        boolean isPossible = false;
        
        for(String name : keySet) {
        	if(fineMap.get(name) != 0) {
        		isPossible = true;
        		sb.append(name + " " + fineMap.get(name)).append("\n");
        	}
        	
        }
        
        if(isPossible) {
        	System.out.println(sb);
        } else {
        	System.out.println(-1);
        }
	}

	public static long getFine(String value, String dateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    	
    	LocalDateTime startTime = LocalDateTime.parse(value, formatter);
        LocalDateTime endTime = LocalDateTime.parse(dateTime, formatter);
    	
        Duration duration = Duration.between(startTime, endTime);
        long diff = duration.toMinutes();
        
        return diff - L > 0 ? (diff - L) * F : 0;
	}

}
