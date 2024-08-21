import sys,heapq

input = lambda: sys.stdin.readline().rstrip()

MOD = 40000
N = int(input())
heap = []

def calc(a,b):
    #분모가 0인 경우 제일 뒤로 보내기
    if a==0:
        return float('inf')
    #분가자가 0인 경우는 제일 앞으로
    elif b==0:
        return 0
    
    #b/a가 가장 작은 것이 가장 높은 우선순위를 가짐
    return b/a

for _ in range(N):

    a,b = map(int, input().split())
    heapq.heappush(heap, (calc(a,b),a,b))

t=0
while heap:
    tmp = heapq.heappop(heap)

    #a가 0이라면 어차피 b값만 더해주므로 a값을 곱하는 과정 생략
    if tmp[0]<float('inf'):
        t += t*tmp[1]

    #b값 더해주기
    t += tmp[2]
    t %= MOD

print(t)

"""
input
10
10 1
0 0
8 2
0 0
6 0
0 0
4 8
0 0
0 8
1 8

output
142
"""
