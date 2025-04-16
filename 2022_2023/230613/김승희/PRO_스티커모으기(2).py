import copy
import heapq
def solution(sticker):
    answer = 0
    if len(sticker) < 4:
        answer = max(sticker)
    else:
        dp1 = [[0, 0] for _ in range(len(sticker))]
        dp2 = [[0, 0] for _ in range(len(sticker))]

        # 첫 번째 스티커를 사용하는 경우
        dp1[0][1] = sticker[0]
        dp1[1][0] = max(dp1[0])
        dp1[1][1] = sticker[1]
        for i in range(2, len(sticker)):
            # i번째 스티커를 사용하지 않는 경우 -> i-1번째 스티커를 사용할 수 있음
            dp1[i][0] = max(dp1[i-1])
            # i번째 스티커를 사용하는 경우 -> i-2번째 스티커를 사용할 수 있음
            dp1[i][1] = sticker[i] + max(dp1[i-2])

        # 첫 번째 스티커를 사용하지 않는 경우
        dp2[1][1] = sticker[1]
        for i in range(2, len(sticker)):
            # i번째 스티커를 사용하지 않는 경우 -> i-1번째 스티커를 사용할 수 있음
            dp2[i][0] = max(dp2[i-1])
            # i번째 스티커를 사용하는 경우 -> i-2번째 스티커를 사용할 수 있음
            dp2[i][1] = sticker[i] + max(dp2[i-2])

        answer = max(answer, max(dp1[len(sticker)-2]))
        answer = max(answer, max(dp2[len(sticker)-1]))
    return answer
        
    