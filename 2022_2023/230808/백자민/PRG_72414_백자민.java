package day0808;
import java.util.*;

public class PRG_72414_백자민 {

	class Solution {
	    
	    static String makeString(int time) {
	        String h = (time/3600)<10?"0"+(time/3600):(time/3600)+"";
	        time = time%3600;
	        String m = (time/60)<10?"0"+(time/60):(time/60)+"";
	        time = time%60;
	        String s = time<10?"0"+time:time+"";
	        return h+":"+m+":"+s;
	    }
	    
	    static int convertTime(String time) { //시간 포맷을 초 단위로 바꿔주기
	        StringTokenizer st = new StringTokenizer(time, ":");
	        int h = Integer.parseInt(st.nextToken());
	        int m = Integer.parseInt(st.nextToken());
	        int s = Integer.parseInt(st.nextToken());
	        return h*60*60 + m*60 + s;
	    }
	    
	    public String solution(String play_time, String adv_time, String[] logs) {
	        
	        int advT = convertTime(adv_time);
	        int playT = convertTime(play_time); 
	        
	        int[] user = new int[playT+1];
	        
	        for(String log: logs) {
	            StringTokenizer st = new StringTokenizer(log, "-");
	            int start = convertTime(st.nextToken());
	            int end = convertTime(st.nextToken());
	            
	            for(int i=start;i<end;i++) {
	                user[i]++;
	            }
	        }
	        
	        
	        long max=0;
	        
	        for(int i=0;i<advT;i++) {
	            max += user[i];
	        } 
	        
	        int idx = 0;
	        long tmp = max;
	        for(int i=advT;i<playT;i++) {
	            tmp += user[i]-user[i-advT];
	            if(tmp>max) {
	                max = tmp;
	                idx = i-advT+1;
	            }
	        }

	        return makeString(idx);
	    }
	}
}
