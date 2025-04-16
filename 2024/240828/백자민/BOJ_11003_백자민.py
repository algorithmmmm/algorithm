import sys
from collections import deque

input = lambda: sys.stdin.readline().rstrip()


def main():
    N,L = map(int, input().split())
    A = list(map(int, input().split()))
    D = [0 for _ in range(N)]

    q = deque()
    for i in range(N):

        #더 작은 수를 발견했을 경우 기존 deque에 있던 큰 수 제거
        while q and (q[-1][1]>A[i]):
            q.pop()
        
        #인덱스와 현재 숫자 저장
        q.append((i+1, A[i]))

        #범위를 벗어나는 경우 가장 앞 숫자 삭제
        if q[-1][0]-q[0][0] >= L:
            q.popleft()
    
        D[i] = q[0][1]
    
    print(*D)


if __name__ == "__main__":
    main()