class Solution:
    def canCompleteCircuit(self, gas: list[int], cost: list[int]) -> int:
        
        if sum(gas) < sum(cost):
            return -1
        
        total, ans = 0,0
        n = len(gas)

        for i in range(n):
            total += (gas[i]-cost[i])

            if total<0:
                total = 0
                ans = i+1
        
        return ans
        