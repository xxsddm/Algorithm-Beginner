//给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。 
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
// Related Topics 数组 回溯 
// 👍 1487 👎 0


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<Integer> target;

    public List<List<Integer>> permute(int[] nums) {    // 慢一些
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
                List<Integer> tempsub = new ArrayList<>(subcontainer);
                tempsub.add(temp);
                container.add(tempsub);
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

    public List<List<Integer>> permute(int[] nums) {
        List<Integer> target = new ArrayList<>(nums.length);
        for (int num : nums) {
            target.add(num);
        }
        backtrack(target, new ArrayList<>());
        return container;
    }

    private void backtrack(List<Integer> target, List<Integer> cache) {
        if (target.isEmpty()) {
            container.add(cache);    // 不能直接用cache, 后面不断修改cache会导致已经加入的cache改变
        }
        else {
            for (int i = 0; i < target.size(); i++) {
                List<Integer> subcache = new ArrayList<>(cache.subList(0, cache.size()));
                List<Integer> subtarget = new ArrayList<>(target.subList(0, target.size()));
                subcache.add(target.get(i));
                subtarget.remove(i);
                backtrack(subtarget, subcache);
            }
        }
    }
}


class Solution {
    List<List<Integer>> container = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        List<Integer> aim = new ArrayList<>(nums.length);
        for (int num: nums) {
            aim.add(num);
        }
        backtrack(0, aim);
        return container;
    }

    private void backtrack(int start, List<Integer> target) {
        if (start == target.size()) {
            container.add(new ArrayList<>(target));
        }
        for (int i = start; i < target.size(); i++) {
            Collections.swap(target, start, i);
            backtrack(start + 1, target);
            Collections.swap(target, start, i);
        }
    }
}
