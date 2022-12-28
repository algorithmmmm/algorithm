package boj18442;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
/*
 * V개의 마을 중 P개를 뽑아 우체국을 설치한다. -> combination
 * 우체국이 설치되어 있지 않은 마을을 기준으로 오른쪽과 왼쪽을 보며, 우체국이 설치되어 있는 마을을 찾는다.
 * 찾은 2개의 마을과의 거리를 각각 구해서 더 가까운 거리를 우체국과의 거리로 결정한다.
 * */
public class Main {

	static int V, P;
	static long L, result;
	static int[] Select;
	static long[] Villege, Post, Dist;
	static boolean[] post;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inarr = br.readLine().split(" ");
		V = Integer.parseInt(inarr[0]); // V : 마을의 개수
		P = Integer.parseInt(inarr[1]);
		; // P : 우체국의 개수
		L = Long.parseLong(inarr[2]);
		; // L : 큰 길의 들레의 길이
		inarr = br.readLine().split(" ");
		Villege = new long[V];
		for (int i = 0; i < V; i++) {
			Villege[i] = Long.parseLong(inarr[i]); // Villege : V개의 마을의 위치
		}
		Select = new int[P]; // Select[i] : 해당 위치의 마을에 우체국을 세운다
		result = Long.MAX_VALUE; // result : 우체국 사이의 거리의 합이 최소인 경우
		combination(0, 0);
		System.out.println(result);
		for (int i = 0; i < P; i++) {
			System.out.print(Post[i] + " ");
		}
		System.out.println();
	}

	static void combination(int cnt, int idx) {
		if (cnt == P) {
			Dist = new long[V];
			post = new boolean[V];
			for (int i = 0; i < V; i++) {
				Dist[i] = Long.MAX_VALUE;
			}
			for (int i = 0; i < P; i++) {
				post[Select[i]] = true;
				Dist[Select[i]] = 0;
			}
			process();
			long sum = 0;
			for (int i = 0; i < V; i++) {
				sum += Dist[i];
			}
			if (result > sum) {
				result = sum;
				Post = new long[P];
				for (int i = 0; i < P; i++) {
					Post[i] = Villege[Select[i]];
				}
			}
			return;
		}

		for (int i = idx; i < V; i++) {
			Select[cnt] = i;
			combination(cnt + 1, i + 1);
		}

	}

	// 우체국이 설치되어 있지 않은 마을들을 대상으로
	// 오른쪽과 왼쪽을 보며 우체국이 있는 마을을 찾는다. 이때 가장 가까운 마을을 찾아 거리를 구한다.
	static void process() {
		for (int i = 0; i < V; i++) {
			if (!post[i])
				continue;
			// 오른쪽
			int nr = (i + 1) % V;
			while (true) {
				if (post[nr]) {
					break;
				}
				Dist[nr] = Math.min(Dist[nr], distance(Villege[i], Villege[nr]));
				nr = (nr + 1) % V;
			}
			// 왼쪽
			int nl = (i - 1 + V) % V;
			while (true) {
				if (post[nl]) {
					break;
				}
				Dist[nl] = Math.min(Dist[nl], distance(Villege[i], Villege[nl]));
				nl = (nl - 1 + V) % V;
			}

		}
	}

	// 파라미터로 2개의 마을 위치가 들어온다.
	// 2개의 마을의 거리를 반환한다.
	static long distance(long a, long b) {
		long absN = Math.abs(a - b);
		return Math.min(absN, L - absN);
	}

}
/*
 * 5 1 51 
 * 6 9 22 44 50
 * 
 * 39 
 * 6 --------------- 
 * 5 5 51 
 * 6 9 22 44 50
 * 
 * 0 
 * 6 9 22 44 50 --------------- 
 * 1 1 200 6
 * 
 * 0 
 * 6 ---------------
 * 
 */
