import sys

input = sys.stdin.readline

N,K = map(int, input().split())

dp = [[[[0 for _ in range(436)] for _ in range(31)] for _ in range(31)] for _ in range(31)]

ans = [0]*31

def isPossible(idx, a, b, cnt):
    if idx==N:#N자리 문자열을 모두 만들었을 때
        if cnt==K:#조건을 충족하면 True, 충족하지 못하면 False
            return True
        return False
    
    #이미 했던 조합에 다시 접근하는 경우는 실패
    if dp[idx][a][b][cnt]:
        return False

    #중복체크
    dp[idx][a][b][cnt] = True
    
    #A사용
    ans[idx] = 'A'
    if isPossible(idx+1, a+1, b, cnt):
        return True
    
    #B사용
    ans[idx] = 'B'
    if isPossible(idx+1, a, b+1, cnt+a):
        return True
    
    #C사용
    ans[idx] = 'C'
    if isPossible(idx+1, a, b, cnt+a+b):
        return True
    
    return False


if isPossible(0,0,0,0): #한 가지라도 가능한 경우가 있다면 만든 문자열 출력
    print(''.join(ans[0:N]))
else:
    print(-1)
