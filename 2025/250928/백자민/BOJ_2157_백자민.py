import sys

input = lambda:sys.stdin.readline().rstrip()

def main():
    N,M,K = map(int, input().split())
    graph = [[0]*(N+1) for _ in range(N+1)] #[i][j] = i->j value 최댓값
    dp = [[0]*(M+1) for _ in range(N+1)] #[i][j] = 1->i 중 j개의 도시를 걸쳐 갈 수 있는 최댓값

    for _ in range(K):
        s,e,v = map(int, input().split())
        if s>e:
            continue
        graph[s][e] = max(graph[s][e], v)
    
    for i in range(2, N+1):
        dp[i][2] = graph[1][i]
    
    for i in range(2, N+1): #도착도시
        for j in range(3, M+1): #도시개수
            for k in range(1, i): #중간도시
                if graph[k][i] and dp[k][j-1]:
                    dp[i][j] = max(dp[i][j], dp[k][j-1]+graph[k][i])
    
    print(max(dp[N]))




if __name__=="__main__":
    main()