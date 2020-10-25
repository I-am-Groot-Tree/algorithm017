public class Solution_33 {
    /**
     * 解题思路：
     * 二分查找
     */
    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int n = nums.length - 1;
        int l = 0, r = n;
        //注意是小于等于
        while (l <= r) {
            int mid = (l + r) / 2;
            if (target == nums[mid]) {
                return mid;
            }
            //因为是升序数组旋转，所以中间数大于首位数则左边有序
            if (nums[mid] >= nums[0]) {
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }
}
