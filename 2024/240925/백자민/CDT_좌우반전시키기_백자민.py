import sys

input = lambda: sys.stdin.readline().rstrip()

n = int(input())
array = list(map(int, input().split()))

def push(state, idx): #idx위치와 양 옆의 비트를 반전시키는 함수
    
    tmp = (1<<idx) #idx 위치 반전

    #좌
    if idx>0:
        tmp |= (1<<(idx-1))
    #우
    if idx<n-1:
        tmp |= (1<<(idx+1))
    
    #해당 위치 비트 반전
    return state ^ tmp


def main():
    #입력받은 배열을 비트로 변환
    state = 0
    for i in range(n):
        state |= (array[i]<<i)
    
    #클릭 횟수
    ans = 0

    #두 번째 숫자부터 누르기
    for i in range(n-1):
        #i번째 비트가 0인 경우
        if not (state & (1<<i)):
            state = push(state, i+1) #옆에 있는 비트 누르기
            ans+=1
    
    #목표 상태=모두 1로 이루어진 상태
    target = (1<<n)-1

    if state != target:
        print(-1)
    else:
        print(ans)
    

if __name__=="__main__":
    main()
