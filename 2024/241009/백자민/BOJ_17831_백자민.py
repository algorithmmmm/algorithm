import sys

sys.setrecursionlimit(10**6)
input = lambda: sys.stdin.readline().rstrip()

N = int(input())
graph = [[] for _ in range(N)]
parent = list(map(int, input().split()))
ability = list(map(int, input().split()))
dp = [[0,0] for _ in range(N)] #0:멘토링이 아닌 경우/1:멘토링에 참여한 경우

def dfs(tmp):
    #tmp 노드가 멘토링에 참여하지 않는 경우 = 부사수의 max값 더해주기
    for i in graph[tmp]:
        dfs(i)
        dp[tmp][0] += max(dp[i])
    
    #tmp 노드가 멘토링에 참여하는 경우 = 부사수와 멘토링을 할 때, 부사수가 멘토인 경우와 아닌 경우 중 최댓값을 선택해서 갱신
    for i in graph[tmp]:
        dp[tmp][1] = max(dp[tmp][1], dp[tmp][0] - max(dp[i]) + dp[i][0] + ability[tmp]*ability[i])

def main():
    global graph

    for i,j in enumerate(parent):
        graph[j-1].append(i+1)

    dfs(0)

    print(max(dp[0]))


if __name__=="__main__":
    main()