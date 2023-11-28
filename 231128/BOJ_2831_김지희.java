import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine(), " "); // 남자
		ArrayList<Integer> man_wanna_taller = new ArrayList<>();
		ArrayList<Integer> man_wanna_smaller = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			int now = Integer.parseInt(st.nextToken());
			if (now < 0) {
				man_wanna_smaller.add(now*(-1));
			} else {
				man_wanna_taller.add(now);
			}
		}

		st = new StringTokenizer(br.readLine(), " ");
		ArrayList<Integer> woman_wanna_taller = new ArrayList<>();
		ArrayList<Integer> woman_wanna_smaller = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			int now = Integer.parseInt(st.nextToken());
			if (now < 0) {
				woman_wanna_smaller.add(now*(-1));
			} else {
				woman_wanna_taller.add(now);
			}
		}
		
		Collections.sort(man_wanna_smaller);
		Collections.sort(woman_wanna_smaller);
		Collections.sort(man_wanna_taller);
		Collections.sort(woman_wanna_taller);
		
		int answer = 0;
		answer= find(woman_wanna_smaller, man_wanna_taller);
		answer+= find(man_wanna_smaller, woman_wanna_taller);
		
		System.out.println(answer);
		
//		for(int i : man_wanna_smaller) {
//			System.out.println(i);
//		}
	}

	public static int find(ArrayList<Integer> smaller, ArrayList<Integer> taller) {
		int small_idx = 0;
		int tall_idx = 0;
		int count = 0;
		
		while(tall_idx<taller.size() && small_idx<smaller.size()) {
			if(taller.get(tall_idx)<smaller.get(small_idx)) { // 큰 사람을 원하는 사람이 작고, 작은 사람을 원하는 사람이 큰거
				// 키 차이 나면
				count++;
				small_idx++;
				tall_idx++;
			}
			else { // 만약 taller를 원하는데 본인이 더 큼(smaller를 원하는데 본인이 더 작음)
				small_idx++;
			}
		}
		
		return count;
	}
}