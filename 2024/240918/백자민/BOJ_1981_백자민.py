from collections import deque
import sys

input = lambda: sys.stdin.readline().rstrip()
n = int(input())
board = []
MIN,MAX = 256, -1
dir = [[0,-1],[0,1],[1,0],[-1,0]]

#정해둔 차이값으로 맵을 통과할 수 있는지 bfs 탐색으로 체크
def bfs(a,b):
    visit = [[False for _ in range(n)] for _ in range(n)]

    q = deque([(0,0)])
    visit[0][0] = True

    while q:
        ti,tj = q.popleft()

        #도착점에 도달한 경우
        if ti==n-1 and tj==n-1:
            return True
        
        for di,dj in dir:
            ni,nj = ti+di, tj+dj

            if 0<=ni<n and 0<=nj<n:
                if visit[ni][nj]:
                    continue

                tmp = board[ni][nj]
                if not (a<=tmp<=b):
                    continue

                visit[ni][nj] = True
                q.append((ni,nj))
    return False


def is_possible(s,e,mid):
    for i in range(MIN, MAX+1):
        if i<=s<=i+mid and i<=e<=i+mid:
            if bfs(i,i+mid):
                return True
    
    return False

def main():
    global n,board,MIN,MAX

    for _ in range(n):
        inputs = list(map(int, input().split()))
        for tmp in inputs:
            MIN = min(MIN, tmp)
            MAX = max(MAX, tmp)
        
        board.append(inputs)

    s,e = board[0][0], board[n-1][n-1]

    #이분탐색을 통해 차이값을 정해두고, 해당 차이값으로 맵을 통과할 수 있는지 확인
    l,r = 0,MAX
    ans = 0
    while l<=r:
        mid = (l+r)//2
        if is_possible(s,e,mid):
            ans = mid
            r = mid-1
        else:
            l = mid+1
    
    print(ans)





if __name__ == "__main__":
    main()