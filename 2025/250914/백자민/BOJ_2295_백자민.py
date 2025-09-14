import sys

input = lambda: sys.stdin.readline().rstrip()

def main():
    N = int(input())
    U = [int(input()) for _ in range(N)]
    U.sort()

    usum = set()
    for a in U:
        for b in U:
            usum.add(a+b)
    
    ans = 0
    for i in range(N-1, -1, -1):
        for j in range(i+1):
            if U[i]-U[j] in usum:
                ans = U[i]
                print(ans)
                return



if __name__=="__main__":
    main()