def solution(rows, columns, queries):
    answer = []
    board = [[i*columns + j + 1 for j in range(columns)] for i in range(rows)]
    for query in queries:
        board, ans = rotate(board, query[0]-1,query[1]-1,query[2]-1,query[3]-1)
        answer.append(ans)
    return answer

def rotate(board, x1, y1, x2, y2):
    temp = []
    answer = int(1e9)
    # 우 
    for j in range(y1, y2 + 1):
        answer = min(answer, board[x1][j])
        temp.append(board[x1][j])
    # 하
    for i in range(x1+1, x2 + 1):
        answer = min(answer, board[i][y2])
        temp.append(board[i][y2])
    # 좌
    for j in range(y2-1, y1-1, -1):
        answer = min(answer, board[x2][j])
        temp.append(board[x2][j])
    # 상
    for i in range(x2-1, x1, -1):
        answer = min(answer, board[i][y1])
        temp.append(board[i][y1])
    temp = [temp[-1]] + temp[:len(temp)-1]
    idx = 0
    # 우 
    for j in range(y1, y2 + 1):
        board[x1][j] = temp[idx]
        idx+=1
    # 하
    for i in range(x1+1, x2 + 1):
        board[i][y2] = temp[idx]
        idx+=1
    # 좌
    for j in range(y2-1, y1-1, -1):
        board[x2][j] = temp[idx]
        idx+=1
    # 상
    for i in range(x2-1, x1, -1):
        board[i][y1] = temp[idx]
        idx+=1
    return board, answer