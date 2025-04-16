import sys

input = lambda: sys.stdin.readline().rstrip()

ans = 0

#모음이면 true/자음이면 false
def is_vowel(x):
    if x in ['A', 'E', 'I', 'O', 'U']:
        return True
    return False

def dfs(word, idx, vowel, cons): #word:초기단어 / idx:현재위치 / vowel:사용모음개수 / cons:사용자음개수
    global ans

    if idx==len(word):
        if 'L' in word: #L 검사
            ans += (5**vowel)*(20**cons)
        return
    
    if word[idx]=='_': #현재 위치가 빈칸일 때
        #L먼저 넣기
        if idx<2 or (is_vowel(word[idx-2]) or is_vowel(word[idx-1])): #연속 자음 방지
            nxt = word[:idx]+'L'+word[idx+1:]
            dfs(nxt, idx+1, vowel, cons)
        #자음 넣기
        if idx<2 or (is_vowel(word[idx-2]) or is_vowel(word[idx-1])):
            nxt = word[:idx]+'B'+word[idx+1:]
            dfs(nxt, idx+1, vowel, cons+1)
        #모음 넣기
        if idx<2 or not (is_vowel(word[idx-2]) and is_vowel(word[idx-1])):
            nxt = word[:idx]+'A'+word[idx+1:]
            dfs(nxt, idx+1, vowel+1, cons)
    else:
        #빈칸이 아니라면 지금까지 만든 것 중 모음/자음 반복 여부 검사
        if idx>=2:
            if is_vowel(word[idx-2]) and is_vowel(word[idx-1]) and is_vowel(word[idx]):
                return
            if not (is_vowel(word[idx-2]) or is_vowel(word[idx-1]) or is_vowel(word[idx])):
                return
        dfs(word, idx+1, vowel, cons)

def main():
    word = input()
    dfs(word, 0,0,0)
    print(ans)


if __name__=="__main__":
    main()