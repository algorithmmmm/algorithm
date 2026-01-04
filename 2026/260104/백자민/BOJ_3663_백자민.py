import sys

input = lambda:sys.stdin.readline().rstrip()

def play():
    name = input()
    N = len(name)

    #상하이동
    vertical = sum(min(ord(c)-ord('A'), ord('Z')-ord(c)+1) for c in name)

    #좌우이동
    horiz = N-1
    for i in range(N):
        tmp = i+1
        while tmp<N and name[tmp]=='A':
            tmp+=1
        
        horiz = min(horiz, 2*i+N-tmp) #오->왼
        horiz = min(horiz, i+2*(N-tmp)) #왼->오
    
    return vertical+horiz


T = int(input())
for _ in range(T):
    print(play())


"""

B B B A A A B B
0 1 2 3 4 5 6 7



"""
