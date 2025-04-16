import sys
import heapq

input = sys.stdin.readline

INF = float('inf') 
N,M = map(int, input().split())
graph = [[] for _ in range(N+1)] #간선정보 저장
dist = [INF for _ in range(N+1)] 
dp = [0 for _ in range(N+1)] #각 노드별 합리적인 경로의 수를 저장할 dp배열

for _ in range(M):
    a,b,c = map(int, input().split())
    graph[a].append((c,b))
    graph[b].append((c,a))
#end input

#다익스트라 수행 함수
def dijkstra(start):
    dist[start] = 0
    heap = []
    heapq.heappush(heap, (dist[start], start))

    while heap:
        td, tv = heapq.heappop(heap)

        #연산을 줄이기 위한 가지치기
        if td > dist[tv]:
            continue

        for nw, nv in graph[tv]:
            if dist[tv]+nw < dist[nv]:
                dist[nv] = dist[tv] + nw
                heapq.heappush(heap, (dist[nv], nv))

            #거리가 더 짧다면 합리적인 경로를 발견한 것
            if dist[tv] > dist[nv]:
                dp[tv] += dp[nv]


#도착점=2
dp[2] = 1
dijkstra(2) #도착점에서부터 다른 모든 노드까지 최단거리 찾기

print(dp[1])