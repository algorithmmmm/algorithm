import heapq
def solution(n, t, m, timetable):
    tt = []
    for temp in timetable:
        temp = temp.split(":")
        heapq.heappush(tt, (int(temp[0]), int(temp[1])))
    
    H = 9       # H : 현재 셔틀 시간
    M = 0       # M : 현재 셔틀 분
    prev = None     # prev : 이전에 탄 사람이 몇 분에 탔는지
    cnt = 0     # cnt : 현재 셔틀 버스에 몇 명 탔는지
    num = 0     # num : 현재 셔틀이 몇 번째 셔틀인지
    while tt:
        temp = heapq.heappop(tt)
         # 현재 사람이 도착한 시간이 셔틀 시간보다 늦게 도착 -> 탈 수 없음
        if temp[0] > H or (temp[0] == H and temp[1] > M):  
            heapq.heappush(tt, temp)
            num += 1
            if num == n: break
            cnt = 0
            H, M = plus(H, M, t)
            continue
        # 탈 수 있음
        if prev != temp:
            prev = temp
        cnt += 1
        print(prev, temp, cnt)
        if cnt == m:    # 현재 셔틀은 자리가 없음
            num += 1
            if num == n: break
            cnt = 0
            H, M = plus(H, M, t)
    if num == n:    # 마지막 셔틀 버스이고 
        if cnt == m:    # 마지막 셔틀버스에 m명이 모두 탔다면
            prev_H, prev_M = minus(prev[0], prev[1], 1)     # prev보다 1분 일찍 와야 함
            return time2str(prev_H, prev_M)
        return time2str(H, M)   # 자리가 남아있으면 셔틀시간에 맞춰 오면 됨
    H, M = plus(H, M, t * (n-num-1))    # 마지막 셔틀이 아니면 마지막 셔틀 시간에 맞춰 오면 됨
    return time2str(H, M)

def time2str(H, M):
    return str(H).rjust(2, "0") + ":" + str(M).rjust(2, "0")

# H시 M분에 t분 더하기
def plus(H, M, t):
    M += t
    H += M // 60
    M %= 60
    return H, M
# H시 M분에 t분 빼기
def minus(H, M, t):
    M -= 1
    if M < 0:
        M = 60 + M
        H -= 1
    return H, M