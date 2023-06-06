def solution(n, words):
    answer = None
    idx = 0
    acc = dict()
    prev = words[0]
    for i in range(len(words)):
        if i % n == 0:
            idx+=1
        # 중복된 단어인지 체크
        temp = acc.setdefault(words[i], 0)
        if temp == 0:
            acc[words[i]] = temp+1
        else:
            answer = [i%n+1, idx]
            break
        # 끝말잇기 규칙을 지켰는지 체크
        if i == 0: continue
        if prev[-1] != words[i][0]:
            answer = [i%n+1, idx]
            break
        prev = words[i]
    if answer == None:
        answer = [0,0]
    return answer