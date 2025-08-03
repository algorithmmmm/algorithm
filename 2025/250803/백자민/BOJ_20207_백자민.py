import sys

input = lambda: sys.stdin.readline().rstrip()

def main():
    N = int(input())
    calendar = [0]*367

    for _ in range(N): #입력받으면서 시작일에는 +1, 끝나는일 다음날엔 -1
        start, end = map(int, input().split())
        calendar[start]+=1
        calendar[end+1]-=1
    #end input

    #max_heigth: 세로길이(일정 겹친 수) / length = 가로길이(연속일정)
    max_height, length, ans = 0,0,0
    for idx in range(1, 367):
        calendar[idx] += calendar[idx-1]

        if calendar[idx]==0: #연속일정이 끊김
            ans += length*max_height #면적 계산
            #초기화
            max_height = 0
            length = 0
        else:
            max_height = max(calendar[idx], max_height)
            length+=1
    
    print(ans)

if __name__=="__main__":
    main()