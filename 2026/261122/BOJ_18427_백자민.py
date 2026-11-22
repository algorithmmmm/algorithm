import sys
from copy import deepcopy

input = lambda:sys.stdin.readline().rstrip()

def main():
    N,M,H = map(int, input().split())
    info = [list(map(int, input().split())) for _ in range(N)]

    ans = [[1]+[0]*H for _ in range(N+1)]

    for i in range(1, N+1):
        ans[i] = deepcopy(ans[i-1]) #블록사용X
        for j in info[i-1]:
            for k in range(j, H+1):
                ans[i][k] += ans[i-1][k-j]

    print(ans[N][H]%10007)


if __name__=="__main__":
    main()