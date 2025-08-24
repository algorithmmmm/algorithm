import sys

input = lambda: sys.stdin.readline().rstrip()

def main():
    T = int(input())
    for _ in range(T):
        N,M = map(int, input().split())

        #끝 구간 기준 정렬
        req = [list(map(int, input().split())) for _ in range(M)]
        req.sort(key=lambda x:x[1])

        books = [True]*(N+1)
        ans = 0

        for s,e in req:

            #처음 찾은 책 위치
            idx = 0
            for i in range(s, e+1):
                if books[i]:
                    idx = i
                    break
            
            #책 모두 나눠주기
            if idx:
                books[idx] = False
                ans+=1
        
        print(ans)

if __name__=="__main__":
    main()