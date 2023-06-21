import heapq
def solution(s):
    answer = []
    # prepare data
    s = s[1:len(s)-1]
    t_list = s.split("},{")
    t_list[0] = t_list[0][1:]
    t_list[len(t_list)-1] = t_list[len(t_list)-1][:len(t_list[len(t_list)-1])-1]
    
    # add PQ
    PQ = []
    for t in t_list:
        heapq.heappush(PQ, (len(t), t))
    
    t_set = set()
    while PQ:
        tp = heapq.heappop(PQ)
        tp_list = tp[1].split(",")
        for n in tp_list:
            prev = len(t_set)
            t_set.add(int(n))
            if prev < len(t_set):
                answer.append(int(n))    
    return answer