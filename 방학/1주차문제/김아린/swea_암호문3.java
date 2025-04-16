package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class swea_암호문3 {
	static BufferedReader br;
	static StringTokenizer st;
	static List<Integer> list;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int t = 1; t <= 10; t++) {
			int N = Integer.parseInt(br.readLine()); // 원문 암호문의 길이

			list = new ArrayList<>(); // 암호문 저장

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}

			int M = Integer.parseInt(br.readLine()); // 명령어의 개수
			st = new StringTokenizer(br.readLine(), " "); // 명령어

			for (int i = 0; i < M; i++) {
				process(); // 명령어 처리
			}

			// 출력
			sb.append("#" + t + " ");

			for(int i = 0; i < 10; i++) {
				sb.append(" " + list.get(i));
			}

			sb.append("\n");
		}

		System.out.println(sb);
	}

	public static void process() {
		String order = st.nextToken();

		switch (order) {
		case "I":
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			for (int i = 0; i < y; i++) {
				int s = Integer.parseInt(st.nextToken());
				list.add(x + i, s);
			}

			break;
		case "D":
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());

			for (int i = 0; i < y; i++) {
				list.remove(x);
			}

			break;
		case "A":
			y = Integer.parseInt(st.nextToken());

			for (int i = 0; i < y; i++) {
				int s = Integer.parseInt(st.nextToken());
				list.add(s);
			}

			break;
		}
	}

}
