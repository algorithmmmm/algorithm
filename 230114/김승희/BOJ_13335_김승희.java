import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
/*
* 트럭의 순서를 바꿀 수 없기 때문에 다리 위에 최대한 올릴 수 있는 만큼 올려야 한다.
* 큐를 이용해 트럭이 다리를 지나가는 것을 형상화?하였고
* 최대하중을 넘어가 트럭을 올릴 수 없는 경우에는 0을 넣어주었다.
* 트럭을 모두 올린 후에는 마지막 트럭이 다리를 모두 건너가는 시간까지 구해야 하기 때문에 W를 더해주었다.
* */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        int N = Integer.parseInt(inArr[0]);
        int W = Integer.parseInt(inArr[1]);
        int L = Integer.parseInt(inArr[2]);

        inArr = br.readLine().split(" ");
        int[] trucks = new int[N];
        for(int i = 0;  i < N; i++){
            trucks[i] = Integer.parseInt(inArr[i]);
        }

        int time = 1;
        int idx = 1;
        Queue<Integer> Q = new ArrayDeque<>();
        Q.add(trucks[0]);
        int sum = trucks[0];
        while(!Q.isEmpty()){
            if(idx == N){
                break;
            }
            if(Q.size() == W){
                sum -= Q.poll();
            }

            int temp = sum + trucks[idx];
            if(temp > L){
                Q.add(0);
            }else{
                Q.add(trucks[idx]);
                sum = temp;
                idx++;
            }
            time++;

        }
        time += W;
        System.out.println(time);
    }
}

/*
5 3 10
3 2 6 4 3
10
----------
4 2 10
7 5 4 6
7

*/