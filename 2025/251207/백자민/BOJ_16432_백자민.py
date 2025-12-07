import sys

sys.setrecursionlimit(10**9)
input = lambda:sys.stdin.readline().rstrip()

def dfs(cnt, rices, prev):
    global ans
    
    if cnt == N:
        for r in rices:
            print(r)
        exit()
    
    for rice in days[cnt]:
        if rice != prev and not visit[cnt][rice-1]:
            visit[cnt][rice-1] = True
            dfs(cnt+1, rices+[rice], rice)

N = int(input())
ans = []
days = []

for _ in range(N):
    m, *rice = map(int, input().split())
    days.append(rice)

visit = [[False] * 10 for _ in range(N+1)]
dfs(0,[],0)

print(-1)


