import sys

input = lambda:sys.stdin.readline().rstrip()

def main():
    N = int(input())
    p_sum = 0 #전체 사람수 저장할 변수
    village = []
        
    for _ in range(N):
        x,a = map(int, input().split())
        p_sum += a
        village.append((x,a))
    #end input
    
    village.sort(key=lambda x:x[0]) #마을 위치 기준 오름차순 정렬
    
    cnt=0
    for x,a in village:
        cnt+=a
        if cnt>=(p_sum/2):
            print(x)
            break
        
    
if __name__=="__main__":
    main()