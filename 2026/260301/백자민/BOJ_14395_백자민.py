import sys
from collections import deque

input = lambda:sys.stdin.readline().rstrip()

def bfs(s):
    q = deque()
    q.append((s, ''))

    visit = set()
    visit.add(s)
    INF = 10e9

    while q:
        s, path = q.popleft()

        if s==T:
            return path
        
        nxt = s*s
        if 0 <= nxt <= INF and nxt not in visit:
            q.append((nxt, path+'*'))
            visit.add(nxt)
        
        nxt = s+s
        if 0 <= nxt <= INF and nxt not in visit:
            q.append((nxt, path+'+'))

        nxt = 1
        if nxt not in visit:
            q.append((1, path+'/'))
            visit.add(nxt)
    
    return -1

S,T = map(int, input().split())

if S==T:
    print(0)
else:
    print(bfs(S))

