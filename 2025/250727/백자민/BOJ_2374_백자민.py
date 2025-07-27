import sys

input = lambda: sys.stdin.readline().rstrip()

def main():
    N = int(input())
    cnt=0
    stack = [int(input())]
    max_num = stack[-1]

    #현재 숫자 tmp를 기준으로 더 낮은 숫자들로 이루어진 그룹에 Add 연산을 몇 번이나 해야 하는지 카운팅하는 것
    for _ in range(N-1):
        tmp = int(input())
        if stack[-1]<tmp: #더 큰 수가 들어오면 Add 연산을 해야 함.
            cnt += tmp - stack[-1] #Add 연산 횟수
            max_num = max(max_num, tmp) #가장 큰 수(=목표 숫자) 갱신
        
        stack.pop()
        stack.append(tmp)
    
    cnt += max_num*len(stack) - sum(stack) #스택에 남아있는 숫자의 연산 횟수 추가
    print(cnt)

if __name__=="__main__":
    main()