import sys

input = lambda: sys.stdin.readline().rstrip()


def main():
    N,M = map(int, input().split())
    male = list(map(int, input().split()))
    female = list(map(int, input().split()))

    male.sort()
    female.sort()

    dp = [[0 for _ in range(M+1)] for _ in range(N+1)]

    for i in range(1, N+1):
        for j in range(1, M+1):
            dp[i][j] = dp[i-1][j-1]+abs(male[i-1]-female[j-1])

            if i>j:
                dp[i][j] = min(dp[i][j], dp[i-1][j])
            elif i<j:
                dp[i][j] = min(dp[i][j], dp[i][j-1])
    
    print(dp[N][M])


if __name__=="__main__":
    main()