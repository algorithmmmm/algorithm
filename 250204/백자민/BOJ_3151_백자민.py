import sys
from bisect import bisect_left

input = lambda: sys.stdin.readline().rstrip()

def main():
    N = int(input())
    abilities = list(map(int, input().split()))
    abilities.sort() #오름차순 정렬

    ans = 0
    for i in range(len(abilities)-2):
        l,r = i+1, N-1
        while l<r:
            tmp = abilities[i]+abilities[l]+abilities[r]

            if tmp>0:
                r -=1
            else:
                if tmp==0: 
                    #팀 결성 완료되면 같은 값일 경우를 처리해줘야 함
                    if abilities[l] == abilities[r]:
                        ans += (r-l)
                    else:
                        idx = bisect_left(abilities, abilities[r])
                        ans += (r-idx+1)
                l += 1
    
    print(ans)



if __name__=="__main__":
    main()