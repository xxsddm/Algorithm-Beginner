//找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。 
//
// 说明： 
//
// 
// 所有数字都是正整数。 
// 解集不能包含重复的组合。 
// 
//
// 示例 1: 
//
// 输入: k = 3, n = 7
//输出: [[1,2,4]]
// 
//
// 示例 2: 
//
// 输入: k = 3, n = 9
//输出: [[1,2,6], [1,3,5], [2,3,4]]
// 
// Related Topics 数组 回溯 👍 334 👎 0


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int length;
    int target;
    LinkedList<Integer> sublist;
    List<List<Integer>> container = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        length = k;
        target = n;
        sublist = new LinkedList<>();
        backtrack(1, 0);
        return container;
    }

    private void backtrack(int start, int cumsum) {
        if (sublist.size() == length && cumsum == target) {
            container.add(new ArrayList<>(sublist));
            return;
        }
        // 避免重复, 从小到大填入
        // 数组有长度要求k, 因此填入当前数字时需要判断剩余数字是否足够使用, 且加入后累和不超过目标值
        for (int num = start; num <= 10 - length + sublist.size() && num <= target - cumsum; num++) {
            sublist.add(num);
            backtrack(num + 1, cumsum + num);
            sublist.removeLast();
        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)
