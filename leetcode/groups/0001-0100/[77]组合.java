//给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。 
//
// 你可以按 任何顺序 返回答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 4, k = 2
//输出：
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//] 
//
// 示例 2： 
//
// 
//输入：n = 1, k = 1
//输出：[[1]] 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 20 
// 1 <= k <= n 
// 
// Related Topics 数组 回溯 👍 667 👎 0


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<Integer>> container = new ArrayList<>();
    LinkedList<Integer> sublist;
    int limit;
    int length;

    public List<List<Integer>> combine(int n, int k) {      // 从小到大加入数字, 避免重复
        sublist = new LinkedList<>();
        limit = n;
        length = k;
        backtrack(1);
        return container;
    }

    private void backtrack(int start) {     // start: 还未加入的1~n中, 可加入的最小元素(避免重复从小到大加入组合)
        if (sublist.size() == length) {
            container.add(new ArrayList<>(sublist));
            return;
        }
        // 设定上界的时候就确定了element选择范围, 超过这个范围则后续加入元素数量达不到数组长度要求k(可以看做剪枝)
        for (int element = start; element <= limit - length + sublist.size() + 1; element++) {
            sublist.add(element);
            backtrack(element + 1);     // 则后面只能加入比element大的元素
            sublist.removeLast();
        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)
