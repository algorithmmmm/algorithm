import sys,heapq

input = lambda: sys.stdin.readline().rstrip()

n = int(input())
parent = [i for i in range(n+1)]

def find(x):
    global parent

    if x != parent[x]:
        parent[x] = find(parent[x])
    return parent[x]

def union(a,b):
    global parent

    a = find(a)
    b = find(b)

    parent[b] = a


def main():
    xlist,ylist,zlist = [],[],[] #x,y,z 좌표 tuple 저장 - (좌표,행성번호)
    for i in range(n):
        x,y,z = map(int, input().split())
        xlist.append((x,i))
        ylist.append((y,i))
        zlist.append((z,i))
    
    #오름차순 정렬(가장 인접한 행성을 먼저 연결하기 위함)
    xlist.sort()
    ylist.sort()
    zlist.sort()

    #가장 인접한 행성끼리 간선 구성
    edges = [] #인접한 행성끼리의 간선 정보를 저장할 최소힙(오름차순 정렬)
    for tmp in xlist,ylist,zlist:
        for i in range(n-1):
            v1,x = tmp[i]
            v2,y = tmp[i+1]
            heapq.heappush(edges, (abs(v1-v2),x,y))

    #kruscal 알고리즘 수행

    t,ans = n-1,0
    while t:
        dist,a,b = heapq.heappop(edges)
        if find(a)==find(b): #사이클이 생긴다면 간선을 만들 수 없음
            continue
        
        t-=1
        union(a,b)
        ans+=dist
    
    print(ans)



if __name__ == "__main__":
    main()