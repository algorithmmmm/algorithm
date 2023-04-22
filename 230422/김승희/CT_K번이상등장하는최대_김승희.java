
import java.util.*;
import java.io.*;

public class CT_K번이상등장하는최대 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        int N = Integer.parseInt(inArr[0]);
        int K = Integer.parseInt(inArr[1]);

        inArr = br.readLine().split(" ");

        int maxNum = -1;
        HashMap<Integer, Integer> hash = new HashMap<>();
        for(int i = 0; i < N; i++){
            int number = Integer.parseInt(inArr[i]);
            int temp = hash.getOrDefault(number, 0);
            hash.put(number, temp+1);
            if(temp+1 >= K){
                if(maxNum <= number){
                    maxNum = number;
                }
            }
        }


        System.out.println(maxNum);

    }
}