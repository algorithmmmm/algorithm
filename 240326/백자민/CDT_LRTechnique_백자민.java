package day240326;

import java.util.*;
import java.io.*;

public class CDT_LRTechnique_백자민 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Point[] points = new Point[N+1];
        StringTokenizer st;
        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            points[i] = new Point(x,y);
        } //end input
        
        //lr배열 채우기
        int[] ldist = new int[N+1];
        int[] rdist = new int[N+1];

        for(int i=2;i<=N;i++) {
            int tmp = Math.abs(points[i].x-points[i-1].x) + Math.abs(points[i].y-points[i-1].y);
            ldist[i] = ldist[i-1]+tmp;
        }

        for(int i=N-1;i>=1;i--) {
            int tmp = Math.abs(points[i].x-points[i+1].x) + Math.abs(points[i].y-points[i+1].y);
            rdist[i] = rdist[i+1]+tmp;
        }

        //모든 구간을 하나씩 건너뛰어보며 최단거리 찾기
        int min = Integer.MAX_VALUE;
        for(int i=2;i<N;i++) {
            int tmp = Math.abs(points[i+1].x-points[i-1].x)+Math.abs(points[i+1].y-points[i-1].y);
            min = Math.min(min, ldist[i-1] + rdist[i+1] + tmp);
        }

        System.out.println(min);
    }

    static class Point {
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}