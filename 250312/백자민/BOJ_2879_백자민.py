import sys

input = lambda: sys.stdin.readline().rstrip()

def main():
    N = int(input())

    tabs = [*map(int, input().split())]
    targets = [*map(int, input().split())]

    #줄마다 필요한 변화량 계산
    for i in range(N):
        tabs[i] -= targets[i]
    
    cnt = 0 #총 편집 횟수(=정답)
    for i in range(N):
        if tabs[i]==0: #완료 상태
            continue
        
        #탭 증가/감소 선택
        if tabs[i] > 0:
            pos = 1
        else:
            pos = -1
        
        while tabs[i] != 0:
            cnt += 1
            for j in range(i, N):
                if tabs[j]*pos <= 0: #같은 그룹으로 묶을 수 없다면 변경X
                    break
                tabs[j] -= pos
    
    print(cnt)

if __name__=="__main__":
    main()