package day240220;

import java.util.*;
import java.io.*;

public class CDT_k번째로작은쌍의합_백자민 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] nlist = new int[N];
        int[] mlist = new int[M];

        st =new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            nlist[i] = Integer.parseInt(st.nextToken());
        }

        st =new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++) {
            mlist[i] = Integer.parseInt(st.nextToken());
        } //end input

        Arrays.sort(nlist);
        Arrays.sort(mlist);

        PriorityQueue<Integer> q = new PriorityQueue<>(Comparator.reverseOrder());

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                int tmp = nlist[i]+mlist[j];

                if(q.size()==K && tmp>q.peek()) {
                    break;
                }
                q.offer(tmp);
                if(q.size()>K) {
                    q.poll();
                }
            }
        }

        System.out.println(q.peek());
    }
}