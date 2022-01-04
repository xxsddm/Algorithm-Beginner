//我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。 
//
// 
//
// 示例: 
//
// 输入: n = 10
//输出: 12
//解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。 
//
// 说明: 
//
// 
// 1 是丑数。 
// n 不超过1690。 
// 
//
// 注意：本题与主站 264 题相同：https://leetcode-cn.com/problems/ugly-number-ii/ 
// Related Topics 哈希表 数学 动态规划 堆（优先队列） 
// 👍 201 👎 0


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
