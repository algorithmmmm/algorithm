import sys
import heapq

input = lambda: sys.stdin.readline().rstrip()

def dijkstra(start, end, graph, dist):
    q = []
    heapq.heappush(q, [float('inf'), start])
    while q:
        weight, node = heapq.heappop(q)
        weight = abs(weight)
        if node == end:
            return weight

        if dist[node] > weight:
            continue

        for next_node, val in graph[node]:
            if dist[next_node] < weight:
                temp = min(val, weight)
                heapq.heappush(q, [-temp, next_node])
                dist[next_node] = temp

def main():
    N, M = map(int, input().split())
    graph = [[] for _ in range(N + 1)]
    for _ in range(M):
        a, b, c = map(int, input().split())
        graph[a].append((b, c))
        graph[b].append((a, c))

    start, end = map(int, input().split())
    dist = [0] * (N + 1)
    result = dijkstra(start, end, graph, dist)
    print(result)

if __name__ == "__main__":
    main()
