//给你一个整数数组 nums ，找出并返回所有该数组中不同的递增子序列，递增子序列中 至少有两个元素 。你可以按 任意顺序 返回答案。 
//
// 数组中可能含有重复元素，如出现两个整数相等，也可以视作递增序列的一种特殊情况。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [4,6,7,7]
//输出：[[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [4,4,3,2,1]
//输出：[[4,4]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 15 
// -100 <= nums[i] <= 100 
// 
// Related Topics 位运算 数组 哈希表 回溯 👍 321 👎 0


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<Integer>> container = new ArrayList<>();
    LinkedList<Integer> sublist = new LinkedList<>();
    int[] nums;

    public List<List<Integer>> findSubsequences(int[] nums) {
        this.nums = nums;
        backtrack(0, -101);
        return container;
    }

    private void backtrack(int start, int maxnum) {
        if (sublist.size() > 1) {
            container.add(new ArrayList<>(sublist));
        }
        HashSet<Integer> used = new HashSet<>();
        for (int end = start; end < nums.length; end++) {
            if (nums[end] >= maxnum && !used.contains(nums[end])) {
                sublist.add(nums[end]);
                used.add(nums[end]);
                backtrack(end + 1, nums[end]);
                sublist.removeLast();
            }
        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    List<List<Integer>> container = new ArrayList<>();
    LinkedList<Integer> sublist = new LinkedList<>();
    int[] nums;

    public List<List<Integer>> findSubsequences(int[] nums) {
        this.nums = nums;
        backtrack(0, -101);
        return container;
    }

    private void backtrack(int start, int maxnum) {
        if (sublist.size() > 1) {
            container.add(new ArrayList<>(sublist));
        }
        for (int end = start; end < nums.length; end++) {
            if (nums[end] >= maxnum && before(nums[end], start, end)) {
                sublist.add(nums[end]);
                backtrack(end + 1, nums[end]);
                sublist.removeLast();
            }
        }
    }

    private boolean before(int target, int start, int end) {        // 数组短, 比hash快点
        for (int i = start; i < end; i++) {
            if (nums[i] == target) {
                return false;
            }
        }
        return true;
    }
}
