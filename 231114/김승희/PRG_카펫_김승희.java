class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = {};
        int a = 2;
        int b = (brown - 2 * a + 4) / 2;
            
        for(int i = a; i <= b; i++){
            int m = i;
            int n = (brown - 2 * m + 4) / 2;
            
            if(n < m) break;
            if(n * m == brown + yellow){
                answer = new int[]{n, m};
            }
        }
        return answer;
    }
}