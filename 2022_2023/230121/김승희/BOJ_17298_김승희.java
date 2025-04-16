import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] inArr = br.readLine().split(" ");
        int[] num = new int[n];
        for(int i = 0; i < n; i++){
            num[i] = Integer.parseInt(inArr[i]);
        }

        int[] answer = new int[n];
        Stack<Integer> stack = new Stack<>();
        stack.add(num[n-1]);
        answer[n-1] = -1;

        int idx = n-2;
        while(idx >= 0){
            if(stack.isEmpty()){
                answer[idx] = -1;
                stack.add(num[idx]);
                idx--;
            }else if(stack.peek() > num[idx]){
                answer[idx] = stack.peek();
                stack.add(num[idx]);
                idx--;
            }else{
                stack.pop();
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < n; i++){
            sb.append(answer[i] + " ");
        }
        System.out.println(sb.toString());
    }
}