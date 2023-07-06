import heapq
# sources 배열에 있는 지역들로부터 destination까지의 최단 시간을 구하는 것
# 뒤집어서 생각하면 destination부터 source 배열에 있는 노드까지의 최단 시간을 구하면 된다.
# dijkstra가 특정 노드에서 출발해 다른 모든 노드로 가는 최단 비용을 구하는 알고리즘
def solution(n, roads, sources, destination):
    # adj[i] : i에서 갈 수 있는 지역
    adj = [[] for _ in range(n+1)]
    for road in roads:
        adj[road[0]].append(road[1])
        adj[road[1]].append(road[0])
    answer = []
    dist = dijkstra(destination, adj)
    for source in sources:
        ans = -1 if dist[source] == None else dist[source]
        answer.append(ans)
    return answer
# 다익스트라 알고리즘
# dist : start부터 모든 노드까지 가는 최단 시간을 담은 배열
def dijkstra(start, adj):
    dist = [None for _ in range(len(adj))]
    dist[start] = 0
    Q = []
    heapq.heappush(Q, (0, start))
    while Q:
        tp = heapq.heappop(Q)
        if dist[tp[1]] < tp[0]: continue
        for a in adj[tp[1]]:
            d = tp[0] + 1
            if dist[a] == None or dist[a] > d:
                dist[a] = d
                heapq.heappush(Q, (d, a))
    return dist
    