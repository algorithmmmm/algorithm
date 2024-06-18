package day240618;

import java.util.*;
import java.io.*;

public class CDT_겹치는원소가적은부분수열_백자민 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        } //end input

        HashMap<Integer, Integer> map = new HashMap<>(); //현재 수열에 있는 원소와 개수를 저장할 해시맵
        int l=0,r=1;
        map.put(arr[l],1);

        int max=1;
        while(l<=r) {
            if(r==N) {
                break;
            }

            while(map.getOrDefault(arr[r],K-1)>=K) { //현재 r포인터가 가리키는 원소가 이미 부분수열에 K개 존재하는 경우(해당 원소를 제거해야 함)
                max = Math.max(max, r-l); //부분 수열 길이를 줄이기 전 최댓값 갱신

                map.put(arr[l], map.get(arr[l])-1); //l포인터 위치 원소 개수 차감
                l++; //l포인터 이동(부분 수열 길이 감소)
            }

            map.put(arr[r], map.getOrDefault(arr[r],0)+1);
            r++;
        }
        max = Math.max(max, r-l); //종료 후 최댓값 갱신(엣지케이스1: 전체 수열이 조건을 만족하는 경우)

        System.out.println(max);
    }
}