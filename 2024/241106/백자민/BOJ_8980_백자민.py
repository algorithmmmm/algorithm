import sys

input = lambda:sys.stdin.readline().rstrip()

def main():
    N,C = map(int, input().split())
    M = int(input())
    
    packages = []
    for _ in range(M):
        packages.append(tuple(map(int, input().split())))
    packages.sort(key=lambda x: x[1]) #받는 마을 번호 기준 오름차순 정렬(가까운 도착마을에 보낼 택배부터 트럭에 싣기)

    villages = [0]*(N+1) #각 마을 구간 별 트럭 용량
    ans = 0
    for fm,to,w in packages:
        tmp = w
        for i in range(fm, to):
            if villages[i]+tmp >= C: #용량을 초과하면 상자 일부만 실을 수 있음
                tmp = C-villages[i]
        
        for i in range(fm,to):
            villages[i] += tmp
        
        ans+=tmp
    
    print(ans)


if __name__=="__main__":
    main()