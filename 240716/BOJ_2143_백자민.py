import bisect

T = int(input())

N = int(input())
A = list(map(int, input().split()))

M = int(input())
B = list(map(int, input().split()))
#end input

#누적합 저장할 배열
prefixA = []
prefixB = []

#A 누적합 저장
for i in range(N):
    #현재 인덱스만 선택한 경우 저장
    tmp = A[i]
    prefixA.append(tmp)

    #i+1~N까지의 인덱스 별 누적합 저장
    for j in range(i+1, N):
        tmp+=A[j]
        prefixA.append(tmp)

#B 누적합 저장(동일한 방식)
for i in range(M):
    tmp = B[i]
    prefixB.append(tmp)

    for j in range(i+1, M):
        tmp+=B[j]
        prefixB.append(tmp)

#정렬
prefixA.sort()
prefixB.sort()

ans = 0
for i in range(len(prefixA)):
    l = bisect.bisect_left(prefixB, T-prefixA[i])
    r = bisect.bisect_right(prefixB, T-prefixA[i])
    ans += (r-l)

print(ans)