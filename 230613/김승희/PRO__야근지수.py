import heapq
def solution(n, works):
    answer = 0
    pq = []
    for w in works:
        heapq.heappush(pq, -w)
        
    while n != 0:
        maxW = -heapq.heappop(pq)
        if maxW < 0: break
        if maxW == -pq[0]:
            heapq.heappush(pq, -(maxW-1))
            n-=1
        else:
            minV = min(n, maxW+pq[0])
            n -= minV
            heapq.heappush(pq, -(maxW-minV))
        
    while pq:
        v = -heapq.heappop(pq)
        if v <= 0:
            continue
        answer += v*v
    return answer