import sys
from collections import defaultdict

input = lambda:sys.stdin.readline().rstrip()

def main():
    M,N = map(int, input().split())
    dictMap = defaultdict(int)

    for i in range(M):
        planets = list(map(int, input().split()))
        sortedP = sorted(list(set(planets))) #중복제거+정렬
        rank = {sortedP[i] : i for i in range(len(sortedP))} #순위 지정

        pattern = tuple([rank[i] for i in planets])
        dictMap[pattern] += 1
    
    ans = 0
    for i in dictMap.values():
        #nC2
        ans += (i*(i-1))//2
    
    print(ans)

if __name__=="__main__":
    main()