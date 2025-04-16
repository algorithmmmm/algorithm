import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ1863 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); //고도가 바뀌는 지점의 좌표 개수
		
		Stack<Integer> stack = new Stack<>(); //stack에 y 좌표를 쌓아나감
		
		int answer = 0; //빌딩 개수
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			//고도가 바뀌는 지점의 좌표
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			//새로 들어오는 고도가 이전의 고도보다 낮으면, 제거 및 카운트
			while(!stack.empty() && y < stack.peek()) { //연속으로 제거
				stack.pop();
				answer++;
			}
			
			if(!stack.contains(y)) { //없는 고도면 스택에 넣기
				stack.add(y);
			}
		}
		
		
		//종료 후 스택에 남은거 0이상이면 카운트
		for(int i : stack) { 
			if(i > 0) answer++;
		}
		
		//출력
		System.out.println(answer);
	}

}