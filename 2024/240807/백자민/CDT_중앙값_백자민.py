import sys
from heapq import heappush, heappop

input = lambda: sys.stdin.readline().rstrip()

def get_median() -> None:
    #1번째 중앙값은 입력값이므로 초기화하고 시작
    #left는 중앙값보다 작은 값이 들어있는 최대힙, right는 중앙값보다 큰 값이 들어있는 최소힙
    left, right, ans = [], [], [nums[0]]
    mid = nums[0]

    for i,n in enumerate(nums[1:], 1):
        #중앙값과 비교해 적절한 힙에 넣기
        if n<mid:
            heappush(left, -n)
        else:
            heappush(right, n)
        
        if i%2!=0:
            continue
        
        #홀수번째 숫자일 경우 중앙값 갱신 후 배열에 저장
        #원소 개수가 더 적은 힙에 현재 중앙값을 넣고, 더 많은 힙에서 새로운 중앙값 추출 후 저장
        if len(left) > len(right):
            heappush(right, mid)
            mid = -heappop(left)
        elif len(left) < len(right):
            heappush(left, -mid)
            mid = heappop(right)
        
        #두 힙의 크기가 같다면 중앙값 갱신X
        ans.append(mid)
    
    #지금까지 찾은 중앙값 출력
    print(*ans)



T = int(input())

for _ in range(T):
    M = int(input())
    nums = list(map(int, input().split()))

    get_median()