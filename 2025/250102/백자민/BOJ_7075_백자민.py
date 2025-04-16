import sys

input = lambda: sys.stdin.readline().rstrip()

def main():
    N = int(input())
    kids = list(map(int, input().split()))
    idxs = [-1] * (N+1) #각 숫자별 인덱스를 저장할 배열

    if N==1:
        #N이 1이면 이동할 필요가 없음
        print(0)
        return

    #초기 상태의 인덱스 저장
    for i,n in enumerate(kids):
        idxs[n] = i

    max_len = 0 #가장 긴 그룹
    len = 1 #올바른 줄 길이

    for n in range(1,N):
        if idxs[n] < idxs[n+1]: 
            #n번째 수 기준 n+1번째 숫자보다 앞에 있다면 올바르게 정렬된 상태
            len+=1 #정렬된 길이가 늘어남
        else:
            #이동이 필요함
            max_len = max(max_len, len) #지금까지 만든 가장 큰 그룹 길이 저장
            len = 1 #정렬된 길이 다시 카운트 시작
    
    print(N-max(max_len, len))
    return

if __name__ == "__main__":
    main()


"""
1 2 3 4 5 6 7

1 2 | 4 5 6 | 3 | 7
- 2 이상 차이나면 한 그룹으로 묶을 수 없음(중간에 다른 수가 추가되어야 하기 때문)
- 가장 큰 그룹을 뺀 나며지는 어차피 개별 이동해야 함 -> 가장 큰 그룹을 찾으면 그 외 나머지가 오름차순으로 이동하면 된다.

"""
