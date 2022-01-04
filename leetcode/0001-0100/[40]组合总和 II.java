//给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。 
//
// candidates 中的每个数字在每个组合中只能使用一次。 
//
// 注意：解集不能包含重复的组合。 
//
// 
//
// 示例 1: 
//
// 
//输入: candidates = [10,1,2,7,6,1,5], target = 8,
//输出:
//[
//[1,1,6],
//[1,2,5],
//[1,7],
//[2,6]
//] 
//
// 示例 2: 
//
// 
//输入: candidates = [2,5,2,1,2], target = 5,
//输出:
//[
//[1,2,2],
//[5]
//] 
//
// 
//
// 提示: 
//
// 
// 1 <= candidates.length <= 100 
// 1 <= candidates[i] <= 50 
// 1 <= target <= 30 
// 
// Related Topics 数组 回溯 👍 670 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<Integer>> container = new ArrayList<>();
    LinkedList<Integer> sublist = new LinkedList<>();
    int[] candidates;
    int target;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        this.candidates = candidates;
        this.target = target;
        Arrays.sort(this.candidates);
        backtrack(0, 0);
        return container;

    }

    private void backtrack(int start, int cumsum) {
        if (cumsum == target) {
            container.add(new ArrayList<>(sublist));
            return;
        }
        for (int idx = start; idx < candidates.length; idx++) {     // 避免重复, 索引从小到大
            int temp = candidates[idx];

            // 剪枝很关键, 避免出现重复组合, 重复元素中仅第一个(剩余部分中的第一个)会被加入
            if (idx > start && candidates[idx] == candidates[idx - 1]) {
                continue;
            }

            if (cumsum + temp <= target) {       // 避免越界
                sublist.add(temp);
                backtrack(idx + 1, cumsum + temp);
                sublist.removeLast();
            }
        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)
