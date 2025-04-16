import sys

input = lambda: sys.stdin.readline().rstrip()

def main():
    L,N,K = map(int, input().split())
    arr = list(map(int, input().split()))

    if N>=K: #가로등이 K개 이상 설치되면 작은값은 모두 0
        print('0\n'*K, end='')
        return
    
    #가로등 개수만큼 0 출력
    K -= N
    print('0\n'*N, end='')

    dist = 1 #거리
    while True:
        
        for tmp in range(N):
            if (tmp==0 and arr[tmp]>=dist) or (tmp and arr[tmp]>arr[tmp-1]+2*dist-1):
                print(dist)
                K-=1
            
            if K<=0:
                exit()
        
            if (tmp==N-1 and arr[tmp]<=L-dist) or (tmp!=N-1 and arr[tmp]<arr[tmp+1]-2*dist):
                print(dist)
                K-=1
        
            if K<=0:
                exit()
        dist += 1


if __name__=="__main__":
    main()