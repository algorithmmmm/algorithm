from collections import deque
def solution(bridge_length, weight, truck_weights):
    time = 0
    bridge = 0
    Q = deque()
    for truck in truck_weights:
        time += 1
        if len(Q) == 0:
            bridge += truck
            Q.append((truck, time))
            continue
        if Q[0][1] + bridge_length <= time:
            bridge -= Q[0][0]
            Q.popleft()
        while bridge + truck > weight:
            bridge -= Q[0][0]
            time = Q[0][1] + bridge_length
            Q.popleft()
        bridge += truck
        Q.append((truck, time))
    if Q:
        time = Q[-1][1] + bridge_length
    return time