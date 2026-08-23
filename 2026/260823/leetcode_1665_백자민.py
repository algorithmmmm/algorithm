class Solution:
    def minimumEffort(self, tasks: List[List[int]]) -> int:
        tasks.sort(key=lambda x: x[1]-x[0], reverse=True)

        ans,tmp = 0,0
        for act, mi in tasks:
            if tmp<mi:
                ans += mi-tmp
                tmp = mi
            
            tmp -= act
        
        return ans
        