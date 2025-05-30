import sys

input = lambda: sys.stdin.readline().rstrip()

def calc(p1, p2):
    return abs(p1[0]-p2[0]) + abs(p1[1]-p2[1])

def main():
    N,K = map(int, input().split())
    checkpoints = [tuple(map(int, input().split())) for _ in range(N)]

    INF = sys.maxsize
    dp = [[INF]*(K+1) for _ in range(N)]
    dp[0][0] = 0

    for tmp in range(1, N):
        for k in range(min(tmp, K+1)):
            for sk in range(k+1):
                prev = tmp - sk -1
                if prev>=0 and k-sk>=0 :
                    dp[tmp][k] = min(dp[tmp][k], dp[prev][k-sk]+calc(checkpoints[prev], checkpoints[tmp]))

    print(min(dp[N-1]))

    
if __name__=="__main__":
    main()