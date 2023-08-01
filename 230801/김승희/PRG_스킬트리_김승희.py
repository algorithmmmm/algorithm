def solution(skill, skill_trees):
    answer = 0
    alpha = [ -1 for _ in range(26)]
    for i in range(len(skill)):
        alpha[ord(skill[i]) - ord('A')] = i
    for tree in skill_trees:
        acc = []
        for ch in tree:
            if alpha[ord(ch) - ord('A')] != -1:
                acc.append(alpha[ord(ch) - ord('A')])
        if check(skill, tree, acc):
            answer+=1
    return answer
def check(skill, tree, acc):
    if len(acc) == 0: return True
    if acc[0] != 0: return False
    curr = 0    # curr : 현재 스킬 레벨
    for n in acc:
        if n == curr+1:
            curr = n
            continue
        if n > curr: return False
    return True