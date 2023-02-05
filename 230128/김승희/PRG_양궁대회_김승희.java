class Solution {
    static boolean[] lionWin;
    static int sub;
    static int[] Answer;
    public int[] solution(int n, int[] info) {
        sub = -1;
        lionWin = new boolean[11];
        subset(0, n, info);
        int[] basic = {-1};
        if(sub == -1){
            return basic;
        }
        return Answer;
    }
    static void subset(int cnt, int n, int[] info){
        if(cnt == 11){
            check(n, info);
            return;
        }
        lionWin[cnt] = true;
        subset(cnt+1, n, info);
        lionWin[cnt] = false;
        subset(cnt+1, n, info);
    }
    
    static void check(int n, int[] info){
        int cnt = n;
        int[] score = new int[11];
        for(int i = 0; i < 11; i++){
            if(lionWin[i]){
                if(cnt <= info[i]){
                    return;
                }
                score[i] =info[i]+1;
                cnt -= info[i]+1;
            }
        }
        if(cnt < 0) return;
        for(int i = 10; i >= 0; i--){
            
            if(!lionWin[i]){
                if(info[i] == 0) continue;
                if(cnt <= info[i]){
                    score[i] = cnt;
                    break;
                }
                score[i] = info[i];
                cnt -= info[i];
            }else{
                score[i] += cnt;
                break;
            }
            if(cnt > 0) return;
        }
        int lion = 0;
        int apeach = 0;
        for(int i = 0; i < 11; i++){
            if(info[i] ==score[i] && score[i] == 0){
                continue;
            }
            if(lionWin[i]){
                lion += 10-i;
            }else{
                apeach += 10-i;
            }
        }
        if(lion > apeach){
            if(sub < lion -apeach){
                sub = lion - apeach;
                Answer = deepcopy(score);
            }else if(sub == lion - apeach){
                for(int i = 0 ; i < 11; i++){
                    if(Answer[i] < score[i]){
                        Answer = deepcopy(score);
                        break;
                    }
                }
            }
        }
    }
    static int[] deepcopy(int[] arr){
        int[] copy = new int[11];
        for(int i=0; i  < 11; i++){
            copy[i] = arr[i];
        }
        return copy;
    }
    
}