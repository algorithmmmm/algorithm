def solution(key, lock):
    N = len(lock)
    M = len(key)
    lock = toggle(lock, N)
    left, right, up, down = one_index(lock, N)
    lock = slicing(lock, left, right, up, down)
    for i in range(4):
        if check(lock, key, M):
            return True
        key = rotate(key, M)    
    return False
# 시계방향으로 90도 회전
def rotate(key, M):
    arr = [[ key[j][M - 1 - i] for j in range(M)] for i in range(M)]
    return arr
# lock의 홈을 돌기로, 돌기를 홈으로
def toggle(lock, N):
    lock = [[ 0 if 1 == lock[i][j] else 1 for j in range(N)] for i in range(N)]
    return lock
# lock에서 돌기의 가장 왼쪽, 가장 오른쪽, 가장 위, 가장 아래 index를 구하기
def one_index(lock, N):
    left = N-1
    right = 0
    up = N-1
    down = 0
    chk = True
    for i in range(N):
        for j in range(N):
            if lock[i][j] == 0:
                continue
            chk = False
            left = min(left, j)
            right = max(right, j)
            up = min(up, i)
            down = max(down, i)
    if chk:
        return 0, 0, 0, 0
    return left, right, up, down
# lock에서 1이 있는 부분만 남기기
def slicing(lock, left, right, up, down):
    lock = lock[up:down+1]
    lock = [lock[i][left:right+1] for i in range(len(lock))]
    return lock
# key에 lock에 해당하는 부분이 있는지 확인하기
def check(lock, key, M):
    I = len(lock)
    J = len(lock[0])
    for i in range(M):
        if i + I  > M: continue
        for j in range(M):
            if j + J > M: continue
            if isSame(lock, key, i, j):
                return True
    return False
# key에 (I, J)부터 lock에 해당하는지 확인하기
def isSame(lock, key, I, J):
    for i in range(len(lock)):
        for j in range(len(lock[0])):
            if lock[i][j] != key[I+i][J+j]:
                return False
    return True