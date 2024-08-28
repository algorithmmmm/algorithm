import sys

input = lambda: sys.stdin.readline().rstrip()

N = int(input())

homes = list(map(int, input().split()))
times = list(map(int, input().split()))

def is_possible(tmp):
    max_x1 = homes[0] - times[0]*tmp
    min_x2 = homes[0] + times[0]*tmp

    for i in range(1,N):
        x1 = homes[i] - times[i]*tmp
        max_x1 = max(max_x1, x1)
        
        x2 = homes[i] + times[i]*tmp
        min_x2 = min(min_x2, x2)
    
    return max_x1 <= min_x2

l,r=0,1e9
ans = 1e9

for _ in range(100):
    mid = (l+r)/2

    if is_possible(mid):
        r = mid
        ans = min(ans, mid)
    else:
        l = mid

print(f"{ans:.4f}")