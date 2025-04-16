class Solution {
    public int[] solution(int[] sequence, int k) {
        int start = 0, end = 0;
        int sum = sequence[0];
        int ans_start = 0, ans_end = 1000001;
        
        while(end < sequence.length) {
            if(sum >= k){
                if(sum == k && end - start < ans_end - ans_start) {
                    ans_start = start;
                    ans_end = end;
                }
                
                // System.out.println(start + " " + end + " " + sum +" 크다");
                start++;
                sum -= sequence[start - 1];
            } else if (sum < k) {
                // System.out.println(start + " " + end + " " + sum +" 작다");
                end++;
                if(end == sequence.length) break;
                sum += sequence[end];
            }
        }
        
        return new int[] {ans_start, ans_end};
    }
}