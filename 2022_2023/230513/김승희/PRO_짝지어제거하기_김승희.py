def solution(s):
    answer = -1
    idx = 0
    stack = []
    chk = False
    while idx < len(s):
        if len(stack) == 0:
            stack.append(s[idx])
        else:
            if stack[-1] == s[idx]:
                stack.pop()
            else:
                stack.append(s[idx])
        idx += 1
    
    if len(stack) == 0:
        return 1
    else:
        return 0
