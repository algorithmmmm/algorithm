def solution(stones, k):
    answer = 0
    start = 0
    end = 200000000
    while start <= end:
        mid = (start + end) // 2
        if check(stones, k, mid):
            answer = max(answer, mid+1)
            start = mid+1
        else:
            end = mid-1
    return answer

def check(stones, k , num):
    acc = 0
    for n in stones:
        if (n-num) <= 0:
            acc+=1
            if acc >= k:
                return False
        else:
            acc = 0
    return True;
            