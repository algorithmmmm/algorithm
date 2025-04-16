package day240429;

import java.util.*;

public class CDT_가장긴좌우대칭인문자열찾기2_백자민 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int N = str.length()*2+1;

        //문자 사이사이에 # 끼워넣기
        char[] manacher = new char[N];
        for(int i=0,size=str.length();i<size;i++) {
            manacher[i*2] = '#';
            manacher[i*2+1] = str.charAt(i);
        }
        manacher[N-1] = '#';

        int[] A = new int[N];
        int r=-1; //j<i일 때 가장 큰 j+A[j] 값
        int j = -1; //j+A[j](=r) 가 가장 클 때의 j값
        //0번부터 중심점을 잡고 펠린드롬 찾아보기
        for(int i=0;i<N;i++) {

            //j를 기준으로 대칭 위치에 있는 ri를 중심으로 이미 구한 펠린드롬 길이를 이용해 
            //초기값을 잡아 연산을 줄일 수 있다.
            if(r>=i) {
                int ni = 2*j-i;
                A[i] = Math.min(r-i, A[ni]);
            }

            //중심점 i를 기준으로 양쪽을 확인하며 범위 늘려가기
            //범위(0~N)를 벗어나지 않으면서 펠린드롬이면 반지름 길이를 늘리고 반복
            while(i-A[i]-1>=0 && i+A[i]+1<N && manacher[i-A[i]-1]==manacher[i+A[i]+1]) {
                A[i]++;
            }

            if(i+A[i]>r) { //최댓값 갱신
                r = i+A[i];
                j = i;
            }
        }

        Arrays.sort(A); //오름차순 정렬 -> 가장 긴 반지름 길이는 A[N-1]에 저장
        System.out.println(A[N-1]); //처음에 문자 사이에 #을 넣어줬기 때문에 2배는 계산하지 않음.
    }
}
