import sys

input = lambda:sys.stdin.readline().rstrip()

def find(x):
    if x != parent[x]:
        parent[x] = find(parent[x])
    return parent[x]

def union(x,y):
    x = find(x)
    y = find(y)

    if x==y:
        return
    
    if rank[x] > rank[y]:
        parent[y] = x
    elif rank[x] < rank[y]:
        parent[x] = y
    else:
        parent[x] = y
        rank[y]+=1


N,M = map(int, input().split())
parent = [i for i in range(N+1)]
rank = [0]*(N+1)

cnt = 0

for _ in range(M):
    x,y = map(int, input().split())

    #사이클이 있는 경우
    if find(x) == find(y):
        cnt+=1
    else:
        union(x,y)
    
set_cycle = set()
for i in range(1, N+1):
    set_cycle.add(find(i))
    
print(cnt+len(set_cycle)-1)