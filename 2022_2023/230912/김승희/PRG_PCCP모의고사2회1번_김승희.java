class Solution {
    // 좌표는 (가로, 세로)
    // URDL 순
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    public int[] solution(String command) {
        int x = 0;
        int y = 0;
        int d = 0;
        for(int i = 0; i < command.length(); i++){
            char op = command.charAt(i);
            if(op == 'R'){
                d = (d+1) % 4;
            }else if(op == 'L'){
                d = d-1 < 0 ? d-1+4 : d-1;
            }else if(op == 'G'){
                x += dx[d];
                y += dy[d];
            }else if(op == 'B'){
                x -= dx[d];
                y -= dy[d];
            }
            //System.out.printf("(%d %d) %d\n", x, y, d);
        }
        return new int[]{x, y};
    }
}