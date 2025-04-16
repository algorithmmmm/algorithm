package day240403;

import java.util.*;

public class CDT_최대숫자만들기_백자민 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        Num[] arr = new Num[N];
        for(int i=0;i<N;i++) {
            arr[i] = new Num(sc.nextInt());
        } //end input

        Arrays.sort(arr);

        StringBuilder sb = new StringBuilder();
        for(Num num: arr) {
            sb.append(num.n);
        }

        System.out.println(sb.toString());
    }

    static class Num implements Comparable<Num> {
        int n;

        public Num(int n) {
            this.n = n;
        }

        @Override
        public int compareTo(Num o) {
            String a = Integer.toString(this.n);
            String b = Integer.toString(o.n);

            return Long.compare(Long.parseLong(b+a), Long.parseLong(a+b));
        }
    }
}