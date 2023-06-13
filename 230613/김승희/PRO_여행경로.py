import copy
import sys
sys.setrecursionlimit(100000)
def solution(tickets):
    airline = refining(tickets)
    visit = visit_init(airline)
    process("ICN",airline, visit, ["ICN"])
    return answer
def refining(tickets):
    airline = dict()
    for ticket in tickets:
        temp = airline.setdefault(ticket[0], [])
        temp.append(ticket[1])
        airline[ticket[0]] = temp
    for key in airline:
        temp = airline[key]
        airline[key] = sorted(temp)
    return airline
def visit_init(airline):
    visit = dict()
    for key in airline:
        visit[key] = [False for _ in range(len(airline[key]))]
    return visit
global answer
answer = None
def process(start, airline, visit, acc):
    global answer
    if check(visit):
        answer = copy.deepcopy(acc)
        return True
    chk = airline.setdefault(start, [])
    if len(chk) == 0: return False
    for i in range(len(airline[start])):
        if visit[start][i]: continue
        visit[start][i] = True
        temp = copy.deepcopy(acc)
        end = airline[start][i]
        temp.append(end)
        ans = process(end, airline, visit,temp)
        if ans:
            return True
        visit[start][i] = False
    return False

def check(visit):
    for key in visit:
        for chk in visit[key]:
            if not chk:
                return False
    return True