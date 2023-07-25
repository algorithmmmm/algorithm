import java.util.*;
class Solution {
    static PriorityQueue<Integer> before, after;
    static Stack<Integer> delete;
    static int curr;
    static StringBuilder sb;
    static boolean[] answer;
    
    public String solution(int n, int k, String[] cmd) {
        curr = k;   // curr : 현재 행
        init(n, k);
        // end init
        for(String str : cmd){
            String[] arr = str.split(" ");
            if(arr.length == 1){
                if(arr[0].equals("C")){
                    C();
                }else if(arr[0].equals("Z")){
                    Z();
                }
            }else{
                int x = Integer.parseInt(arr[1]);
                if(arr[0].equals("U")){
                    U(x);
                }else if(arr[0].equals("D")){
                    D(x);
                }
            }
        }
        System.out.printf("%d %d", before.size(), after.size());
        // end cmd
        
        answer = new boolean[n];    // answer[i] : i번째 행이 살아있는지 여부
        check(before);
        answer[curr] = true;    
        check(after);
        // answer값이 true면 살아있으니까 O
        sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            sb.append(answer[i] ? "O" : "X");
        }
        return sb.toString();
    }
    // PQ에 있으면 answer값을 true로
    static void check(PriorityQueue<Integer> PQ){
        while(!PQ.isEmpty()){
            answer[Math.abs(PQ.poll())] = true;
        }
    }
    // before, after, delete 초기화
    static void init(int n, int k){
        before = new PriorityQueue<>(); // before : curr 앞에 행   max
        after = new PriorityQueue<>();  // after : curr 뒤에 행    min
        delete = new Stack<>();     // delete : 삭제된 행을 모아둘 스택
        for(int i = 0; i < k; i++){
            before.add(-i);
        }
        for(int i = k+1; i < n; i++){
            after.add(i);
        }
    }
    static void U(int x){
        // 현재 행(curr)을 after에 넣는다.
        after.add(curr);
        // x-1 만큼 before에서 꺼내서 after에 넣는다.
        for(int i = 0; i < x-1; i++){
            after.add(-before.poll());
        }
        // before에서 꺼낸 값을 curr로 만든다.
        curr = -before.poll();
    }
    static void D(int x){
        // 현재 행(curr)을 before에 넣는다.
        before.add(-curr);
        // x-1 만큼 after에서 꺼내서 before에 넣는다.
        for(int i = 0; i < x-1; i++){
            before.add(-after.poll());
        }
        // after에서 꺼낸 값을 curr로 만든다.
        curr = after.poll();
    }
    static void C(){
        // 현재 행을 delete에 넣는다.
        delete.add(curr);
        if(after.isEmpty()){    // after가 비어있다면
            curr = -before.poll();  // before에서 가장 큰 값을 curr로 만든다.
        }else{  // after가 비어있지 않다면
            curr = after.poll();    // after에서 가장 작은 값을 curr로 만든다.
        }
    }
    static void Z(){
        // delete의 top을 꺼낸다.
        int temp = delete.pop();
        if(temp < curr){    // top이 curr보다 작다면 before에 넣는다.
            before.add(-temp);
        }else{              // top이 curr보다 크다면 after에 넣는다.
            after.add(temp);
        }
    }
}