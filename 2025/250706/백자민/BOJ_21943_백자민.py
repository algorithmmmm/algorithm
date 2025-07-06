import sys

input = lambda: sys.stdin.readline().rstrip()

N = int(input())
lst = sorted(map(int, input().split()))
p,q = map(int, input().split())
ans = 0

def comb(arr, idx):
    for i in range(len(arr)):
        if idx==1:
            yield [arr[i]]
        else:
            for next in comb(arr[i+1:],idx-1):
                yield next + [arr[i]]

def dfs(arr, count, tmp):
    #곱하기0이면 남은 숫자 모두 더하기
    if count==0:
        global ans
        ans = max(ans, tmp*sum(arr))
        return tmp*sum(arr)


    idx = range(len(arr))
    for i in range(1, len(arr)-count+1):
        #만든 조합에서 i개를 뽑아 이전 결과에 곱하기
        for j in comb(idx, i):
            cur = arr[:]
            multiply = 0

            for k in j:
                multiply += cur.pop(k)
            
            dfs(cur, count-1, tmp*multiply)
    
    return ans

def main():
    print(dfs(lst, q, 1))


if __name__=="__main__":
    main()