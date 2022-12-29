import java.util.*;
/*
 * 부분집합으로 던전을 선택하는 모든 경우의 수를 구한다. -> subset
 * 구해진 경우의 수에서, 던전을 방문하는 순서를 구한다. -> permutation
 * 구해진 방문하는 순서대로 던전을 방문한다. -> process
 * 만약 방문할 수 없다면(=현재 피로도가 최소 필요 피로도보다 작다면) 더 이상 볼 필요 없다.
 * 정해진 순서대로 모두 방문이 가능한 경우에서 방문한 던전의 개수가 가장 많은 경우를 구한다.
 * */
class Solution {
    static int result;
    static int[] order;
    static boolean[] selectS, selectP;
    static ArrayList<Integer> number;
    
    public int solution(int k, int[][] dungeons) {
        result = Integer.MIN_VALUE;
        selectS = new boolean[dungeons.length];
        subset(0, dungeons.length, k, dungeons);
        return result;
    }
    // 부분집합 구하기. 던전을 선택하는 모든 경우의 수
    static void subset(int cnt, int LEN, int k, int[][] dungeons){
        if(cnt == LEN){
            number = new ArrayList<>();
            for(int i = 0; i < LEN; i++){
                if(selectS[i]){
                    number.add(i);
                }
            }
            // 공집합인 경우 빼기
            if(number.size() == 0){
                return;
            }
            selectP = new boolean[number.size()];
            order = new int[number.size()];
            permutation(0, number.size(), k, dungeons);
            return;
        }
        selectS[cnt] = true;
        subset(cnt+1, LEN, k, dungeons);
        selectS[cnt] = false;
        subset(cnt+1, LEN, k, dungeons);
    }
    
    // 선택된 던전을 방문할 순서 정하기
    static void permutation(int cnt, int LEN, int k, int[][] dungeons){
        if(cnt == LEN){
            int now = k;
            for(int i = 0; i < number.size(); i++){
                // 최소 필요 피로도만큼 피로도가 존재하지 않기 때문에 방문 불가. 
                // 더 이상 볼 필요 없다.
                if(now < dungeons[order[i]][0]){
                    return;
                }
                now -= dungeons[order[i]][1];
            }
            result = Math.max(result, number.size());
            return;
        }
        for(int i = 0; i < number.size(); i++){
            if(selectP[i]){
                continue;
            }
            order[cnt] = number.get(i);
            selectP[i] = true;
            permutation(cnt+1, LEN, k, dungeons);
            selectP[i] = false;
        }
    }
}
/*
90
[[80, 20], [30,10],[40,10],[50,40]]
4
-------------------------------------
40
[[40, 10], [30,20],[20,20]]
2
-------------------------------------
40
[[40, 30], [30,30],[20,20]]
1
-------------------------------------
90
[[80, 20], [30,10],[40,10],[70,30]]
4
-------------------------------------
*/