package day0527;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class PRG_42579_백자민 {
	class Solution {
	    public int[] solution(String[] genres, int[] plays) {
	    	
	    	//장르별로 총 재생수 저장 
	    	HashMap<String, Integer> map = new HashMap<>();
	        for(int i=0; i<genres.length; i++){
	            map.put(genres[i], map.getOrDefault(genres[i], 0)+plays[i]);
	        }
	        
	        //장르 뽑기
	        ArrayList<String> keyset = new ArrayList(map.keySet());
	        Collections.sort(keyset, (k1, k2) -> map.get(k2) - (map.get(k1)));
	        
	        //장르 안에서 노래 뽑기
	        ArrayList<Integer> songlist = new ArrayList<>();
	        for(String key: keyset) {
	        	
	        	//장르에 해당하는 노래 모두 뽑아서
	        	ArrayList<Song> tmplist = new ArrayList<>();
	        	for(int i=0; i<genres.length; i++) {
	        		if(genres[i].equals(key))
	        			tmplist.add(new Song(i, plays[i]));
	        	}
	        	
	        	Collections.sort(tmplist, (l1, l2) -> l2.cnt - l1.cnt);
	        	songlist.add(tmplist.get(0).idx); //장르별 곡은 무조건 하나 이상 존재 
	        	if(tmplist.size()>1)
	        		songlist.add(tmplist.get(1).idx);
	        }
	        
	        return songlist.stream().mapToInt(i -> i).toArray();
	    }
	    
	    static class Song {
	    	int idx;
	    	int cnt;
	    	
			public Song(int idx, int cnt) {
				this.idx = idx;
				this.cnt = cnt;
			}
	    	
	    }
	}
}
