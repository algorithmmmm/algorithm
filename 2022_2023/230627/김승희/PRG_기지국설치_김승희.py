def solution(n, stations, w):
    answer = 0
    W = 2 * w + 1
    start = 1
    for station in stations:
        left = station - w - 1
        right = station + w + 1
        if left < start:
            start = right
        else:
            temp = left - start + 1
            answer += temp // W
            if temp % W != 0:
                answer += 1
            start = right
    if start <= n:
        temp = n - start + 1
        answer += temp // W
        if temp % W != 0:
            answer += 1
    return answer