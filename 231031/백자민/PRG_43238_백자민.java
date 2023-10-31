package day1031;
import java.util.*;

public class PRG_43238_백자민 {

	class Solution {
	    public long solution(int n, int[] times) {
	        
	        Arrays.sort(times);
	        
	        long l=0,r=(long)times[times.length-1]*n;
	        
	        while(l<=r) {
	            long mid = (l+r)/2;
	            long sum = 0;
	            
	            for(int t: times) {
	                sum += mid/t;
	            }
	            
	            if(sum>=n) {
	                r = mid-1;
	            } else {
	                l = mid+1;
	            }
	        }
	        
	        return l;
	    }
	}
}
