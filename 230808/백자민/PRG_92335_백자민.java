package day0808;

public class PRG_92335_백자민 {
	class Solution {
	    
	    static boolean isPrime(long n) {
	        
	        if(n<2) return false;
	        
	        for(int i=2;i<=Math.sqrt(n);i++) {
	            if(n%i==0)
	                return false;
	        }
	        
	        return true;
	    }
	    
	    static String changeNum(int n, int k) {
	        String tmp = "";
	        while(n!=0) {
	            tmp = n%k+tmp;
	            n/=k;
	        }
	        return tmp;
	    }
	    
	    public int solution(int n, int k) {
	        int answer = 0;
	        String tmpStr = changeNum(n, k);
	        
	        String[] arr = tmpStr.split("0");
	        
	        for(String s: arr) {
	            if(s.equals("")) continue;
	            
	            long tmp = Long.parseLong(s);
	            if(isPrime(tmp))
	                answer++;
	        }
	        
	        return answer;
	    }
	}
}
