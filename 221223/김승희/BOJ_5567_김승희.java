package boj5567;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

/*
 * BFS를 2번 돌렸다.
 * 처음 시작을 1(상근이)로
 * BFS : 현재 Q의 크기만큼 값을 꺼내서 그 친구의 인접 친구 중 count되지 않은 친구를 다시 Q에 넣는다.
 * count 값에 Q의 크기를 더한다.
 * */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // N : 상근이의 동기 수. 1은 상근이
		int M = Integer.parseInt(br.readLine()); // M : 동기들의 친구 관계의 수.
		ArrayList<ArrayList<Integer>> adjList = new ArrayList<>(); // adjList.get(i) : i와 친구인 사람의 list
		for (int i = 0; i < N + 1; i++) {
			adjList.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < M; i++) {
			String[] inArr = br.readLine().split(" ");
			// a랑 b랑 친구
			int a = Integer.parseInt(inArr[0]);
			int b = Integer.parseInt(inArr[1]);
			adjList.get(a).add(b);
			adjList.get(b).add(a);
		}

		int count = 0; // count : 상근이의 결혼식에 초대할 동기의 수
		boolean[] visit = new boolean[N + 1]; // visit[i] : i번 동기가 count 되었는지 표시
		Queue<Integer> Q = new ArrayDeque<>(); // Q : BFS를 돌릴 때 사용할 Q

		Q.add(1);
		visit[1] = true;

		// p = 0 -> 상근이의 친구가 Q에 들어간다.
		// p = 1 -> 상근이의 친구의 친구가 Q에 들어간다.
		for (int p = 0; p < 2; p++) {
			int size = Q.size();
			for (int q = 0; q < size; q++) {
				int n = Q.poll();
				for (int i = 0; i < adjList.get(n).size(); i++) {
					int getN = adjList.get(n).get(i);
					if (visit[getN])
						continue;
					Q.add(getN);
					visit[getN] = true;
				}
			}
			count += Q.size();
		}

		System.out.println(count);
	}

}
/*
4
3
1 2
1 3
4 3

3
---------------
4
1
2 4

0
---------------
4
1
1 4

1
---------------
 * */
