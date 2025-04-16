package day_240116;

import java.util.*;

public class CDT_아름다운수_백자민 {

	static int N, cnt;

	static void func(int n) {
		if (n==N) {
			cnt++;
			return;
		}

		for (int i=1;i<5;i++) {
			if (n+i>N)
				return; // N자리수 넘어감

			func(n+i);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		cnt = 0;
		func(0);
		System.out.println(cnt);
	}
}
