import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Main {
    static class Tuple implements Comparable<Tuple>{
        int idx, color, size;
        public Tuple(int idx, int color, int size) {
            this.idx = idx;
            this.color = color;
            this.size = size;
        }

        public int compareTo(Tuple oth) {
            if (this.size < oth.size) {
                return -1;
            }else if (this.size == oth.size){
                return 0;
            }else{
                return 1;
            }
        }
    }
    static int acc, sameAcc;
    static HashMap<Integer, Integer> col2acc;
    static HashMap<Integer, Integer> sameSize;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Tuple> PQ = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            String[] inArr = br.readLine().split(" ");
            int color = Integer.parseInt(inArr[0]);
            int size = Integer.parseInt(inArr[1]);
            PQ.add(new Tuple(i, color, size));
        }
        int[] answer = new int[N];
        Tuple tp = PQ.poll();
        acc = 0;
        col2acc = new HashMap<>();      // key : color, value : color 별 누적값
        int prev = tp.size;
        init(tp);
        while (!PQ.isEmpty()) {
            tp = PQ.poll();
            if (prev == tp.size) {  // 이전 크기와 같은 경우
                sameAcc += tp.size; // sameAcc를 누적
                sameSize.put(tp.color, sameSize.getOrDefault(tp.color, 0) + tp.size); // sameSize에 누적
            }else{          // 이전 크기와 다른 경우
                update();   // sameSize에 있는 것을 col2acc로 옮긴다.
                init(tp);   // sameSize를 초기화. 이제 현재 tp가 기준이 되니까
                prev = tp.size;
            }
            // answer[tp.idx] 는 acc에서 현재 컬러의 누적값을 뺴는 것. 이건 col2acc에서 가져온다. sameSize에 있는 건 가져오면 안됨.
            answer[tp.idx] = acc - col2acc.getOrDefault(tp.color, 0);
        }
        pretty(answer);
    }
    static void init(Tuple tp){ // sameAcc, sameSize 초기화
        sameAcc = tp.size;
        // key : color, value : color 별 누적값
        sameSize = new HashMap<>();     // sameSize : 처음에 누적하는 곳, 크기가 같은 경우 여기에 계속 누적된다.
        sameSize.put(tp.color, tp.size);
    }
    static void update(){       // sameSize에 있는 값을 col2acc로 옮긴다. acc에도 sameAcc를 더해준다.
        acc += sameAcc;
        for (Integer key : sameSize.keySet()) {
            int same = sameSize.get(key);
            int col = col2acc.getOrDefault(key, 0);
            col2acc.put(key, same + col);
        }
    }

    static void pretty(int[] answer) {
        StringBuilder sb = new StringBuilder();
        for (Integer ans : answer) {
            sb.append(ans + "\n");
        }
        System.out.println(sb.toString());
    }
}