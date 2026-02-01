import sys

input = lambda:sys.stdin.readline().rstrip()

def main():
    N = int(input())
    train = list(map(int, input().split()))
    train.insert(0,0)
    maximum = int(input())

    dp = [[0]*(N+1) for _ in range(4)]

    prefix = [0]
    for i in range(1, N+1):
        prefix.append(prefix[i-1]+train[i])
    
    for i in range(1,4):
        for j in range(maximum, N+1):
            dp[i][j] = max(dp[i][j-1], dp[i-1][j-maximum]+prefix[j]-prefix[j-maximum])
    
    print(dp[3][N])


if __name__=="__main__":
    main()