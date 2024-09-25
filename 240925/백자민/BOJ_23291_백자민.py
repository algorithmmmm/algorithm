import sys
from collections import deque

input = lambda: sys.stdin.readline().rstrip()

n,k = map(int, input().split())
dir = [(-1,0),(1,0),(0,-1),(0,1)]
board = [deque(list(map(int, input().split())))]

#배열을 180도 회전하는 함수
def rotate_180(arr):
    tmp = []
    for i in reversed(range(len(arr))):
        arr[i].reverse()
        tmp.append(arr[i])
    
    return tmp

#절반 잘라서 180도 회전하는 함수 
def rotate_half():
    global board

    #왼쪽에서 절반 자르고
    tmp = deque()
    for i in range(n//2):
        tmp.append(board[0].popleft())
    #회전
    rotated = rotate_180([tmp])
    board += rotated

    #한 번더 왼쪽에서 절반 잘라서
    left = []
    for i in range(2):
        tmp = deque()
        for j in range(n//4):
            tmp.append(board[i].popleft())
        left.append(tmp)
    #회전
    rotated = rotate_180(left)
    board+=rotated

    return

#다차원 어항 배열을 일렬로 만드는 함수 
def make_board_to_line():
    global board

    tmp = deque()

    for i in range(len(board[-1])):
        for j in range(len(board)):
            tmp.append(board[j][i])
    
    for i in range(len(board[-1]), len(board[0])):
        tmp.append(board[0][i])
    
    board = [tmp] #한 줄이지만 이후 연산을 위해 2차원 배열 형태로 저장
    return


#물고기 수 조절하는 함수(동시 수행을 위해 임시배열 활용)
def adjust_fish():
    global board

    tmp = [[0]*len(board[i]) for i in range(len(board))]

    #인접한 칸을 모두 탐색하며 조정할 물고기 수를 임시 배열에 저장
    for i in range(len(board)):
        for j in range(len(board[i])):
            for di,dj in dir:
                ni,nj = i+di,j+dj

                if 0<=ni<len(board) and 0<=nj<len(board[ni]):
                    #현재 칸이 인접 칸보다 클 경우
                    if board[i][j] > board[ni][nj]:
                        diff = (board[i][j]-board[ni][nj])//5
                        if diff>=1:
                            tmp[i][j] -= diff
                    else:
                        diff = (board[ni][nj]-board[i][j])//5
                        if diff>=1:
                            tmp[i][j] += diff
    
    #동시에 실제 어항에 반영
    for i in range(len(board)):
        for j in range(len(board[i])):
            board[i][j] += tmp[i][j]


#배열을 90도 회전하는 함수
def rotate_90(arr):
    result = [[0]*len(arr) for _ in range(len(arr[0]))]
    for i in range(len(arr[0])):
        for j in range(len(arr)):
            result[i][j] = arr[j][len(arr[0])-(i+1)]
    
    return result

#가장 왼쪽 어항을 90도 회전시켜 쌓는 함수 
def rotate_and_stack():
    global board

    #바닥에 있는 블록보다 회전시킬 블록 길이가 작아지기 전까지 반복
    while len(board)<= len(board[0]) - len(board[-1]):

        blocks = [] #공중부양할 블록을 빼서 저장할 리스트
        i = len(board)
        j = len(board[-1])

        for tmp_i in range(i):
            tmp_q = deque() #임시큐
            for _ in range(j):
                tmp_q.append(board[tmp_i].popleft())
            blocks.append(tmp_q)
        
        board = [board[0]] #가장 아랫줄블록
        rotated_blocks = rotate_90(blocks)#빼둔 블록 90도 회전

        #두 개 합치기
        for i in rotated_blocks:
            board.append(deque(i))
    
    return


#어항 최댓값-최솟값 리턴하는 함수
def get_diff():
    tmp = board[0]
    return max(tmp) - min(tmp)

def main():

    global board

    ans = 0 #어항정리 카운트
    
    #어항 물고기 차이가 k이하가 될 때까지 반복
    while get_diff() > k:

        #1.물고기 수가 가장 적은 어항에 물고기 넣기
        min_val = min(board[0])
        for i in range(len(board[0])):
            if board[0][i] == min_val:
                board[0][i]+=1
        
        #2.왼쪽에 있는 어항 올려놓기
        tmp = board[0].popleft()
        board.append(deque([tmp]))

        #3.90도 회전시켜서 쌓기
        rotate_and_stack()

        #4.물고기 수 조절(동시 수행)
        adjust_fish()

        #5.어항 일렬로 만들기
        make_board_to_line()

        #6.절반 잘라서 180도 회전 후 쌓기
        rotate_half()

        #7.물고기 수 조절(동시 수행)
        adjust_fish()

        #8.다시 일렬로 만들기
        make_board_to_line()

        ans+=1
    
    print(ans)





if __name__=="__main__":
    main()