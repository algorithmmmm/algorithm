class Solution:
    def maxArea(self, height: List[int]) -> int:
        l=0
        r=len(height)-1
        extent=0

        while l<r:
            h = min(height[l], height[r])
            w = r-l
            extent = max(extent, h*w)

            if height[l]<height[r]:
                l+=1
            else:
                r-=1

        return extent
        