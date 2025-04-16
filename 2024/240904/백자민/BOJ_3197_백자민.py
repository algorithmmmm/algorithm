import sys
from collections import deque

input = sys.stdin.readline

d = [(-1, 0), (0, 1), (1, 0), (0, -1)]

def solution():
    #Union-find 용 함수들
    def find(n):
        temp = n
        while parent[n] != n:
            n = parent[n]
        parent[temp] = n
        return n

    def union(n1, n2):
        n1 = find(n1)
        n2 = find(n2)
        if n1 > n2:
            n1, n2 = n2, n1
        parent[n2] = n1

    R,C = map(int, input().split())
    board = [[*input().rstrip()] for _ in range(R)] 
    parent = [i for i in range(R*C)] #각 물의 그룹을 저장할 배열
    visit = [[0] * C for _ in range(R)]
    swan = []

    _queue = deque() #물 위치를 저장할 큐
    for i in range(R):
        for j in range(C):
            #이미 방문했거나 빙하인 경우 패스
            if visit[i][j]==0 and board[i][j]!='X':
                q = deque([(i,j)])
                visit[i][j] = 1

                while q:
                    ti,tj = q.popleft()
                    if board[ti][tj] == 'L':
                        swan.append((ti,tj))
                    
                    for di,dj in d:
                        ni = ti+di
                        nj = tj+dj

                        if 0<=ni<R and 0<=nj<C:
                            if visit[ni][nj]==0:
                                if board[ni][nj]!='X':
                                    q.append((ni,nj))
                                    union(ti*C+tj, ni*C+nj)
                                else:
                                    _queue.append((ni,nj))
                                
                                visit[ni][nj] = 1

    #두 백조의 그룹
    swan1 = swan[0][0]*C + swan[0][1]
    swan2 = swan[1][0]*C + swan[1][1]
    ans = 0
    q = _queue
    #두 백조의 그룹이 같아지면 만날 수 있는 것
    while find(swan1) != find(swan2):
        ans +=1
        _queue = deque()
        while q:
            ti,tj = q.popleft()
            for di,dj in d:
                ni = ti+di
                nj = tj+dj

                if 0<=ni<R and 0<=nj<C:
                    if visit[ni][nj]==0:
                        visit[ni][nj] = ans+1
                        _queue.append((ni,nj))
                    elif visit[ni][nj] != ans+1:
                        union(ti*C+tj, ni*C+nj)
        
        #일괄변경
        q = _queue

    print(ans)

solution()
