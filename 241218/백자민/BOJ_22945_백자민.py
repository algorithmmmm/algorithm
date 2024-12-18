import sys

input = lambda: sys.stdin.readline().rstrip()

def main():
    N = int(input())
    abilities = list(map(int, input().split()))

    l,r,max_ability = 0,N-1,0

    while l+1 < r : #겹치기 직전까지 포인터 이동
        max_ability = max(max_ability, (r-l-1)*min(abilities[l], abilities[r]))

        #팀원수를 줄이되 더 능력치가 적은 팀원 방출
        if abilities[l] < abilities[r]:
            l+=1
        else:
            r-=1
    
    print(max_ability)
    

if __name__=="__main__":
    main()