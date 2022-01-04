//已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,4,4,5,6,7] 在变
//化后可能得到：
// 
// 若旋转 4 次，则可以得到 [4,5,6,7,0,1,4] 
// 若旋转 7 次，则可以得到 [0,1,4,4,5,6,7] 
// 
//
// 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], 
//..., a[n-2]] 。 
//
// 给你一个可能存在 重复 元素值的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,3,5]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：nums = [2,2,2,0,1]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 1 <= n <= 5000 
// -5000 <= nums[i] <= 5000 
// nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转 
// 
//
// 
//
// 进阶： 
//
// 
// 这道题是 寻找旋转排序数组中的最小值 的延伸题目。 
// 允许重复会影响算法的时间复杂度吗？会如何影响，为什么？ 
// 
// Related Topics 数组 二分查找 👍 401 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findMin(int[] nums) {
        // 检索最小值的数组范围
        int left = 0, right = nums.length - 1;
        while (left < right) {
            // 可以直接判断的情况
            if (nums[left] < nums[right]) {
                return nums[left];
            }
            int mid = (left + right) >> 1;
            // 左端值大于中间值时,目标范围缩小为[left,mid]
            if (nums[left] > nums[mid]) {
                right = mid;
            }
            // 右侧值小于中间值时,目标范围缩小为[mid+1,right]
            else if (nums[right] < nums[mid]) {
                left = mid + 1;
            }
            // 否则可能在[left,right-1]
            else {
                right--;
            }
        }
        return nums[left];
    }
}

//leetcode submit region end(Prohibit modification and deletion)

class Solution {
    public int findMin(int[] nums) {
        int ans = 5000;
        LinkedList<int[]> container = new LinkedList<>();
        container.add(new int[] {0, nums.length - 1});
        while (!container.isEmpty()) {
            int[] pair = container.poll();
            int left = pair[0], right = pair[1];
            if (nums[left] < nums[right] || left == right) {
                ans = Math.min(ans, nums[left]);
                continue;
            }
            int mid = (left + right) >> 1;
            if (nums[left] > nums[mid]) {
                container.add(new int[] {left, mid});
            }
            else if (nums[left] < nums[mid]) {
                container.add(new int[] {mid + 1, right});
            }
            else {
                container.add(new int[] {left, mid});
                container.add(new int[] {mid + 1, right});
            }
        }
        return ans;
    }
}
