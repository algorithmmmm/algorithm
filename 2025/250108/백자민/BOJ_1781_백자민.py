import sys
import heapq

input = lambda:sys.stdin.readline().rstrip()

def main():
    n = int(input())
    data, hq = [], []
    
    for _ in range(n):
        data.append(tuple(map(int, input().split()))) #디데이와 컵라면 정보 저장

    #디데이는 빨라야 하므로 오름차순, 디데이가 같다면 컵라면 수가 커야 하니 내림차순 정렬
    data.sort(key=lambda x: (x[0], -x[1]))

    time, ans = 1,0
    for d,c in data:
        #문제를 풀 수 있는 경우(디데이가 남음)
        if time <=d:
            ans += c #컵라면 개수 더함
            heapq.heappush(hq, (c,time)) #현재 상태 업데이트
            time += 1 #
        #문제를 못푸는 경우(디데이를 이미 넘김)
        else:
            if hq:
                min_tmp = heapq.heappop(hq) #걸린 컵라면이 가장 적은 문제를 건너뛰기
                if min_tmp[0] < c: #현재 문제보다 컵라면을 적게 받는 경우 - 현재 문제로 교체
                    ans += (c-min_tmp[0]) #차액만 추가로 더해줌
                    heapq.heappush(hq, (c, min_tmp[1]))
                else:
                    heapq.heappush(hq, min_tmp)
    
    print (ans)

if __name__=="__main__":
    main()

"""

4
2 50
1 10
2 20
1 40

정렬 후: [(1,40), (1,10), (2,50), (2,20)]


"""
