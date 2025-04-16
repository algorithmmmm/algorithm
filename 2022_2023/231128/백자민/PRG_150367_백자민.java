package day1128;

public class PRG_150367_백자민 {
	class Solution {
	    
	    static boolean isPossible(String str) {
	        if(str.length()==1)
	            return true;
	        
	        String leftT = str.substring(0, str.length()/2);
	        String rightT = str.substring(str.length()/2+1);
	        
	        char root = str.charAt(str.length()/2);
	        char left = leftT.charAt(leftT.length()/2);
	        char right = rightT.charAt(rightT.length()/2);
	        
	        if(root=='0' && (left=='1' || right=='1'))
	            return false;
	        else
	            return isPossible(leftT)&&isPossible(rightT);
	    }
	    
	    static String getBinary(long n) {
	        String tmpBinary = Long.toBinaryString(n);
	        
	        int len = tmpBinary.length();
	        int nodeCount = 1;
	        int level = 1;
	        while (len > nodeCount) {
	            level *= 2;
	            nodeCount += level;
	        }

	        StringBuilder sb = new StringBuilder();
	        for(int i=0,size=nodeCount-len;i<size;i++)
	            sb.append("0");
	        sb.append(tmpBinary);
	        
	        return sb.toString();
	    }
	    
	    public int[] solution(long[] numbers) {

	        int n = numbers.length;
	        int[] answer = new int[n];
	        
	        for(int i=0;i<n;i++) {
	            String tmp = getBinary(numbers[i]);
	            if(isPossible(tmp))
	                answer[i] = 1;
	            else
	                answer[i] = 0;
	        }
	        
	        
	        
	        return answer;
	    }
	}
}
