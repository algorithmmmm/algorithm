package day240206;

import java.util.*;
import java.io.*;

public class CDT_겹치지않게선분고르기2_백자민 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Meeting[] list = new Meeting[N];
        StringTokenizer st;
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            list[i] = new Meeting(s,e);
        } //end input

        Arrays.sort(list);

        int[] dp = new int[N];
        for(int i=0;i<N;i++) {
            dp[i] = 1;
            for(int j=0;j<i;j++) {

                if(list[j].e >= list[i].s) continue;

                dp[i] = Math.max(dp[i], dp[j]+1);
            }
        }

        int max=0;
        for(int i: dp) {
            max = Math.max(i, max);
        }

        System.out.println(max);
    }

    static class Meeting implements Comparable<Meeting> {
        int s,e;

        public Meeting(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Meeting o) {
            return this.s-o.s;
        }
    }
}
