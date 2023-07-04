def solution(s):
    answer = 0
    s = list(s)
    
    for i in range(len(s)):
        if i == 0:
            answer += check(s)
        else:
            answer += check(s[i:len(s)]+s[:i])
    return answer
def check(s):
    ST = []
    for ch in s:
        if ch == '[' or ch == '{' or ch == '(':
            ST.append(ch)
        else:
            if len(ST) == 0:
                return 0
            temp = ST.pop()
            if (ch == ']' and temp == '['):
                continue 
            elif (ch == '}' and temp == '{'):
                continue
            elif (ch == ')' and temp == '('):
                continue
            else:
                return 0
    if len(ST) != 0:
        return 0
    return 1