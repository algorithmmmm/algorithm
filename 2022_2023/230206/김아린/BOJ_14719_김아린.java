import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14719_김아린 {
    /*
        1. 가장 큰 지점 구하기(idx)
        2. 그 지점을 기준으로 고이는 빗물량 구하기 : 0~idx 빗물량 + W~idx 빗물량
        3. 빗물량 구하기 : 1) 시작 시점부터 idx까지 가장 큰 높이 갱신하기
                         2) 가장 큰 높이보다 작으면, (가장 큰 높이 - 현재 높이) 만큼 더해주기
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int H = Integer.parseInt(st.nextToken()); //세로 길이
        int W = Integer.parseInt(st.nextToken()); //가로 길이

        int[] arr = new int[W]; //각 높이 저장하는 배열

        st = new StringTokenizer(br.readLine(), " ");

        int highest = 0; //가장 큰 지점
        int idx = 0; //가장 큰 지점의 인덱스
        for(int i = 0; i < W; i++) {
            arr[i] = Integer.parseInt(st.nextToken());

            if(highest < arr[i]) { //가장 큰 지점과 인덱스 갱신
                highest = arr[i];
                idx = i;
            }
        }

//        System.out.println("highest: " + highest);
//        System.out.println("idx: " + idx);

        //0~idx 빗물량 구하기
        int volume1 = 0;
        highest = arr[0]; //초기 가장 큰 높이 값 설정
        for(int i = 1; i < idx; i++) {
            if(arr[i] <= highest) { //가장 큰 높이보다 작으면
                volume1 += (highest - arr[i]); //빗물양 더하기
//                System.out.println(i + ": " + (highest - arr[i]));
            } else { //크면
                highest = arr[i]; //가장 큰 높이 값 갱신
            }
        }
        
        //W~idx 빗물량 구하기
        int volume2 = 0;
        highest = arr[W - 1]; //초기 가장 큰 높이 값 설정
        for(int i = W - 1; i > idx; i--) {
            if(arr[i] <= highest) { //가장 큰 높이보다 작으면
                volume2 += (highest - arr[i]); //빗물양 더하기
            } else { //크면
                highest = arr[i]; //가장 큰 높이 값 갱신
//                System.out.println(i + ": " + (highest - arr[i]));
            }
        }

        int answer = volume1 + volume2; //빗물의 총량

        System.out.println(answer);
    }
}
