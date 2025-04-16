def solution(sequence):
    N = len(sequence)
    plus = [sequence[i] for i in range(N)]
    minus = [sequence[i] for i in range(N)]
    
    p = 1
    m = -1
    for i in range(N):
        plus[i] *= p
        minus[i] *= m
        p *= -1
        m *= -1
    answer = BS(0, N-1, plus)
    answer = max(answer, BS(0, N-1, minus))
    return answer
def BS(left, right, sequence):
    if left == right: return sequence[left]
    mid = (left + right) // 2
    l = BS(left, mid, sequence)
    r = BS(mid+1, right, sequence)
    
    l_part = -int(1e9)
    temp = 0
    for i in range(mid, left-1, -1):
        temp += sequence[i]
        l_part = max(l_part, temp)
    r_part = -int(1e9)
    temp = 0
    for i in range(mid+1, right+1):
        temp += sequence[i]
        r_part = max(r_part, temp)
    return max(l, r, l_part + r_part)