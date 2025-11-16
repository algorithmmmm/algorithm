import sys
from bisect import bisect_right, bisect_left

input = lambda: sys.stdin.readline().rstrip()

def main():
    S = input()
    T = input()

    nxt = [[] for _ in range(26)]
    for i in range(len(T)):
        nxt[ord(T[i])-ord('a')].append(i)
    
    if S==T:
        print(1)
        return

    ans = 1
    idx = -1
    bf_idx = -1

    for x in S:
        k = ord(x) - ord('a')
        if not nxt[k]:
            print(-1)
            return
        
        idx = bisect_right(nxt[k], bf_idx)
        if idx == len(nxt[k]):
            ans += 1
            idx = 0
        
        bf_idx = nxt[k][idx]
    
    print(ans)


if __name__=="__main__":
    main()