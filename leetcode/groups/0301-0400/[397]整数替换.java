//给定一个正整数 n ，你可以做如下操作： 
//
// 
// 如果 n 是偶数，则用 n / 2替换 n 。 
// 如果 n 是奇数，则可以用 n + 1或n - 1替换 n 。 
// 
//
// n 变为 1 所需的最小替换次数是多少？ 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 8
//输出：3
//解释：8 -> 4 -> 2 -> 1
// 
//
// 示例 2： 
//
// 
//输入：n = 7
//输出：4
//解释：7 -> 8 -> 4 -> 2 -> 1
//或 7 -> 6 -> 3 -> 2 -> 1
// 
//
// 示例 3： 
//
// 
//输入：n = 4
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 2³¹ - 1 
// 
// Related Topics 贪心 位运算 记忆化搜索 动态规划 👍 115 👎 0

import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    HashMap<Integer, Integer> container = new HashMap<>();

    public int integerReplacement(int n) {  // 记忆化搜索(使用负数防止溢出)
        container.put(-1, 0);
        return dfs(-n);
    }

    int dfs(int num) {
        if (container.containsKey(num)) {
            return container.get(num);
        }
        int ans;
        if (num % 2 == 0) {
            ans = 1 + dfs(num / 2);
            container.put(num, ans);
            return ans;
        }
        ans = 1 + Math.min(dfs(num + 1), dfs(num - 1));
        container.put(num, ans);
        return ans;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
