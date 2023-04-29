import java.util.*;
class Solution {
    static class Bundle implements Comparable<Bundle>{
        int dia, iron, stone;
        public Bundle(int dia, int iron, int stone){
            this.dia = dia;
            this.iron =iron;
            this.stone = stone;
        }
        public int compareTo(Bundle oth){
            if(this.dia > oth.dia){
                return 1;
            }else if(this.dia == oth.dia){
                if(this.iron > oth.iron){
                    return 1;
                }else if(this.iron == oth.iron){
                    if(this.stone > oth.stone){
                        return 1;
                    }else if(this.stone == oth.stone){
                        return 0;
                    }else{
                        return -1;
                    }
                }else{
                    return -1;
                }
            }else{
                return -1;
            }
        }
    }
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        int totalCnt = picks[0] + picks[1] + picks[2];
        int[][] stat = {
            {1, 1, 1},
            {5, 1, 1},
            {25, 5, 1}
        };
        PriorityQueue<Bundle> pq = new PriorityQueue<>();
        int[] cnt = new int[3];
        cnt[str2int(minerals[0])]++;
        for(int i = 1; i < minerals.length; i++){
            if(i%5 == 0){
                pq.add(new Bundle(-cnt[0], -cnt[1], -cnt[2]));
                if(pq.size() == totalCnt){
                    break;
                }
                cnt = new int[3];
            }
            cnt[str2int(minerals[i])]++;
        }
        if(pq.size() < totalCnt){
            pq.add(new Bundle(-cnt[0], -cnt[1], -cnt[2]));
        }
        int N = Math.min(totalCnt, pq.size());
        int idx = 0;
        for(int i = 0; i < N; i++){
            while(picks[idx] == 0){
                idx++;
            }
            Bundle bundle = pq.poll();
            answer += stat[idx][0] * bundle.dia * (-1);
            answer += stat[idx][1] * bundle.iron * (-1);
            answer += stat[idx][2] * bundle.stone * (-1);
            picks[idx]--;
        }
        return answer;
    }
    static int str2int(String str){
        if(str.equals("diamond"))return 0;
        if(str.equals("iron"))return 1;
        if(str.equals("stone"))return 2;
        return -1;
    }
}