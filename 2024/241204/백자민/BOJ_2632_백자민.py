import sys
from collections import defaultdict

input = lambda: sys.stdin.readline().rstrip()


def get_pizza(pizza, len):
    res = defaultdict(int)

    for i in range(len):
        tmp = pizza[i:]+pizza[:i]
        prev = 0
        for n in tmp:
            prev += n
            res[prev] += 1
    
    res[sum(pizza)] = 1
    return res

def main():
    size = int(input())
    M,N = map(int, input().split())
    a_pizza = [int(input()) for _ in range(M)]
    b_pizza = [int(input()) for _ in range(N)]

    caseA = get_pizza(a_pizza, M)
    caseB = get_pizza(b_pizza, N)

    result = caseA.get(size, 0) + caseB.get(size, 0)

    for n in caseB:
        if size-n in caseA:
            result += caseB[n] * caseA[size-n]
    
    print(result)

if __name__=="__main__":
    main()
