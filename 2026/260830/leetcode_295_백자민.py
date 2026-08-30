import heapq

class MedianFinder:

    def __init__(self):
        self.small = [] #최대힙
        self.large = [] #최소힙

        #small = [-3,-2,-1]
        #large = [4,5,6]

    def addNum(self, num: int) -> None:
        heapq.heappush(self.small, -num)

        if self.small and self.large and (-self.small[0]>self.large[0]):
            tmp = -heapq.heappop(self.small)
            heapq.heappush(self.large, tmp)
        
        if len(self.small) > len(self.large)+1: 
            tmp = -heapq.heappop(self.small) 
            heapq.heappush(self.large, tmp) 
            
        elif len(self.large) > len(self.small)+1: 
            tmp = heapq.heappop(self.large) 
            heapq.heappush(self.small, -tmp)

    def findMedian(self) -> float:
        #홀수
        if len(self.small) < len(self.large):
            return self.large[0]
        elif len(self.small) > len(self.large):
            return -self.small[0]
        
        #짝수
        else:
            return (-self.small[0] + self.large[0])/2


# Your MedianFinder object will be instantiated and called as such:
# obj = MedianFinder()
# obj.addNum(num)
# param_2 = obj.findMedian()