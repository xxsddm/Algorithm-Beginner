import java.util.ArrayList;
import java.util.Arrays;

//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重
//复的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 3000 
// -105 <= nums[i] <= 105 
// 
// Related Topics 数组 双指针 排序 
// 👍 3542 👎 0
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> container = new ArrayList<List<Integer>>();
        if (nums.length < 3) return container;
        Arrays.sort(nums);
        int right2 = nums[nums.length - 1] + nums[nums.length - 2];
        for (int i = 0; i < nums.length - 2; i++){
            if (i > 0 && nums[i - 1] == nums[i]) continue;
            int aim = - nums[i];
            if (right2 < - nums[i]) continue;
            if (nums[i + 1] + nums[i + 2] > - nums[i]) break;
            int left = i + 1, right = nums.length - 1;
            while (left < right){
                if (nums[left] + nums[right] > aim) {
                    while (left < right && nums[right] == nums[right - 1]) right --;
                    right--;
                }
                else if (nums[left] + nums[right] < aim) {
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    left++;
                }
                else{
                    container.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right --;
                    left++;
                    right--;
                }
            }
        }
        return container;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
