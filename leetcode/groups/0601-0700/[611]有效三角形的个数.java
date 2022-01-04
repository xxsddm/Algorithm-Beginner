//给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。 
//
// 示例 1: 
//
// 
//输入: [2,2,3,4]
//输出: 3
//解释:
//有效的组合是: 
//2,3,4 (使用第一个 2)
//2,3,4 (使用第二个 2)
//2,2,3
// 
//
// 注意: 
//
// 
// 数组长度不超过1000。 
// 数组里整数的范围为 [0, 1000]。 
// 
// Related Topics 贪心 数组 双指针 二分查找 排序 
// 👍 236 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for (int right = 2; right < nums.length; right++){
            int left = 0, mid = right - 1;
            while (left < mid){
                if (nums[left] + nums[mid] > nums[right]) count += mid-- - left;
                else left++;
            }
        }
        return count;
    }
}

//leetcode submit region end(Prohibit modification and deletion)

class Solution {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i < nums.length - 2; i++){
            for (int j = i + 1; j < nums.length - 1; j++){
                int temp = nums[i] + nums[j];
                int left = 0, right = nums.length - 1, mid;
                while (left <= right){
                    mid = (left + right) >> 1;
                    if (nums[mid] > temp) right = mid - 1;
                    else if (nums[mid] < temp) left = mid + 1;
                    else {
                        while (mid > 0 && nums[mid] == nums[mid - 1]) mid--;
                        left = mid;
                        break;
                    }
                }
                temp = left - j - 1;
                if (temp > 0) count += temp;
            }
        }
        return count;
    }
}
