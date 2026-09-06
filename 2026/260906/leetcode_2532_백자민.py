import heapq
from typing import List

class Solution:
    def findCrossingTime(self, n: int, k: int, time: List[List[int]]) -> int:
        #(-efficiency, -idx, idx)
        waitL, waitR = [], []
        workL, workR = [], []

        for i in range(k): 
            r, pick, l, put = time[i]
            heapq.heappush(waitL, (-(l+r), -i, i))
        
        ans=0
        sent=0 #짐 가지러 간 사람
        returned = 0 #가져온 짐

        while waitL or waitR or workL or workR:

            #작업이 끝났으면 대기 상태로
            while workL and workL[0][0]<=ans:
                _,i = heapq.heappop(workL)
                r, pick, l, put = time[i]
                heapq.heappush(waitL, (-(l+r), -i, i))
            
            while workR and workR[0][0]<=ans:
                _,i = heapq.heappop(workR)
                r, pick, l, put = time[i]
                heapq.heappush(waitR, (-(l+r), -i, i))

            
            #1.오른쪽에서 기다리는 사람은 먼저 이동
            if waitR:
                _,_,i = heapq.heappop(waitR)
                r,pick,l,put = time[i]

                ans+=l #이동시간
                returned+=1
                heapq.heappush(workL, (ans+put, i))#put시간

                if returned==n: #사람 더 보낼필요X
                    return ans
            
            #2.돌아올 작업자가 없으면 오른쪽으로 보내기
            elif waitL and sent<n:
                _,_,i = heapq.heappop(waitL)
                r, pick, l, put = time[i]

                ans+=r #이동시간
                sent+=1
                heapq.heappush(workR, (ans+pick, i)) #pick시간
            
            #3.다리 이동할 작업자가 없으면 제일 먼저 끝나는 사람 선택
            else:
                nextL = workL[0][0] if workL else float('inf')
                nextR = workR[0][0] if workR else float('inf')

                ans = min(nextL, nextR)
        
    
        return ans
