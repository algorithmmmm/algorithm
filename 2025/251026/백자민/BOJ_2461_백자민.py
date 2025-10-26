import sys, heapq

input = lambda: sys.stdin.readline().rstrip()

def main():
    N,M = map(int, input().split())
    powers = []
    idx = [1]*1001 #배열 인덱스 저장
    heap = []

    maxPower = 0
    for i in range(N):
        tmp = list(map(int, input().split()))
        tmp.sort()
        maxPower = max(maxPower, tmp[0])
        powers.append(tmp)
        heapq.heappush(heap, (powers[i][0], i))

    ans = 1e9
    while heap:
        first = heapq.heappop(heap)
        minPower = first[0]
        index = first[1]

        ans = min(ans, maxPower-minPower)
        if idx[index] == M:
            break

        heapq.heappush(heap, (powers[index][idx[index]], index))
        maxPower = max(maxPower, powers[index][idx[index]])
        idx[index] += 1
    
    print(ans)

if __name__=="__main__":
    main()