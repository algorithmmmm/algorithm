import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_중위순회 {
    static final int T = 10;

    static class Node{
        char value;
        Integer left, right;
        public Node(char value, Integer left, Integer right){
            this.value = value;
            this.left = left;
            this.right = right;
        }
        public Node(char value, Integer left){
            this.value = value;
            this.left = left;
        }
        public Node(char value){
            this.value = value;
        }
    }
    static Node[] Tree;
    static int N;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        for(int t = 1; t <= T; t++){
            N = Integer.parseInt(br.readLine());
            Tree = new Node[N + 1];
            for(int i = 0; i < N; i++){
                String[] inArr = br.readLine().split(" ");
                int idx = Integer.parseInt(inArr[0]);
                if(inArr.length == 4){  // 자식이 둘 다 있는 경우
                    Tree[idx] = new Node(inArr[1].charAt(0), Integer.parseInt(inArr[2]), Integer.parseInt(inArr[3]));
                }else if(inArr.length == 3){    // 왼쪽 자식만 있는 경우
                    Tree[idx] = new Node(inArr[1].charAt(0), Integer.parseInt(inArr[2]));
                }else if(inArr.length == 2){    // 자식이 없는 경우
                    Tree[idx] = new Node(inArr[1].charAt(0));
                }
            } // end input
            sb = new StringBuilder();
            inOrder(1);
            answer.append("#").append(t).append(" ").append(sb.toString()).append("\n");
        }
        System.out.println(answer.toString());
    }
    
    static void inOrder(int idx){
        // 왼쪽
        if(Tree[idx].left != null){
            inOrder(Tree[idx].left);
        }
        // 자기 자신
        sb.append(Tree[idx].value);
        // 오른쪽
        if(Tree[idx].right != null){
            inOrder(Tree[idx].right);
        }
    }
}
