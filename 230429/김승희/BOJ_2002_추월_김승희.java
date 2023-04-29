import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ_2002_추월 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Queue<String> start = new ArrayDeque<>();
        Queue<String> end = new ArrayDeque<>();

        for(int i = 0; i < N; i++){
            start.add(br.readLine());
        }
        for(int i = 0; i < N; i++){
            end.add(br.readLine());
        }
    }
}
