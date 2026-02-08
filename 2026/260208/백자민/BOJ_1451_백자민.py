import sys

input = lambda:sys.stdin.readline().rstrip()

def main():
    N,M = map(int, input().split())
    board = [list(map(int, list(input()))) for _ in range(N)]
    ans = 0

    #1.세로로만 3등분 |||
    for i in range(1, M-1):
        for j in range(i+1, M):
            a1 = sum([board[x][y] for x in range(N) for y in range(i)])
            a2 = sum([board[x][y] for x in range(N) for y in range(i,j)])
            a3 = sum([board[x][y] for x in range(N) for y in range(j,M)])

            ans = max(ans, a1*a2*a3)

    #2.가로로만 3등분
    for i in range(1, N-1):
        for j in range(i+1, N):
            a1 = sum([board[x][y] for x in range(i) for y in range(M)])
            a2 = sum([board[x][y] for x in range(i,j) for y in range(M)])
            a3 = sum([board[x][y] for x in range(j,N) for y in range(M)])

            ans = max(ans, a1*a2*a3)
    
    #3.위에 하나, 아래 세로 두 개
    #-----
    #| | |
    for i in range(1, N):
        for j in range(1, M):
            a1 = sum([board[x][y] for x in range(i) for y in range(M)])
            a2 = sum([board[x][y] for x in range(i,N) for y in range(j)])
            a3 = sum([board[x][y] for x in range(i,N) for y in range(j,M)])

            ans = max(ans, a1*a2*a3)

    #4.3번 뒤집기
    for i in range(1, M):
        for j in range(1, N):
            a1 = sum([board[x][y] for x in range(j) for y in range(i)])
            a2 = sum([board[x][y] for x in range(j) for y in range(i,M)])
            a3 = sum([board[x][y] for x in range(j,N) for y in range(M)])

            ans = max(ans, a1*a2*a3)

    #5.4번 반대
    for i in range(1, N):
        for j in range(1, M):
            a1 = sum([board[x][y] for x in range(i) for y in range(j)])
            a2 = sum([board[x][y] for x in range(i,N) for y in range(j)])
            a3 = sum([board[x][y] for x in range(N) for y in range(j,M)])

            ans = max(ans, a1*a2*a3)

    #6.왼쪽에 하나 오른쪽 두 개
    # |  |  |
    # |  |--|
    # |  |  |
    for i in range(1, N):
        for j in range(1, M):
            a1 = sum([board[x][y] for x in range(i) for y in range(j,M)])
            a2 = sum([board[x][y] for x in range(i,N) for y in range(j,M)])
            a3 = sum([board[x][y] for x in range(N) for y in range(j)])

            ans = max(ans, a1*a2*a3)

    print(ans)

if __name__=="__main__":
    main()