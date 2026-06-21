class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while(left <= right) {
            int mid = (left + right) / 2;

            if(nums[mid] == target) {
                return mid;
            }

            // 왼쪽이 정렬
            if(nums[left] <= nums[mid]) {
                if(nums[left] <= target && target < nums[mid]) { // 왼쪽 범위 안에 있음
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // 오른쪽이 정렬
            else {
                if(nums[mid] < target && target <= nums[right]) { // 오른쪽 범위 안에 있음
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }
}