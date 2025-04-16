def solution(n, s):
    answer = []
    if n == 1:
        return [s]
    q = s // n
    r = s % n
    if q == 0:  # 몫이 0인 경우, 집합을 만들 수 없음
        return [-1]
    else:
        answer = [q for _ in range(n)]
        # n개의 q에서 나머지 갯수만큼 1씩 더한다.
        # 뒤에서부터 하기 위해 size 변수 사용
        size = len(answer)-1
        for i in range(size, size-r, -1):
            answer[i] += 1
    return answer