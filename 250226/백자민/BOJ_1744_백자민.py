import sys
from itertools import zip_longest

input = lambda: sys.stdin.readline().rstrip()

def calc(nums, is_positive):
    #양수는 내림차순, 음수는 오름차순 정렬
    nums.sort(reverse=is_positive)
    
    #배열에 있는 수 묶어 더하기
    res = 0
    for i in range(0, len(nums), 2):
        if i+1 >= len(nums):
            res += nums[i]
        else:
            res += nums[i] * nums[i+1]
    
    # for a, b in zip_longest(nums[::2], nums[1::2], fillvalue=1):
    #     res += a * b
    
    return res

def main():
    N = int(input())
    pos_nums = []
    neg_nums = []
    ans = 0

    for i in range(N):
        tmp = int(input())

        if tmp == 1:
            ans += 1
        elif tmp > 1:
            pos_nums.append(tmp)
        else:
            neg_nums.append(tmp)
    
    ans += calc(pos_nums, True)
    ans += calc(neg_nums, False)
    
    print(ans)

if __name__=="__main__":
    main()