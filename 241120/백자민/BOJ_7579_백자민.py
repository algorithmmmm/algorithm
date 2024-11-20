import sys

input = lambda:sys.stdin.readline().rstrip()


def main():
    #input / 1-index 사용
    N,M  = map(int, input().split())
    memorys = [0]+list(map(int, input().split()))
    costs = [0]+list(map(int, input().split()))

    max_cost = sum(costs) #가능한 최대 비용(앱을 모두 껐다 킬 경우)
    dp = [[0 for _ in range(max_cost+1)] for _ in range(N+1)] #i번째 앱까지 비용 j로 얻을 수 있는 최대 메모리값
    ans = max_cost

    for i in range(1,N+1):
        for j in range(0, max_cost+1):
            if j< costs[i]: #현재 앱의 비용이 j보다 클 경우 그대로 둠(비활성화X)
                dp[i][j] = dp[i-1][j]
            else: #j보다 비용이 작다면 비활성화 했을 때의 비용과 비교해 갱신
                dp[i][j] = max(dp[i-1][j], dp[i-1][j-costs[i]]+memorys[i])
            
            if dp[i][j]>=M: 
                ans = min(ans, j)
    
    print(ans)


if __name__=="__main__":
    main()