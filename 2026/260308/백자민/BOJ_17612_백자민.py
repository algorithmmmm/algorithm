import sys, heapq

input = lambda:sys.stdin.readline().rstrip()

def main():
    N,K = map(int, input().split())
    ans = 0
    cashier = [] #현재 계산대 [계산시간, 계산대번호, 고객번호]
    finished = [] #계산 끝난 사람 내보내기 [계산시간, -계산대번호, 고객변호]

    for i in range(N):
        customer, stock = map(int, input().split())

        if len(cashier)<K: #계산대 자리 남은 경우
            heapq.heappush(cashier, [stock, (i+1), customer])
        else:
            time, tmpch, outcustm = heapq.heappop(cashier) #가장 빨리 끝나는 계산대 꺼내기
            heapq.heappush(cashier, [stock+time, tmpch, customer]) #기존종료시간+새 고객시간
            heapq.heappush(finished, [time, -tmpch, outcustm])
    
    while cashier: #남은 계산대 빼기
        time, tmpch, outcustm = heapq.heappop(cashier)
        heapq.heappush(finished, [time, -tmpch, outcustm])
    
    for j in range(N):
        tmp = heapq.heappop(finished)
        ans += tmp[2]*(j+1)
    
    print(ans)


if __name__=="__main__":
    main()