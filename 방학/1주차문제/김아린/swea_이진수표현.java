package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * M�� ������ ǥ���� ������ N��Ʈ�� ��� 1�� ���� �ִ��� �ƴ��� �Ǻ�
 */
public class swea_������ǥ�� {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int mask = (1 << N) - 1; //������ N��Ʈ�� 1�� ���� ex) 4 : 10000 - 1 = 01111
			int result = M & mask; // ex) 47 & 1111 = 101111 & 001111 = 001111
			
			String answer = (mask == result) ? "ON" : "OFF";
			
			System.out.println("#" + t + " " + answer);
		}
	}

}
