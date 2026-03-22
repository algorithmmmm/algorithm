import sys

input = lambda:sys.stdin.readline().rstrip()

def main():
    N = int(input())
    hills = list(map(int, input().split()))
    ans = 0

    roomSum = sum(hills)
    if roomSum == 0:
        ans = N//2
    
    else:
        idx = 0
        for idx, hill in enumerate(hills):
            if hill:
                break

        hills = hills[idx+1:]+hills[:idx+1]
        visit = [0]*N

        for i in range(N):
            if hills[i] or visit[i]:
                continue

            for j in range(i, N):
                if hills[j]:
                    break
                visit[j] = 1
            roomSum += (j-i+1)//2
        
        ans = roomSum
    
    print(ans)
    

if __name__=="__main__":
    main()