def solution(n):
    answer = 0
    while n > 1:
        remain = n % 2
        n = n // 2
        if remain == 1:
            answer+=1
    
    return answer+1;