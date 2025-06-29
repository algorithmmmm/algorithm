import sys, heapq

input = lambda: sys.stdin.readline().rstrip()

def main():
    N,K = map(int, input().split())
    gems = [[*map(int, input().split())] for _ in range(N)]
    bags = [int(input()) for _ in range(K)]

    gems.sort() #제일 가벼운 것부터(무게 같으면 가격이 저렴한 것부터)
    bags.sort()

    ans = 0

    tmp = [] #보석 담아둘 리스트
    for bag in bags:
        while gems and gems[0][0]<=bag: #담을 수 있는 보석이면
            heapq.heappush(tmp, -gems[0][1]) #가장 비싼 것부터 담아야하니까 음수로 저장
            heapq.heappop(gems)
        
        if tmp:
            ans -= heapq.heappop(tmp)
    
    print(ans)

if __name__=="__main__":
    main()
