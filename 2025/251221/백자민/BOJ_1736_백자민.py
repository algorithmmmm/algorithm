import sys

input = lambda:sys.stdin.readline().rstrip()

def clean():
    prev = 0
    removed = 0

    for i in range(N):
        end = -1
        for j in range(prev, M):
            if board[i][j]==1:
                end = j
        
        if end == -1:
            continue

        for j in range(prev, end+1):
            if board[i][j]==1:
                board[i][j]=0
                removed +=1
        
        prev = end
    
    return removed

N,M = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]
ans = 0
total_trash = sum(map(sum, board))

while total_trash>0:
    removed = clean()
    total_trash-=removed
    ans+=1

print(ans)