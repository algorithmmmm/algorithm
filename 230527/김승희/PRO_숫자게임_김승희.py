def solution(A, B):
    answer = 0
    A.sort()
    B.sort()
    Astart = 0
    Aend = len(A)-1   
    Bstart = 0
    Bend = len(B)-1
    for i in range(len(A)):
        if A[Aend] >= B[Bend]:
            Aend-=1
            Bstart+=1
        else:
            answer+=1
            Aend-=1
            Bend-=1
        
    return answer