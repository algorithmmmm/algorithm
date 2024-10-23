import sys

input = lambda: sys.stdin.readline().rstrip()

def main():
    N = int(input())
    MOD = 1000000000
    MAX = 1<<10

    dp = [[0]*(MAX) for _ in range(10)] #0:마지막 자리 숫자 / 1:사용한 숫자(비트로 표시)

    #초기화: 1자리수 만들기(0제외)
    for i in range(1,10):
        dp[i][1<<i] = 1
    
    for _ in range(2, N+1):
        next = [[0]*(MAX) for _ in range(10)]

        for i in range(10):
            for j in range(MAX):
                if i>0:
                    next[i][j|(1<<i)] = (next[i][j|(1<<i)]+dp[i-1][j]) % MOD
                if i<9:
                    next[i][j|(1<<i)] = (next[i][j|(1<<i)]+dp[i+1][j]) % MOD
        
        dp = next
    
    ans = sum(dp[i][MAX-1] for i in range(10)) % MOD
    print(ans)



if __name__=="__main__":
    main()