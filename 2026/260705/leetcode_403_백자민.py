from collections import defaultdict

class Solution:
    def canCross(self, stones: List[int]) -> bool:
        dic = {stone: i for i, stone in enumerate(stones)}

        dp = defaultdict(set)
        dp[0].add(0)

        n = len(stones)

        for i in range(n):
            for k in dp[i]:
                for jump in (k-1, k, k+1):
                    if jump <= 0:
                        continue

                    nxt = stones[i] + jump

                    if nxt in dic:
                        dp[dic[nxt]].add(jump)

        return len(dp[n-1]) > 0