package day221223;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import javax.xml.crypto.Data;

public class BOJ_17406_백자민 {
	
	static Point[] cals;
	static int[][] arr;
	static int h;
	static int w;
	static int k;
	static int[][] copyArr;
	
	static int startX;
	static int startY;
	static int endX;
	static int endY;
	static int count;
	static int minVal;
	
	public static void rotation(Point[] temp) {
		for(int i=0; i<k; i++) {
			Point cal = temp[i];
			startX = cal.r - cal.s - 1;
			startY = cal.c - cal.s - 1;
			endX = cal.r + cal.s - 1;
			endY = cal.c + cal.s - 1;
			count = Math.min(endX - startX, endY - startY) / 2; //돌아가는 라인의 수
			
			for(int c=0; c<count; c++) { //라인 전부 돌리기
				int val = copyArr[startX+c][startY+c]; //(c,c)
				
				for(int j=startX+c; j<endX-c; j++) //왼쪽
					copyArr[j][startY+c] = copyArr[j+1][startY+c];
				for(int j=startY+c; j<endY-c; j++) //아래쪽
					copyArr[endX-c][j] = copyArr[endX-c][j+1];
				for(int j=endX-c; j>startX+c; j--) //오른쪽
					copyArr[j][endY-c] = copyArr[j-1][endY-c];
				for(int j=endY-c; j>startY+c; j--) //위쪽
					copyArr[startX+c][j] = copyArr[startX+c][j-1];
				
				copyArr[startX+c][startY+c+1] = val;
			}
			
		}
		int min = Integer.MAX_VALUE;
		for(int i=0; i<h; i++) {
			int cnt = 0;
			for(int j=0; j<w; j++)
				cnt += copyArr[i][j];
			min = Math.min(cnt, min);
		}
		minVal = Math.min(min, minVal);
	}
	
	static void perm(int r, int current, Point[] temp, boolean[] visited) {
		if(current == r) {
			copyArr = new int[h][w];
			for(int i=0; i<h; i++)
				for(int j=0; j<w; j++)
					copyArr[i][j] = arr[i][j];
			rotation(temp);
			return;
		}
		for(int i=0; i<cals.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				temp[current] = cals[i];
				perm(r, current+1, temp, visited);
				visited[i] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		arr = new int[h][w];
		for(int i=0; i<h; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<w; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cals = new Point[k];
		for(int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			cals[i] = new Point(r,c,s);
		}

		boolean[] visited = new boolean[k];
		Point[] temp = new Point[k];
		minVal = Integer.MAX_VALUE;
		perm(k, 0, temp, visited);
		
		System.out.println(minVal);
	}
}

class Point {
	int r;
	int c;
	int s;
	
	public Point(int r, int c, int s){
		this.r = r;
		this.c = c;
		this.s = s;
	}

	@Override
	public String toString() {
		return "Point [r=" + r + ", c=" + c + ", s=" + s + "]";
	}
	
}