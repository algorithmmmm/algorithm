from collections import deque

def bfs(i,j):
    q = deque([(i,j)])
    visit[i][j] = 1

    while q:
        ti,tj = q.popleft()

        for d in range(4):
            ni = ti+di[d]
            nj = tj+dj[d]

            if ni<0 or ni>=n or nj<0 or nj>=m:
                continue
            if visit[ni][nj]==1 or board[ni][nj]==0:
                continue
            
            visit[ni][nj]=1
            q.append((ni,nj))
    return
    
def rain(k):
    count=0

    for i in range(n):
        for j in range(m):
            if 0<board[i][j]<=k:
                board[i][j]=0
                count+=1
    
    return count

di = [0,0,-1,1]
dj = [-1,1,0,0]

n,m = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(n)]

safezone=0
maxK=1
cnt=n*m #남은 땅 개수

for k in range(1,101):
    
    cnt -= rain(k)
    
    tmp=0
    visit = [[0]*m for _ in range(n)]
    for i in range(n):
        for j in range(m):
            if board[i][j]>0 and visit[i][j]==0:
                bfs(i,j)
                tmp+=1
    
    if tmp>safezone:
        safezone = tmp
        maxK=k
    
    #모든 건물이 물에 잠긴 경우
    if cnt==0: 
        break

print(maxK, safezone)