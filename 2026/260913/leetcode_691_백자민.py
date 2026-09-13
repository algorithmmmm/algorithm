from collections import Counter
from typing import List

class Solution:
    def minStickers(self, stickers: List[str], target: str) -> int:
        n = len(target)
        ans = (1<<n)-1

        sticker_count = [Counter(sticker) for sticker in stickers]

        # dp[tmp] tmp 까지 사용한 최소 스티커 수
        dp = [float('inf')]*(1<<n)
        dp[0] = 0

        for tmp in range(1<<n):
            if dp[tmp] == float('inf'): #tmp까지 도달 불가
                continue

            for sticker in sticker_count:
                nxt = tmp
                count = sticker.copy()

                #만들 문자 탐색
                for i in range(n):
                    if nxt & (1<<i): #이미완성
                        continue

                    char = target[i]

                    if count[char]>0: 
                        count[char]-=1 #문자 사용
                        nxt |= (1<<i)

                dp[nxt] = min(dp[nxt], dp[tmp]+1)

        if dp[ans] == float('inf'):
            return -1

        return dp[ans]