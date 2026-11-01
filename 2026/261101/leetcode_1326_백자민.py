class Solution:
    def minTaps(self, n: int, ranges: List[int]) -> int:
        cover = [0]*(n+1)

        for i in range(n+1):
            l = max(0, i-ranges[i])
            r = min(n, i+ranges[i])
            cover[l] = max(cover[l], r)

        tmpR, maxR, ans = 0, 0, 0

        for i in range(n+1):
            if maxR<i:
                return -1

            if tmpR<i:
                tmpR=maxR
                ans+=1

            maxR = max(maxR, cover[i])

        return ans