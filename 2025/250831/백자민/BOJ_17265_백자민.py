import sys

input = lambda: sys.stdin.readline().rstrip()
di, dj = [1,0], [0,1] #U-D

def dfs(i,j,route,flag):
    global min_ans, max_ans
    
    if i==N-1 and j==N-1: #도착
        ans = eval(''.join(route))
        max_ans = max(ans, max_ans)
        min_ans = min(ans, min_ans)
        return

    for d in range(2):
        ni = i+di[d]
        nj = j+dj[d]

        if 0<=ni<N and 0<=nj<N and not visit[ni][nj]:
            visit[ni][nj] = True
            if flag: #부호가 등장할 차례
                dfs(ni,nj,route+board[ni][nj], False)
            else:
                dfs(ni,nj,"("+route+board[ni][nj]+")",True)
            visit[ni][nj] = False



N = int(input())
board = [list(input().split()) for _ in range(N)]
visit = [[False for _ in range(N)] for _ in range(N)]

max_ans = -1e9
min_ans = 1e9

visit[0][0] = True
dfs(0,0,board[0][0], True)

print(max_ans, min_ans)