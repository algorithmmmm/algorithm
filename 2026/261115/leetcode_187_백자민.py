class Solution:
    def findRepeatedDnaSequences(self, s: str) -> list[str]:
        seen_sub = set()
        ans = set()
        s_len = len(s)

        for i in range(s_len-9):
            tmp = s[i:i+10]
            if tmp in seen_sub:
                ans.add(tmp)
            else:
                seen_sub.add(tmp)
        
        return list(ans)

        