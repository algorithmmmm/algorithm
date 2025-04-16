import java.util.*;
import java.io.*;
/*
* 2차평면 위 N개의 점
* 원점에서 시작해 모든 점을 겹치지 않게 단 한 번씩만 방문하고 다시 원점으로 돌아오기
* 주어진 점에서 방향을 꼭 전환해야 한다.
* x축 혹은 y축에 평행하게 이동해야 한다.
* 특정 점을 만나게 되더라도 방문하지 않고 스쳐지나가는 것도 가능하다.
*
* N개의 점을 순열을 구한다.
* check(start, end)
* : start에서 end로 갈 수 없으면 -1, 갈 수 있으면 방향에 해당하는 숫자를 반환한다.
* run()
* : 원점에서 number[0]으로
* number[0]에서 number[1]로
* ...
* number[N-1]에서 원점으로
* 갈 수 있는지 판단한다.
* */

public class CT_원점으로돌아오기 {
    static int N, total;
    static int[] number;
    static boolean[] isSelect;
    static int[][] points;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        points = new int[N][2];

        for(int i = 0; i < N; i++){
            String[] inArr = br.readLine().split(" ");
            points[i][0] = Integer.parseInt(inArr[0]);
            points[i][1] = Integer.parseInt(inArr[1]);

        }

        total = 0;
        number = new int[N];
        isSelect = new boolean[N];
        permutation(0);
        System.out.println(total);
    }

    static void permutation(int idx){
        if(idx == N){
            if(run()){
                total++;
            }
            return;
        }
        for(int i = 0; i < N; i++){
            if(isSelect[i]) continue;
            isSelect[i] = true;
            number[idx] = i;
            permutation(idx+1);
            isSelect[i] = false;
        }
    }
    static boolean run(){
        int[] start = {0, 0};
        int[] end;
        int prev = -1;
        for(int i = 0; i < N; i++){
            end = points[number[i]];
            int direct = check(start, end);
            if(direct == -1) return false;
            if(prev == direct) return false;
            prev = direct;
            start = end;
        }
        end = new int[]{0, 0};
        int direct = check(start, end);
        if(direct == -1 || prev == direct) return false;
        return true;
    }

    static int check(int[] start, int[] end){
        if(start[0] != end[0] && start[1] != end[1]){
            return -1;
        }
        if(start[0] == end[0]){
            if(start[1] < end[1]){
                return 0;
            }else{
                return 1;
            }
        }else{
            if(start[0] < end[0]){
                return 2;
            }else{
                return 3;
            }
        }
    }
}