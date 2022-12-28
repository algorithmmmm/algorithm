import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 스택에 탑 높이와 순서가 있는 객체를 저장
 * 1. 신호를 받을 수 있는 탑 발견하기 전까지 낮은 탑들은 스택에서 뽑아버리기
 * 2. 탑 발견하면 탑의 높이 출력
 * 3. 탑이 존재하지 않으면 0출력
 * 4. 새로운 탑을 스택에 추가
 */
public class Main {
	static class Tower { //탑 객체
		int height, index; //높이, 순서

		public Tower(int height, int index) {
			super();
			this.height = height;
			this.index = index;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); //탑의 수
		StringTokenizer st = new StringTokenizer(br.readLine(), " "); //탑들의 높이
		
		Stack<Tower> stack = new Stack<>(); //탑을 담는 스택
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++) { //탑 순서 1~N까지 반복
			int height = Integer.parseInt(st.nextToken());
			
			while(!stack.isEmpty()) { //왼쪽에 있는 탑들 중
				if(stack.peek().height < height) { //더 낮은 탑 있으면
					stack.pop(); //제외시키기
				} else { //높은 탑 있으면
					sb.append(stack.peek().index + " "); //탑들중 가장 오른쪽에 있는 탑의 높이를 출력
					break; //탐색 끝내기(시간초과 원인 제거)
				}
			}
			
			if(stack.isEmpty()) { //레이저 신호를 수신하는 탑이 존재x
				sb.append("0 ");
			}
			
			stack.push(new Tower(height, i)); //새로운 탑을 스택에 추가시키기
		}
		
		//출력
		System.out.println(sb);
	}

}