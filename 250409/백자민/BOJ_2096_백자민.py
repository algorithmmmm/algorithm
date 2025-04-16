import sys

input = lambda: sys.stdin.readline().rstrip()


def main():
    N = int(input())

    #dp배열 초기값은 처음 숫자 3개
    nums = list(map(int, input().split()))
    maxdp = nums
    mindp = nums

    for _ in range(N-1):
        nums = list(map(int, input().split()))
        maxdp = [nums[0]+max(maxdp[0], maxdp[1]),
                    nums[1]+max(maxdp),
                    nums[2]+max(maxdp[1], maxdp[2])]
        mindp = [nums[0]+min(mindp[0], mindp[1]),
                    nums[1]+min(mindp),
                    nums[2]+min(mindp[1], mindp[2])]
    
    print(max(maxdp), min(mindp))
    

if __name__=="__main__":
    main()