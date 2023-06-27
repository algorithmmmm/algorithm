import heapq
def solution(book_time):
    PQ = []
    for time in book_time:
        temp = time[0].split(":")
        start_H = int(temp[0])
        start_M = int(temp[1])
        temp = time[1].split(":")
        end_H = int(temp[0])
        end_M = int(temp[1])
        heapq.heappush(PQ, (start_H, start_M, end_H, end_M))
    # preparing
    answer = 0
    # PQ가 빌 때까지 process를 반복
    while PQ:
        PQ = process(PQ)
        answer += 1
    return answer
# 입실 시간을 기준으로 정렬
# 가장 이른 입실 시간을 기준으로 시작해서
# 이어지며 가능한 시간을 모두 빼고 가능하지 않은 시간은 new_PQ에 넣기
def process(PQ):
    new_PQ = []
    prev = heapq.heappop(PQ)
    while PQ:
        time = heapq.heappop(PQ)
        prev_H = prev[2]
        prev_M = prev[3] + 10
        if prev_M // 60 == 1:
            prev_M = prev_M % 60
            prev_H += 1
        if prev_H > time[0]:
            heapq.heappush(new_PQ, time)
            continue
        elif prev_H == time[0]:
            if prev_M > time[1]:
                heapq.heappush(new_PQ, time)
                continue
        prev = time
    return new_PQ