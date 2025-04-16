package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 1. �缼�� : ù ��° N�� ��, �� ��° 2N�� �� ... k��° kN����
 * 2. ���� �� ���� : ex) 1295�� -> 1, 2, 9, 5
 * 3. 0~9 ��� ���ڸ� ���� �� ���� ����  
 */
public class swea_���ο�Ҹ���ġ��� {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine()); //ù��° �� ��ȣ
			
			int visited = 0; //������� �� ���ڸ� ��Ʈ�� ǥ��, 0000000000(2)
			int goal = (1 << 10) - 1; //��� ���ڸ� ���� �Ǿ��� ���� ��, 10000000000 - 1 = 111111111(2)
			
			int x = 1;
			while(true) {
				int sheep = x * N; //x��° �� ��ȣ
				
				char[] chs = String.valueOf(sheep).toCharArray();
				for(int i = 0; i < chs.length; i++) {
					int num = chs[i] - '0';
					visited = visited | (1 << num); //�ش� ���ڰ� ���� �Ǿ� 1�� ����
				}
				
				if(visited == goal) break; //��� ���ڰ� �����ϸ� ����
				
				x++;
			}
			
			sb.append("#" + t + " " + x * N + "\n");
		}
		System.out.println(sb);
	}

}
