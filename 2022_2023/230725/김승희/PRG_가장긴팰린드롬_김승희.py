def solution(s):
    # i는 길이. len(s)부터 1까지
    for i in range(len(s), 0, -1):
        for j in range(len(s)-i+1):
            if isPalindrome(s[j:j+i]):
                return i
    return 1

def isPalindrome(s):
    for i in range(len(s) // 2):
        if s[i] != s[len(s)-1-i]:
            return False
    return True