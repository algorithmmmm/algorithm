from collections import Counter

class Solution:
    def reorganizeString(self, s: str) -> str:
        count = Counter(s).most_common()
        
        if count[0][1] > (len(s)+1)//2:
            return ""
        
        ans = ['']*len(s)
        idx=0

        for char,num in count:
            for _ in range(num):
                ans[idx] = char
                idx+=2

                if idx>=len(s):
                    idx=1
        
        return "".join(ans)
        