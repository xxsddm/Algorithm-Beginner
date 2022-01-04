//给你一个整数 n ，请你找出并返回第 n 个 丑数 。 
//
// 丑数 就是只包含质因数 2、3 和/或 5 的正整数。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 10
//输出：12
//解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：1
//解释：1 通常被视为丑数。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 1690 
// 
// Related Topics 哈希表 数学 动态规划 堆（优先队列） 
// 👍 710 👎 0


import java.util.ArrayList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int nthUglyNumber(int n) {
        int[] prime = {2, 3, 5};
        int[] idx = {0, 0, 0};
        int[] container = new int[n];
        container[0] = 1;
        for (int i = 0; i < n - 1; i++) {
            int[] nums = new int[3];
            for (int j = 0; j < 3; j++)    nums[j] = container[idx[j]] * prime[j];
            int temp = nums[0];
            ArrayList<Integer> move = new ArrayList<>();
            move.add(0);

            for (int j = 1; j < 3; j++) {
                if (nums[j] == temp) {
                    move.add(j);
                }
                else if (nums[j] < temp) {
                    temp = nums[j];
                    move.clear();
                    move.add(j);
                }
            }

            for (int moving: move)    idx[moving]++;
            container[i + 1] = temp;
        }
        return container[n - 1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
