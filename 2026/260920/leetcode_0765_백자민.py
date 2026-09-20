class Solution:
    def minSwapsCouples(self, row: list[int]) -> int:
        seats = {idx: i for i, idx in enumerate(row)}
        ans = 0

        for i in range(0, len(row), 2):
            tmp = row[i]
            couple = tmp+1 if tmp%2==0 else tmp-1 #짝궁 번호

            if row[i+1]!=couple: #옆자리가 짝궁이 아니면
                target = seats[couple]
                change = row[i+1]

                row[i+1] = couple
                row[target] = change

                seats[change] = target
                ans+=1

        return ans        