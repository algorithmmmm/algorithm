import sys, collections

input = lambda:sys.stdin.readline().rstrip()

def main():
    N = int(input())
    board = list(input() for _ in range(N))

    di = [-1,1,0,0]
    dj = [0,0,-1,1]

    si,sj = -1,-1 #시작하는 문 좌표
    ei,ej = -1,-1 #끝나는 문 좌표

    for i in range(N):
        for j in range(N):
            if board[i][j] == '#':
                if si==-1 and sj==-1:
                    si,sj = i,j
                else:
                    ei,ej = i,j
    
    #i,j에 d방향으로 들어올 때 필요한 최소 거울 개수
    check = [[[-1]*4 for _ in range(N)] for _ in range(N)]
    q = collections.deque()

    #시작위치에서 4방향으로 탐색 시작
    for d in range(4):
        q.append((si,sj,d))
        check[si][sj][d] = 0

    while q:
        ti,tj,d = q.popleft()

        #도착
        if ti==ei and tj==ej:
            print(check[ti][tj][d])
            break
        
        ni,nj = ti+di[d], tj+dj[d]

        if 0<=ni<N and 0<=nj<N and board[ni][nj]!='*':
            #처음방문 or 최단거리 발견 ->큐 앞에 삽입
            if check[ni][nj][d] == -1 or check[ni][nj][d]>check[ti][tj][d]:
                check[ni][nj][d] = check[ti][tj][d]
                q.appendleft((ni,nj,d))
            
            #거울 추가로 놓기 -> 큐 뒤에 삽입
            if board[ni][nj]=='!':
                #방향 보정 후 큐에 넣기
                if d<2:
                    for nd in range(2,4):
                        if check[ni][nj][nd]==-1 or check[ni][nj][nd]>check[ti][tj][d]+1:
                            check[ni][nj][nd] = check[ti][tj][d]+1
                            q.append((ni,nj,nd))
                
                else:
                    for nd in range(2):
                        if check[ni][nj][nd]==-1 or check[ni][nj][nd]>check[ti][tj][d]+1:
                            check[ni][nj][nd] = check[ti][tj][d]+1
                            q.append((ni,nj,nd))

if __name__=="__main__":
    main()