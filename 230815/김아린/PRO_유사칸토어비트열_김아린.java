class Solution {
    public int solution(int n, long l, long r) {
        int answer = 0;
        
        for(long i = l; i <= r; i++) {
            if(isOne(i)) {
                // System.out.println(i);
                answer++;
            }
        }
        
        return answer;
    }
    
    public boolean isOne(long i) {
        if(i % 5 == 3) {
            return false;
        }
        
        if(i <= 5) {
            return true;
        }
        
        long next = (long)Math.ceil(i / (double)5);
        
        return isOne(next);
    }
}