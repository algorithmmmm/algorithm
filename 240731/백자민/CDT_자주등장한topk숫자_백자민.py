import sys
from collections import defaultdict, Counter
import heapq

input = sys.stdin.readline

n,k = map(int, input().split())
inputs = list(map(int, input().split())) #입력받은 배열

def useDict():
    hmap = defaultdict(lambda: 0) #key=등장한 숫자 / value=등장 횟수(기본값=0)

    for n in inputs:
        hmap[n]+=1
    
    #(등장횟수, 등장한 숫자) 튜플을 사용해 최대 힙 생성(내림차순 정렬 위해 부호 변경)
    heap = [(-freq, -num) for num, freq in hmap.items()]

    #최대힙 heapify
    heapq.heapify(heap)

    #힙에서 k개의 숫자 추출
    return [-heapq.heappop(heap)[1] for _ in range(k)]

def useCounter():
    count = Counter(inputs)

    heap = [(-freq, -num) for num, freq in count.items()]
    heapq.heapify(heap)
    return [-heapq.heappop(heap)[1] for _ in range(k)]

# print(*useDict())
print(*useCounter())