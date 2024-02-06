import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if(N == 1){
            System.out.println(1);
            System.exit(0);
        }

        long start = 1;
        long end = 10000000000L;
        long answer = 0;
        while(start <= end){
            long mid = (start + end) / 2;
            // num : 3, 5의 배수 빼고 숫자의 개수
            long num = mid - ( mid / 3 + mid / 5 - mid / 15);
            if(num == N){
                answer = mid;
                while (answer % 3 == 0 || answer % 5 == 0) {
                    answer--;
                }
                break;
            }else if(num > N){
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }
        System.out.println(answer);
    }
}