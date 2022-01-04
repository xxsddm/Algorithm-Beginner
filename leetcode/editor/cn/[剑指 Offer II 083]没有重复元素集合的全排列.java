//给定一个不含重复数字的整数数组 nums ，返回其 所有可能的全排列 。可以 按任意顺序 返回答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1]
//输出：[[0,1],[1,0]]
// 
//
// 示例 3： 
//
// 
//输入：nums = [1]
//输出：[[1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 6 
// -10 <= nums[i] <= 10 
// nums 中的所有整数 互不相同 
// 
//
// 
//
// 注意：本题与主站 46 题相同：https://leetcode-cn.com/problems/permutations/ 
// Related Topics 数组 回溯 
// 👍 1 👎 0


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<Integer> target;

    public List<List<Integer>> permute(int[] nums) {
        target = new ArrayList<>(nums.length);
        for (int num: nums) {
            target.add(num);
        }
        return permute(target);
    }

    private List<List<Integer>> permute(List<Integer> target) {
        List<List<Integer>> container = new ArrayList<>();
        if (target.size() == 1) {
            container.add(new ArrayList<>(target));
            return container;
        }
        for (int i = 0; i < target.size(); i++) {
            Collections.swap(target, i, target.size() - 1);
            int temp = target.remove(target.size() - 1);
            for (List<Integer> subcontainer: permute(target)) {
                subcontainer.add(temp);
                container.add(subcontainer);
            }
            target.add(temp);
            Collections.swap(target, i, target.size() - 1);
        }
        return container;
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    List<List<Integer>> container = new ArrayList<>();
    List<Integer> target;

    public List<List<Integer>> permute(int[] nums) {
        target = new ArrayList<>(nums.length);
        for (int num : nums) {
            target.add(num);
        }
        permute(0);
        return container;
    }

    private void permute(int idx) {
        if (idx == target.size() - 1) {
            container.add(new ArrayList<>(target));
            return;
        }
        for (int i = idx; i < target.size(); i++) {
            Collections.swap(target, idx, i);
            permute(idx + 1);
            Collections.swap(target, idx, i);
        }
    }
}
