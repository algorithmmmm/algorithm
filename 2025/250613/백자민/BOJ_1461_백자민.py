import sys

input = lambda: sys.stdin.readline().rstrip()

def main():
    N,M = map(int, input().split())
    books  = list(map(int, input().split()))

    pos,neg = [],[]
    last_book = 0
    for book in books:
        last_book = max(abs(book), last_book)
        if book>0:
            pos.append(book)
        else:
            neg.append(abs(book))
    
    pos.sort(reverse=1)
    neg.sort(reverse=1)

    ans=0
    for i in range(0, len(pos), M):
        ans += pos[i]*2
    for i in range(0, len(neg), M):
        ans += neg[i]*2
    
    print(ans-last_book)
    


if __name__=="__main__":
    main()