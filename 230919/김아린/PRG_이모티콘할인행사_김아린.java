class Solution {
    static int[] rate = {10, 20, 30, 40}; //이모티콘 할인율
    static int[] result; //중복 순열 결과
    static int answer_plus;
    static int answer_price;
    
    public int[] solution(int[][] users, int[] emoticons) {
        result = new int[emoticons.length];
        answer_plus = 0;
        answer_price = 0;
        
        permutation(0, users, emoticons); //중복 순열
        
        int[] answer = {answer_plus, answer_price};
        return answer;
    }
    
    public void permutation(int depth, int[][] users, int[] emoticons) {
        if(depth == emoticons.length) {
            calculate(users, emoticons); //유저별 이모티콘 구매 비용 계산
            return;
        }
        
        for(int i = 0; i < rate.length; i++) {
            result[depth] = rate[i];
            permutation(depth + 1, users, emoticons);
        }
    }
    
    public void calculate(int[][] users, int[] emoticons) {
        int plus = 0; //플러스 가입자 수
        int price = 0; //판매액
        
        //유저별로 구매 비용 합 계산
        for(int i = 0; i < users.length; i++) {
            int standard_rate = users[i][0]; //기준 비율
            int standard_price = users[i][1]; //기준 비용
            
            int sum = 0; //구매 비용 합
            for(int j = 0; j < emoticons.length; j++) {
                if(result[j] >= standard_rate) { //기준보다 높으면 이모티콘 구매
                    sum += (emoticons[j] * (100 - result[j]) / 100);
                }
            }
            
            //플러스 가입여부 판단
            if(sum >= standard_price) {
                plus++;
            } else {
                price += sum;
            }
        }
        
        //최댓값 갱신하기
        if(plus == answer_plus) {
            if(price > answer_price) {
                answer_plus = plus;
                answer_price = price;
            }
        } else if(plus > answer_plus) {
            answer_plus = plus;
            answer_price = price;
        }
    }
}