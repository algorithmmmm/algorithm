import sys

input = lambda: sys.stdin.readline().rstrip()

def find_path(n, graph, dist, start,end):
    tmp = start

    #최단거리를 만족하는 경로 중 간선 번호가 가장 작은 곳으로 이동
    print(tmp, end=" ")
    while tmp != end:
        for i in range(1, n+1):
            if graph[i][tmp]==0:
                continue
            
            if dist[i] + graph[i][tmp] == dist[tmp]:
                tmp = i
                break
        
        print(tmp, end=" ")
        

def dijkstra(n, graph, s, e):

    visit = [False]*(n+1)
    dist = [float('inf')] * (n + 1)

    #도착지에서 시작점까지 역방향으로 탐색 진행
    dist[e] = 0

    for i in range(1, n+1):

        min_index = -1
        for j in range(1, n+1):
            if visit[j]:
                continue
            
            if min_index==-1 or dist[min_index] > dist[j]:
                min_index = j
        
        visit[min_index] = True

        for j in range(1, n+1):
            #간선이 없는 경우는 패스
            if graph[min_index][j]==0:
                continue
            
            if dist[j] > dist[min_index] + graph[min_index][j]:
                dist[j] = dist[min_index] + graph[min_index][j]
    
    return dist

def main():
    n, m = map(int, input().split())

    graph = [[0]*(n+1) for _ in range(n + 1)]
    for _ in range(m):
        s, e, w = map(int, input().split())
        graph[s][e] = w
        graph[e][s] = w

    start, end = map(int, input().split())

    # 다익스트라 알고리즘 실행
    dist = dijkstra(n, graph, start, end)

    # 최단 거리 출력
    print(dist[start])

    # 경로 복원 및 출력
    find_path(n, graph, dist, start,end)

if __name__ == "__main__":
    main()