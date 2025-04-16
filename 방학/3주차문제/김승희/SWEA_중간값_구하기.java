import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class SWEA_중간값_구하기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static PriorityQueue<Integer> Small, Large;
    static int N, first;
    static int[][] data;

    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int t = 1; t < T + 1; t++){
            input();
            Long answer = getMidNum();
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }
        System.out.println(sb.toString());
    }

    static void input() throws IOException {
        String[] inArr = br.readLine().split(" ");
        N = Integer.parseInt(inArr[0]);
        first = Integer.parseInt(inArr[1]);
        data = new int[N][2];
        for(int i = 0; i < N; i++){
            inArr = br.readLine().split(" ");
            data[i][0] = Integer.parseInt(inArr[0]);
            data[i][1] = Integer.parseInt(inArr[1]);
        }
    }

    static Long getMidNum() {
        Small = new PriorityQueue<>();  // 중간값보다 작은 값이 들어가 있음. poll하면 큰 값이 나온다.
        Large = new PriorityQueue<>();  // 중간값과 같거나 큰 값이 들어가 있음. poll하면 작은 값이 나온다.
        Long sum = 0L;
        // first를 Large에 먼저 넣기
        Large.add(first);
        int mid = Large.peek();
        for(int i = 0; i < N; i++){
            for(int j = 0 ; j < 2; j++){
                if (data[i][j] < mid) {
                    Small.add(-data[i][j]);
                }else{
                    Large.add(data[i][j]);
                }
            }
            balancing();
            mid = Large.peek();

            sum = (sum + mid) % 20171109;
        }
        return sum;
    }

    // Small과 Large의 비율 맞춰주기
    // 항상 Large가 Small 보다 1 커야 한다.
    // 데이터를 2개씩 넣기 때문에 Small이 1 크거나, Large가 3크거나 Large가 1 크거나 3가지 경우밖에 나오지 않는다.
    static void balancing(){
        if (Large.size() - Small.size() > 1) {
            Small.add(-Large.poll());
        } else if (Large.size() - Small.size() == 1) {
            return;
        }else{
            Large.add(-Small.poll());
        }

    }
}
/*
2
3 5
1 3
2 6
8 9
4 5
1 3
4 5
5 5
5 8

#1 11
#2 17
 */