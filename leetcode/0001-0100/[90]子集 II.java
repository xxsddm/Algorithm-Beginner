//给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。 
//
// 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。 
//
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,2]
//输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0]
//输出：[[],[0]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10 
// -10 <= nums[i] <= 10 
// 
// 
// 
// Related Topics 位运算 数组 回溯 👍 631 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<Integer>> container = new ArrayList<>();
    LinkedList<Integer> sublist = new LinkedList<>();
    int[] nums;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        this.nums = nums;
        Arrays.sort(this.nums);
        backtrack(0);
        return container;
    }

    private void backtrack(int start) {
        container.add(new ArrayList<>(sublist));
        for (int end = start; end < nums.length; end++) {
            if (end > start && nums[end] == nums[end - 1]) {
                continue;
            }
            sublist.add(nums[end]);
            backtrack(end + 1);
            sublist.removeLast();
        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)
