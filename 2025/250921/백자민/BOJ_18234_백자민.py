import sys

input = lambda: sys.stdin.readline().rstrip()

def main():
    N,T = map(int, input().split())
    carrots = [list(map(int, input().split())) for i in range(N)]

    carrots.sort(key=lambda x:(x[1], x[0]))
    ans = 0

    for i in range(N-1, -1, -1):
        ans += carrots[-1][0] + carrots[-1][1]*(T-N+i)
        carrots.pop()

    print(ans)

if __name__=="__main__":
    main()