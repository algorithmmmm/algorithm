import heapq
INF = 2e9
def solution(board):
    answer = INF
    N = len(board)
    # 상좌하우 -> 짝수는 상하, 홀수는 좌우
    di = [-1, 0, 1, 0]  
    dj = [0, -1, 0, 1]
    
    # Q, visit init
    Q = []
    # visit[i][j][k] = (i, j) 위치를 이전 방향을 k로 했을 때 건설한 비용의 최솟값
    visit = [[[INF for _ in range(4)] for _ in range(N)] for _ in range(N)]
    
    # add start
    heapq.heappush(Q, (0, 0, 0, -1)) # (i, j, 거리, 이전 방향)
    visit[0][0] = [0, 0, 0, 0]
    
    while Q:
        tp = heapq.heappop(Q)
        for k in range(4):
            ni = di[k] + tp[0]
            nj = dj[k] + tp[1]
            # 범위를 벗어나거나 벽인 경우 -> 경주로 건설 불가
            if ni < 0 or nj < 0 or ni >= N or nj >= N or board[ni][nj] == 1:
                continue
            # dist : 직선도로 한 개 건설 비용 추가
            dist = tp[2] + 100
            if isCorner(tp[3], k):  # 코너라면 코너 건설 비용 추가
                dist += 500
            # 방문 체크
            # 만약 visit 배열의 값이 dist보다 작다면 -> 이미 해당 경우로 왔었음
            if visit[ni][nj][k] < dist: 
                continue
            # 도착지에 도착했다면 answer update
            if ni == N-1 and nj == N-1:
                answer = min(answer, dist)
                continue
            # Q에 넣기
            heapq.heappush(Q, (ni, nj, dist, k))
            visit[ni][nj][k] = dist
    return answer
def isCorner(prev, now):   # dist : 거리, prev : 이전 방향, now : 현재 방향
    if prev == -1: # 처음 시작 -> 직선도로
        return False
    # 이전 방향, 현재 방향이 둘 다 짝수(상하)거나 둘 다 홀수(좌우) -> 직선도로
    if (now % 2 == 0 and prev % 2 == 0):
        return False
    if (now % 2 == 1 and prev % 2 == 1):
        return False
    # 코너
    return True

    