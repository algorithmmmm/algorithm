package day240521;

import java.util.*;
import java.io.*;

public class CDT_길이가무한대인수평선트랙_백자민ㄴ {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        TreeSet<Long> set = new TreeSet<>();
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());

            long end = start+(long)speed*T; //도착점 계산
            //출발 기준 오름차순이므로 현재 사람이 T분 후에 도착한 지점보다 더 멀리 도착한 사람이 존재할 경우
            //그 사람은 현재 사람을 반드시 지나치게 되어있다.
            while(set.higher(end)!=null) {
                //마주치는 순간 이전에 출발한 사람이 현재 사람의 속도에 맞추므로 이전에 출발한 사람을 set에서 제거
                set.remove(set.higher(end)); 
            }
            set.add(end); //현재 사람은 그대로 달리므로 set에 추가(현재 사람을 기준으로 그룹 형성)
        }

        System.out.println(set.size()); //set에 남아있는 사람들은 그룹의 기준 속도가 되는 사람들 = 그룹 대표자
    }

}
