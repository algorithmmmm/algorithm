package day240403;

import java.io.*;
import java.util.*;

public class CDT_적석과흑석2_백자민 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        TreeSet<Integer> redStone = new TreeSet<>();//빨간돌을 저장할 트리셋
        for(int i=0;i<C;i++) {
            redStone.add(Integer.parseInt(br.readLine()));
        }
        
        PriorityQueue<BlackStone> pq = new PriorityQueue<>(); //검정돌을 저장할 우선순위 큐
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            pq.offer(new BlackStone(a,b));
        } //end input

        int ans=0;
        while(!pq.isEmpty() && !redStone.isEmpty()) { //빨간돌 or 검정돌을 모두 사용하기 전까지 계속해서 매핑
            BlackStone black = pq.poll();
            Integer red = redStone.floor(black.b);

            if(red!=null && red>=black.a) {
                redStone.remove(red);
                ans++;
            }
        }

        System.out.println(ans);
    }

    static class BlackStone implements Comparable<BlackStone> {
        int a,b;

        public BlackStone(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(BlackStone o) {
            int thisGap = this.b-this.a;
            int oGap = o.b-o.a;

            return thisGap-oGap;
        }
    }
}