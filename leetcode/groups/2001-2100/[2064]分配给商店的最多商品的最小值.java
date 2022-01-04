//给你一个整数 n ，表示有 n 间零售商店。总共有 m 种产品，每种产品的数目用一个下标从 0 开始的整数数组 quantities 表示，其中 
//quantities[i] 表示第 i 种商品的数目。 
//
// 你需要将 所有商品 分配到零售商店，并遵守这些规则： 
//
// 
// 一间商店 至多 只能有 一种商品 ，但一间商店拥有的商品数目可以为 任意 件。 
// 分配后，每间商店都会被分配一定数目的商品（可能为 0 件）。用 x 表示所有商店中分配商品数目的最大值，你希望 x 越小越好。也就是说，你想 最小化 分配
//给任意商店商品数目的 最大值 。 
// 
//
// 请你返回最小的可能的 x 。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 6, quantities = [11,6]
//输出：3
//解释： 一种最优方案为：
//- 11 件种类为 0 的商品被分配到前 4 间商店，分配数目分别为：2，3，3，3 。
//- 6 件种类为 1 的商品被分配到另外 2 间商店，分配数目分别为：3，3 。
//分配给所有商店的最大商品数目为 max(2, 3, 3, 3, 3, 3) = 3 。
// 
//
// 示例 2： 
//
// 
//输入：n = 7, quantities = [15,10,10]
//输出：5
//解释：一种最优方案为：
//- 15 件种类为 0 的商品被分配到前 3 间商店，分配数目为：5，5，5 。
//- 10 件种类为 1 的商品被分配到接下来 2 间商店，数目为：5，5 。
//- 10 件种类为 2 的商品被分配到最后 2 间商店，数目为：5，5 。
//分配给所有商店的最大商品数目为 max(5, 5, 5, 5, 5, 5, 5) = 5 。
// 
//
// 示例 3： 
//
// 
//输入：n = 1, quantities = [100000]
//输出：100000
//解释：唯一一种最优方案为：
//- 所有 100000 件商品 0 都分配到唯一的商店中。
//分配给所有商店的最大商品数目为 max(100000) = 100000 。
// 
//
// 
//
// 提示： 
//
// 
// m == quantities.length 
// 1 <= m <= n <= 10⁵ 
// 1 <= quantities[i] <= 10⁵ 
// 
// 👍 12 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minimizedMaximum(int n, int[] quantities) {  // 二分,尝试不同结果
        int left = 1, right = 100000;
        while (left <= right) {
            int mid = (left + right) >> 1, temp = calculate(mid, quantities);
            if (temp > n) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    int calculate(int mean, int[] nums) {   // 计算所需分配的商店数量
        int ans = 0;
        for (int num: nums) {
            ans += num / mean + (num % mean == 0 ? 0 : 1);
        }
        return ans;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
