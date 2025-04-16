import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        //정렬
        Arrays.sort(data, (o1, o2) -> o1[col - 1] == o2[col - 1] ? o2[0] - o1[0] : o1[col - 1] - o2[col - 1]);
        
        int answer = 0; //누적하여 bitwise XOR 한 값
        
        for(int i = row_begin; i <= row_end; i++) {
            int S = 0; //나머지들의 합
            
            for(int j = 0; j < data[0].length; j++) {
                S += data[i - 1][j] % i;
            }
            
            answer ^= S; //XOR
        }
        
        return answer;
    }
}