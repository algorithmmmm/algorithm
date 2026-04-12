import sys
from collections import deque, defaultdict

input = lambda:sys.stdin.readline().rstrip()

def main():
    N,T = map(int, input().split())
    walls = defaultdict(list)
    for _ in range(N):
        x,y = map(int, input().split())
        walls[y].append(x)
    
    q = deque([(0,0,0)])

    while q:
        x,y,cnt = q.popleft()
        if y==T:
            print(cnt)
            exit()
        for b in range(-2,3):
            if y+b in walls:
                visit = []
                for a in walls[y+b]:
                    if abs(a-x)<=2:
                        q.append((a,y+b,cnt+1))
                        visit.append(a)
                for tmp in visit:
                    walls[y+b].remove(tmp)
    
    print(-1)

if __name__=="__main__":
    main()