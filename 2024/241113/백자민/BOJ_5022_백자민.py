import sys
from collections import deque

input = lambda: sys.stdin.readline().rstrip()

#global var
INF = 1e9
di = [-1,1,0,0]
dj = [0,0,-1,1]
N,M = map(int, input().split())

#시작점부터 도착점까지의 경로를 확정하고 기록하는 함수
def make_path(si,sj,ei,ej,visit,path):
    while True:
        visit[si][sj] = True
        if si==ei and sj==ej:
            break
        si,sj = path[si][sj]

#bfs 탐색 함수
def bfs(si,sj,ei,ej,visit,path):
    q = deque([(si,sj)])
    visit[si][sj] = 1

    while q:
        ti,tj = q.popleft()

        #도착한 경우 경로의 길이 리턴
        if ti==ei and tj==ej:
            return visit[ei][ej]-1
        
        for d in range(4):
            ni = ti+di[d]
            nj = tj+dj[d]

            if 0<=ni<=N and 0<=nj<=M:
                if visit[ni][nj]:
                    continue
                
                q.append((ni,nj))
                visit[ni][nj] = visit[ti][tj]+1
                path[ni][nj] = (ti,tj)
    
    #두 점을 잇지 못하는 경우 INF 리턴
    return INF


def calc(si1,sj1,si2,sj2,ei1,ej1,ei2,ej2): #s1~s2 사이를 잇고 e1~e2를 이은 값을 리턴하는 함수
    path = [[(0,0) for _ in range(M+1)] for _ in range(N+1)] #경로를 저장할 배열

    visit = [[0]*(M+1) for _ in range(N+1)] #탐색에 사용할 방문처리 배열 
    #나중에 이을 점 e1,e2를 지나지 않도록 미리 방문처리
    visit[ei1][ej1] = 1
    visit[ei2][ej2] = 1
    s_dist = bfs(si1,sj1,si2,sj2,visit,path)

    #앞서 찾은 최솟값 경로 확정하기(방문처리)
    visit = [[0]*(M+1) for _ in range(N+1)]
    make_path(si2,sj2,si1,sj1,visit,path)

    #위 경로를 제외한 경로로 나머지 두 점 잇기
    e_dist = bfs(ei1,ej1,ei2,ej2,visit,path)

    return s_dist+e_dist

def main():
    ai1, aj1 = map(int, input().split())
    ai2, aj2 = map(int, input().split())
    bi1, bj1 = map(int, input().split())
    bi2, bj2 = map(int, input().split())

    a_and_b = calc(ai1, aj1, ai2, aj2, bi1, bj1, bi2, bj2) #A먼저 잇고 B 이어보기
    b_and_a = calc(bi1, bj1, bi2, bj2, ai1, aj1, ai2, aj2) #B먼저 잇고 A 이어보기

    ans = min(a_and_b, b_and_a)

    if ans >= INF:
        print("IMPOSSIBLE")
    else:
        print(ans)


if __name__=="__main__":
    main()