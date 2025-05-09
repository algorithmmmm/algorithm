import sys, heapq

input = lambda: sys.stdin.readline().rstrip()

def main():
    N = int(input())

    people = [tuple(map(int, input().split())) for _ in range(N)]
    people.sort() #시작시간 기준
    
    used = [] #(종료시간, 자리번호)
    available = [] #사용가능한 자리번호
    count = [] #각 자리를 사용한 횟수

    for p,q in people:

        #일찍 끝난 자리 비워주기
        while used and used[0][0]<=p:
            _, seat = heapq.heappop(used)
            heapq.heappush(available, seat)
        
        if available:
            seat = heapq.heappop(available)
        else:
            seat = len(count) #새로운 자리번호
            count.append(0)
        
        count[seat]+=1
        heapq.heappush(used, (q, seat))
    
    print(len(count))
    # print(*[x for x in count if x!=0])
    for x in count:
        if x != 0:
            print(x, end=" ")

    

if __name__=="__main__":
    main()
