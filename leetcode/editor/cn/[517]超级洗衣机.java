//假设有 n 台超级洗衣机放在同一排上。开始的时候，每台洗衣机内可能有一定量的衣服，也可能是空的。 
//
// 在每一步操作中，你可以选择任意 m (1 <= m <= n) 台洗衣机，与此同时将每台洗衣机的一件衣服送到相邻的一台洗衣机。 
//
// 给定一个整数数组 machines 代表从左至右每台洗衣机中的衣物数量，请给出能让所有洗衣机中剩下的衣物的数量相等的 最少的操作步数 。如果不能使每台洗衣
//机中衣物的数量相等，则返回 -1 。 
//
// 
//
// 示例 1： 
//
// 
//输入：machines = [1,0,5]
//输出：3
//解释：
//第一步:    1     0 <-- 5    =>    1     1     4
//第二步:    1 <-- 1 <-- 4    =>    2     1     3    
//第三步:    2     1 <-- 3    =>    2     2     2   
// 
//
// 示例 2： 
//
// 
//输入：machines = [0,3,0]
//输出：2
//解释：
//第一步:    0 <-- 3     0    =>    1     2     0    
//第二步:    1     2 --> 0    =>    1     1     1     
// 
//
// 示例 3： 
//
// 
//输入：machines = [0,2,0]
//输出：-1
//解释：
//不可能让所有三个洗衣机同时剩下相同数量的衣物。
// 
//
// 
//
// 提示： 
//
// 
// n == machines.length 
// 1 <= n <= 10⁴ 
// 0 <= machines[i] <= 10⁵ 
// 
// Related Topics 贪心 数组 👍 85 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findMinMoves(int[] machines) {   // 从较多位置的顶端移动一个到 连接(路径中均非0) 的较少位置
        // 分割为多个machines(多个区域),每个都能在分割的范围内部移动(外部移动会使次数增加)使每个元素变为average
        // 考虑任意区域,视为左侧和右侧两个连接子区域,当前左侧累计差值cumsum(绝对值)即当前待移动次数
        // 然后不断移动边界扩大左侧缩小右侧
        // 还需要考虑交换次数 下界 即当前区域最大超出数量num(不考虑最大不足数量因为可以有左右两边向其移动)
        // 然后多个machines当中最大的移动次数即答案

        // 但由于每个区域的cumsum最终为0(可以内部移动使元素均为average故该区域累计差值cumsum为0),可以合并为一次遍历
        int total = 0, length = machines.length;
        for (int machine: machines) {
            total += machine;
        }
        if (total % length != 0) {
            return -1;
        }
        int cumsum = 0, average = total / length, ans = 0, maxNum = 0;
        for (int machine : machines) {
            int num = machine - average;
            cumsum += num;
            maxNum = Math.max(maxNum, num);
            ans = Math.max(ans, Math.abs(cumsum));
        }
        return Math.max(ans, maxNum);
    }
}

//leetcode submit region end(Prohibit modification and deletion)
