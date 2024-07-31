import sys

input = sys.stdin.readline

N,M = map(int, input().split())
grounds = list(map(int, input().split())) #흙 초기 상태

#명령받은 구간 정보 저장
orders = [0 for _ in range(N+1)]

for _ in range(M):
    a, b, k = map(int, input().split())

    orders[a-1] += k
    orders[b] -= k

#현재 땅의 변화
tmp = 0
for i in range(N):
    tmp += orders[i]
    grounds[i]+=tmp

print(*grounds)
