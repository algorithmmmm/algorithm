import sys

input = lambda: sys.stdin.readline().rstrip()

N = int(input())
ingd = []
mp, mf, ms, mv = map(int, input().split())
ans = 1e9
idx_list = []

def select(idx, prot, fat, cab, vit, price, tmp_list):
    global ans, idx_list

    if idx==N:
        if len(tmp_list)==0:
            return
        if mp<=prot and mf<=fat and ms<=cab and mv<=vit: #조건 만족
            #최소비용 갱신
            if price < ans:
                ans = price
                idx_list = tmp_list
            elif price == ans:
                if idx_list > tmp_list:
                    idx_list = tmp_list
        return
    
    #선택할 경우와 선택하지 않을 경우 모두 확인
    select(idx+1, prot+ingd[idx][0], fat+ingd[idx][1], cab+ingd[idx][2],
        vit+ingd[idx][3], price+ingd[idx][4], tmp_list+[idx])
    select(idx+1, prot, fat, cab, vit, price, tmp_list)

def main():
    for i in range(N):
        prot, fat, cab, vit, price = map(int, input().split())
        ingd.append([prot,fat,cab,vit,price])
    
    select(0,0,0,0,0,0,[])

    if len(idx_list) == 0:
        print(-1)
    else: 
        print(ans)
        for i in idx_list:
            print(i+1, end=" ")

if __name__=="__main__":
    main()