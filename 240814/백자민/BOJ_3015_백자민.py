import sys
from collections import deque

input = lambda: sys.stdin.readline().rstrip()

N = int(input())

stack = deque() #스택으로 사용할 deque 
ans = 0 #서로 볼 수 있는 쌍의 수를 저장할 변수

for _ in range(N):
    tmp = int(input())

    #현재 줄 서 있는 사람 중 나보다 키가 큰 사람이 나타날 때까지 반복
    while len(stack) and stack[-1][0]<tmp:
        ans += stack.pop()[1] #스택에 있는 사람 수만큼 카운팅
    
    #스택이 비어있을 경우에는 현재 사람을 스택에 넣기
    if not len(stack):
        stack.append([tmp,1])
        continue

    #스택 마지막 사람이 현재 사람과 키가 동일한 경우
    if stack[-1][0]==tmp:
        #현재 사람과 마지막 사람들은 모두 서로를 볼 수 있음
        ans += stack[-1][1]
        stack[-1][1] += 1 #tmp키의 사람 수 증가

        if len(stack)>1: #스택에 들어있는 사람들은 모두 tmp 사람을 볼 수 있음
            ans += 1
    else:
        stack.append([tmp, 1])
        ans +=1

print(ans)
