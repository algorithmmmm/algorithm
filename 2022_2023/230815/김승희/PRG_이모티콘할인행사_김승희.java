import java.util.*;
class Solution {
    static int N, M;
    static int[] Answer;
    static int[] Emoticons;
    static int[][] Users;
    static int[] select;
    
    public int[] solution(int[][] users, int[] emoticons) {
        Emoticons = emoticons;
        Users = users;
        N = users.length;
        M = emoticons.length;
        Answer = new int[]{0, 0};
        select = new int[M];
        permutation(0);
        return Answer;
    }
    static int[] calc(){
        int[] price = discount();
        
        int cnt = 0;
        int profit = 0;
        for(int i = 0; i < N; i++){
            int acc = 0;
            for(int j = 0; j < M; j++){
                if(Users[i][0] <= select[j]){
                    acc += price[j];
                    if(acc >= Users[i][1]){
                        break;
                    }
                }
            }
            if(acc >= Users[i][1]){
                cnt++;
            }else{
                profit += acc;
            }
        }
        return new int[]{cnt, profit};
    }
    static int[] discount(){
        int[] price = new int[M];
        for(int i = 0; i < M; i++){
            double a = (double) select[i] / 100.0;
            a = Emoticons[i] * a;
            price[i] = Emoticons[i] - (int) a;
        }
        return price;
    }
    static void permutation(int cnt){
        if(cnt == M){
            int[] temp = calc();
            if(Answer[0] < temp[0]){
                Answer = temp;
            }else if(Answer[0] == temp[0]){
                Answer[1] = Math.max(Answer[1], temp[1]);
            }
            return;
        }
        for(int i = 10; i < 50; i+=10){
            select[cnt] = i;
            permutation(cnt+1);
        }
    }
}