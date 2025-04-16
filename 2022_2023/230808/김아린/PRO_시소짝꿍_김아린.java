class Solution {
    public long solution(int[] weights) {
        long[] counts = new long[2001]; //몸무게 빈도 카운트
        
        for(int weight : weights) {
            counts[weight]++;
        } 
        
        long answer = 0; //시소 짝꿍 쌍 개수
        
        for(int i = 100; i <= 1000; i++) { //몸무게 범위 탐색
            if(counts[i] > 0) {
                answer += counts[i] * (counts[i] - 1) / 2; // 1 : 1 (nC2)
                
                if((i * 3) % 2 == 0) {
                    answer += counts[i] * counts[i * 3 / 2]; // 2: 3
                }
                
                answer += counts[i] * counts[i * 2]; // 2 : 4
                
                if((i * 4) % 3 == 0) {
                    answer += counts[i] * counts[i * 4 / 3]; // 3 : 4
                }
            }
        }
        
        return answer;
    }
}