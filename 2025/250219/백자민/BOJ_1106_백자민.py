import sys

input = lambda: sys.stdin.readline().rstrip()

def main():
    C,N = map(int, input().split())
    advertise = [list(map(int, input().split())) for _ in range(N)]

    dp = [1e7 for _ in range(C+100)]
    dp[0] = 0 

    for c,n in advertise:
        for i in range(n, C+100):
            dp[i] = min(dp[i-n]+c, dp[i]) #최소비용 저장
    
    print(min(dp[C:])) #고객이 최소 c명 이상인 것 중 최솟값 출력

if __name__=="__main__":
    main()


"""

dp = [[12,2],[3,5],[1,1]]

"""
