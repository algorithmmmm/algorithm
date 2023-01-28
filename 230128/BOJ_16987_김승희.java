import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ16987 {
    static int N,Answer;
    static int[] weight;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] durability = new int[N];
        weight = new int[N];
        for(int i = 0 ; i < N; i++){
            String[] inArr = br.readLine().split(" ");
            durability[i] = Integer.parseInt(inArr[0]);
            weight[i] = Integer.parseInt(inArr[1]);
        }
        Answer = 0;
        crash(0, durability);
        System.out.println(Answer);

    }
    static void crash(int now, int[] durability){
        // 가장 오른쪽에 있는 계란일 경우
        if(now == N){
            //현재 깨져있는 계란 수 구하기 
            int cnt = 0;
            for(int i = 0; i < N; i++){
                if(durability[i] <= 0){
                    cnt++;
                }
            }
            Answer = Math.max(cnt, Answer);
            return;
        }
        // 현재 들고 있는 계란이 깨져있는 경우 다음 계란 으로 넘어가기
        if(durability[now] <= 0) {
            crash(now + 1, durability);
            return;
        }
        // 현재 들고 있는 계란으로 다른 계란 치기
        for(int i = 0 ; i < N; i++){
            // 자기자신은 칠 수 없음
            if(now == i && i == N-1){
                crash(now + 1, durability);
            }
            if(now == i || durability[i] <= 0) continue;
            int[] cdurability = deepcopy(durability);
            cdurability[now] -= weight[i];
            cdurability[i] -= weight[now];
            crash(now+1, cdurability);
        }

    }
    static int[] deepcopy(int[] arr){
        int[] copy = new int[arr.length];
        for(int i = 0; i < arr.length; i++){
            copy[i] = arr[i];
        }
        return copy;
    }
}
