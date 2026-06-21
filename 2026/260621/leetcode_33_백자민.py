class Solution:
    def search(self, nums: List[int], target: int) -> int:
        #idx = nums.index(min(nums))

        def find(arr, target, s, e):
            while s<=e:
                m = (s+e)//2

                if arr[m]==target:
                    return m
                
                if arr[m]>=arr[s]:
                    if target>arr[m] or target<arr[s]:
                        s = m+1
                    else:
                        e = m-1
                else:
                    if target<arr[m] or target>=arr[s]:
                        e = m-1
                    else:
                        s = m+1
            return -1
            
        return find(nums, target, 0, len(nums)-1)