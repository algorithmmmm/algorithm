import java.util.*;
import java.io.*;
public class CT_Carry피하기 {
    static int N;
    static String[] number;        // nubmer[i] : i번째 숫자
    static boolean[] select;     // select[i] : false면 i번째 숫자를 선택했다는 의미
    static int MAX;
    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 초기화
        number = new String[N];
        select = new boolean[N];
        MAX = 0;
        for(int i = 0; i < N; i++){
            number[i] = br.readLine();
        }
        subset(0);
        System.out.println(MAX);

    }
    public static void subset(int idx){
        if(idx == N){
            check();
            return;
        }
        select[idx] = false;
        subset(idx+1);
        select[idx] = true;
        subset(idx+1);

    }

    public static void check(){
        ArrayList<String> list = selectNumber();
        if(list.isEmpty()) return;
        int num = Integer.parseInt(list.get(0));
        String a = list.get(0);
        for(int i = 1; i < list.size(); i++){
            String b = list.get(i);
            if(a.length() > b.length()){
                b = a;
                a = list.get(i);
            }
            if(!isCarry(a, b)){
                return;
            }
            int temp = Integer.parseInt(a) + Integer.parseInt(b);
            a = String.valueOf(temp);
        }
        MAX = Math.max(MAX, list.size());
    }

    public static ArrayList<String> selectNumber(){
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < N; i++){
            if(!select[i]){
                list.add(number[i]);
            }
        }
        return list;
    }

    // 항상 a의 길이가 더 짧음
    // a와 b를 더했을 때, Carry가 발생하면 true
    public static boolean isCarry(String a, String b){
        for(int i = 0; i < a.length(); i++){
            int A = a.charAt(a.length()-1-i) - '0';
            int B = b.charAt(b.length()-1-i) - '0';
            if(A + B >= 10){
                return false;
            }
        }
        return true;
    }
}