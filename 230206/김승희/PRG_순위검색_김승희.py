import re
def solution(info, query):
    answer = []
    for i in range(len(query)):
        cond = query[i].split(" ")
        cnt = 0
        for j in range(len(info)):
            chk = True;
            for k in range(len(cond)-1):
                if cond[k] == "-" or cond[k] == "and":
                    continue
                if cond[k] not in info[j]:
                    chk = False
                    break
            temp = info[j].split(" ")
            score = int(temp[len(temp)-1])
            standard = int(cond[len(cond)-1])
            if(score < standard):
                chk = False;
            if(chk):
                cnt+=1
        answer.append(cnt)
    return answer