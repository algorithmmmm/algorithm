import sys
from collections import deque

input = lambda: sys.stdin.readline().rstrip()

N,K,M = map(int, input().split())
hyperStation = [[] for _ in range(N+M)] #0~N-1:역 / N~N+M-1:하이퍼튜브

def bfs():
    q = deque()
    visit = [0 for _ in range(N+M)] #방문 시 최소 거리 저장

    q.append(0)
    visit[0] = 1
    
    while q:
        tmp = q.popleft()
        if tmp==N-1: #목적지 도착
            return visit[tmp]
        
        for next in hyperStation[tmp]:
            if not visit[next]:
                if next>=N: #하이퍼튜브를 사용하는 경우 - 거리 증가X
                    visit[next] = visit[tmp]
                    q.append(next)
                else: #일반 역을 방문하는 경우 - 거리 증가(+1)
                    visit[next] = visit[tmp]+1
                    q.append(next)
    
    return -1 

def main():
    for i in range(M):
        tmp = list(map(int, input().split()))
        for j in range(K):
            hyperStation[tmp[j]-1].append(N+i) 
            hyperStation[N+i].append(tmp[j]-1)
    
    ans = bfs()

    print(ans)


if __name__=="__main__":
    main()

'''
hyperStation[0] -> [10]
hyperStation[1] -> [10]
hyperStation[2] -> [10]

hyperStation[10] -> [0,1,2]
'''