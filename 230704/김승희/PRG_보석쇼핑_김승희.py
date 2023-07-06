def solution(gems):
    answer = [0, len(gems)]
    N = len(set(gems))
    gem = dict()
    gem.setdefault(gems[0], 1)
    head = 0
    tail = 0
    while head <= tail:
        if N == len(gem):   # gem에 모든 종류의 보석이 있다.
            # 가장 짧은 구간이면 answer update
            if answer[1]-answer[0] > tail - head:
                answer = [head, tail]
            # head를 한 칸 뒤로
            temp = gem[gems[head]]
            if temp == 1:
                del gem[gems[head]]
            else:
                gem[gems[head]] = temp-1
            head += 1
        else:   # gem에 모든 종류의 보석이 없다.
            # tail을 한 칸 뒤로
            tail += 1
            if tail == len(gems): break
            temp = gem.setdefault(gems[tail], 0)
            gem[gems[tail]] = temp + 1
    
    return [answer[0]+1, answer[1]+1]
    
# 입력값 〉	["x", "x", "y", "x", "y", "z", "y", "k", "x"]
# 기댓값 〉	[6, 9]