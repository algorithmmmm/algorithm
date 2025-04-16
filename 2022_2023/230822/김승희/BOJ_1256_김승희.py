def answer(sub, origin, N):
    ans = []
    for i in range(N-origin[0]):
        ans.append('a')
    return ''.join(ans) + sub
def process(origin, K, N):
    ans = []
    index = [origin[0], origin[1]]
    while True:
        # a : dp[index[0]-1][index[1]] 경우의 수, 
        # 즉, a를 index[0]-1개, z를 index[1]개 썼을 때 앞에 a를 붙이는 경우의 수 
        a = dp[index[0]-1][index[1]]    
        # z : dp[index[0]][index[1]-1] 경우의 수, 
        # 즉, a를 index[0]개, z를 index[1]-1개 썼을 때 앞에 z를 붙이는 경우의 수 
        z = dp[index[0]][index[1]-1]
        if K > a:   # K가 a보다 크면 z 경우에 해당
            K -= a
            ans.append('z')
            if index[1]-1 == 0:
                for i in range(index[0]):
                    ans.append('a')
                break
            index = [index[0], index[1]-1]
        else:
            ans.append('a')
            if index[0]-1 == 0:
                for i in range(index[1]):
                    ans.append('z')
                break
            index = [index[0]-1, index[1]]
    return answer(''.join(ans), origin, N)

# N : a의 개수
# M : z의 개수
# K : k번째 문자열
N, M, K = map(int, input().split(" "))
# dp[i][j] = a를 i개, z를 j개 썼을 때 경우의 수
dp = [[0 for _ in range(M+1)] for _ in range(N+1)]
index = [N, M]
for i in range(1, N+1):
    # a만 있는 경우는 1개
    dp[i][0] = 1
for i in range(1, M+1):
    # z만 있는 경우는 1개
    dp[0][i] = 1
for i in range(1, N+1):
    for j in range(1, M+1):
        # dp[i][j] = a를 i-1개 z를 j개 쓴 문자열 앞에 a 붙이기 + a를 i개 z를 j-1개 쓴 문자열 앞에 z 붙이기
        dp[i][j] = dp[i-1][j] + dp[i][j-1]
    if dp[i][M] >= K:
        index[0] = i
        break
print(-1 if dp[index[0]][index[1]] < K  else process(index, K, N))
