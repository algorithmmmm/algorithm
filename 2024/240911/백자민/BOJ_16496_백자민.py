import sys
from functools import cmp_to_key

input = lambda: sys.stdin.readline().rstrip()

def compare(a,b):
    sa,sb = str(a),str(b)
    if sa+sb > sb+sa:
        return -1
    
    if sa+sb < sb+sa:
        return 1
    
    return 0

def main():
    n = int(input())
    arr = list(map(int, input().split()))
    arr.sort(key=cmp_to_key(compare))

    ans=''
    if arr[0]<1:
        ans = '0'
    else:
        for i in range(n):
            ans+=str(arr[i])
        
    print(ans)


if __name__ == "__main__":
    main()