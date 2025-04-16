package day240409;

import java.util.*;
import java.io.*;

public class CDT_쪼개어배낭채우기구현_백자민 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<Gem> pq = new PriorityQueue<>(); //무게 대비 가격이 높은 순으로 정렬
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            double v = Double.parseDouble(st.nextToken());

            pq.offer(new Gem(w, v/w));
        }

        double sum=0.000;
        while(!pq.isEmpty()) { //가방이 꽉 차거나 큐가 빌 때까지 무게당 가치가 높은 보석을 최대한 많이 넣기
            if(M==0) break;

            Gem tmp = pq.poll();
            if(M>=tmp.weight) { //보석을 자르지 않고 넣는 경우
                sum += (tmp.weight*tmp.pricePerWeight);
                M-=tmp.weight;
            } else { //보석을 자르는 경우
                sum += (M*tmp.pricePerWeight);
                break;
            }
        }

        sum = Math.round(sum*1000)/1000.0;
        System.out.printf("%.3f", sum);
    }

    static class Gem implements Comparable<Gem> {
        int weight;
        double pricePerWeight;

        public Gem(int weight, double pricePerWeight) {
            this.weight = weight;
            this.pricePerWeight = pricePerWeight;
        }

        @Override
        public int compareTo(Gem o) {
            double res = o.pricePerWeight-this.pricePerWeight;

            if(res<0) return -1;
            if(res==0) return 0;

            return 1;
        }
    }
}
