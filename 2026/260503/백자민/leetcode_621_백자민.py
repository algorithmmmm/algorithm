class Solution:
    def leastInterval(self, tasks: List[str], n: int) -> int:
        freq_map = collections.Counter(tasks).values()
        max_value = max(freq_map)
        another_max = list(freq_map).count(max_value) #최댓값이 여러개일 때

        return max((max_value-1)*(n+1)+another_max, len(tasks))
        