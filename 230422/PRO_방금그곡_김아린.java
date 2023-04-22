class Solution {
    public String solution(String m, String[] musicinfos) {
    	String answer = "(None)"; //정답 : 멜로디의 제목
    	int maxLength = Integer.MIN_VALUE; //음악 재생 시간 최대 길이
    	
    	for(int i = 0; i < musicinfos.length; i++) {
            //문자 파싱
    		String[] musicinfo = musicinfos[i].split(",");
    		
    		String start = musicinfo[0]; //시작 시각
    		String end = musicinfo[1]; //끝난 시각
    		String title = musicinfo[2];//음악 제목
    		String melody = musicinfo[3]; //악보 정보
    		
    		int length = getLength(start, end); //곡의 재생시간
    		
            //#붙은 음 문자 하나로 변환하기
    		m = changeMelody(m);
    		melody = changeMelody(melody);
    		
    		String play = ""; //재생될 음악
    		if(length <= melody.length()) { //재생 시간이 멜로디보다 작으면 -> 음악은 재생 시간만큼
    			play = melody.substring(0, length);
    		} else { //재생 시간이 멜로디보다 크면 -> 반복해서 재생
    			int q = length / melody.length();
    			int r = length % melody.length();
    			
    			for(int j = 0; j < q; j++) {
    				play += melody;
    			}
    			play += melody.substring(0, r);
    		}
    		
    		if(play.contains(m) && length > maxLength) { //조건에 일치한 음악이고 재생된 시간이 제일 긴 음악이면 갱신
    			answer = title;
    			maxLength = length;
    		}
    		
    	}
        
        return answer;
    }

	public String changeMelody(String melody) { //멜로디 한 글자로 변환하기
		melody = melody.replaceAll("C#", "H");
		melody = melody.replaceAll("D#", "I");
		melody = melody.replaceAll("F#", "J");
		melody = melody.replaceAll("G#", "K");
        melody = melody.replaceAll("A#", "L");
		
		return melody;
	}

	public int getLength(String start, String end) { //재생시간 구하기
		String[] strStart = start.split(":"); //시간, 분으로 나누기
		String[] strEnd = end.split(":");
		
        //분단위로 계산
		int intStart = Integer.parseInt(strStart[0]) * 60 + Integer.parseInt(strStart[1]);
		int intEnd = Integer.parseInt(strEnd[0]) * 60 + Integer.parseInt(strEnd[1]);
		
		return intEnd - intStart;
	}

}