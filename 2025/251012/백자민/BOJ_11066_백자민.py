import sys
input = lambda: sys.stdin.readline().rstrip()

def main():
    T = int(input())
    for _ in range(T):
        K = int(input())
        files = list(map(int, input().split()))

        # 파일 크기의 누적합
        prefix = [0] * (K + 1)
        for k in range(1, K + 1):
            prefix[k] = prefix[k - 1] + files[k - 1]

        dp = [[0] * (K + 1) for _ in range(K + 1)]  # dp[i][j] = i~j까지 합치는 최소 비용

        for length in range(2, K + 1):  # 구간 길이는 2부터 K까지
            for i in range(1, K - length + 2):
                j = i + length - 1
                dp[i][j] = 1e9

                for k in range(i, j):
                    tmp = dp[i][k] + dp[k + 1][j] + prefix[j] - prefix[i - 1]
                    dp[i][j] = min(dp[i][j], tmp)

        print(dp[1][K])

if __name__ == "__main__":
    main()
