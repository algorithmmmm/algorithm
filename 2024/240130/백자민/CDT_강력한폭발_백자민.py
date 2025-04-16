def bomb():
    global ans
    visit = [[False for _ in range(n)] for _ in range(n)]
    cnt=m

    for i in range(m):
        ti,tj = bomblist[i]
        bnum = check[i]
        
        for d in range(4):
            ni = ti+pos[bnum][d][0]
            nj = tj+pos[bnum][d][1]
            
            if ni<0 or ni>=n or nj<0 or nj>=n:
                continue
            if visit[ni][nj] or board[ni][nj]>0:
                continue
            
            visit[ni][nj] = True
            cnt+=1

    if ans<cnt:
        ans=cnt
        
    return
    

def comb(cnt, idx):
    if cnt==m:
        bomb()
        return
    
    for i in range(idx,3):
        check[cnt] = i
        comb(cnt+1, idx)


n = int(input())
board = [list(map(int, input().split())) for _ in range(n)]
pos = [[(-1,0), (-2,0), (1,0), (2,0)],[(-1,0), (0,-1), (1,0), (0,1)],[(-1,-1), (-1,1), (1,-1), (1,1)]]
ans = 0

bomblist = list()
for i in range(n):
    for j in range(n):
        if board[i][j]==1:
            bomblist.append((i,j))

m = len(bomblist)
check = [0 for _ in range(m)]


comb(0,0)

print(ans)