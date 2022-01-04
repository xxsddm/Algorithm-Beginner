//在 LeetCode 商店中， 有 n 件在售的物品。每件物品都有对应的价格。然而，也有一些大礼包，每个大礼包以优惠的价格捆绑销售一组物品。 
//
// 给你一个整数数组 price 表示物品价格，其中 price[i] 是第 i 件物品的价格。另有一个整数数组 needs 表示购物清单，其中 needs[
//i] 是需要购买第 i 件物品的数量。 
//
// 还有一个数组 special 表示大礼包，special[i] 的长度为 n + 1 ，其中 special[i][j] 表示第 i 个大礼包中内含第 
//j 件物品的数量，且 special[i][n] （也就是数组中的最后一个整数）为第 i 个大礼包的价格。 
//
// 返回 确切 满足购物清单所需花费的最低价格，你可以充分利用大礼包的优惠活动。你不能购买超出购物清单指定数量的物品，即使那样会降低整体价格。任意大礼包可无限
//次购买。 
//
// 
//
// 示例 1： 
//
// 
//输入：price = [2,5], special = [[3,0,5],[1,2,10]], needs = [3,2]
//输出：14
//解释：有 A 和 B 两种物品，价格分别为 ¥2 和 ¥5 。 
//大礼包 1 ，你可以以 ¥5 的价格购买 3A 和 0B 。 
//大礼包 2 ，你可以以 ¥10 的价格购买 1A 和 2B 。 
//需要购买 3 个 A 和 2 个 B ， 所以付 ¥10 购买 1A 和 2B（大礼包 2），以及 ¥4 购买 2A 。 
//
// 示例 2： 
//
// 
//输入：price = [2,3,4], special = [[1,1,0,4],[2,2,1,9]], needs = [1,2,1]
//输出：11
//解释：A ，B ，C 的价格分别为 ¥2 ，¥3 ，¥4 。
//可以用 ¥4 购买 1A 和 1B ，也可以用 ¥9 购买 2A ，2B 和 1C 。 
//需要买 1A ，2B 和 1C ，所以付 ¥4 买 1A 和 1B（大礼包 1），以及 ¥3 购买 1B ， ¥4 购买 1C 。 
//不可以购买超出待购清单的物品，尽管购买大礼包 2 更加便宜。 
//
// 
//
// 提示： 
//
// 
// n == price.length 
// n == needs.length 
// 1 <= n <= 6 
// 0 <= price[i] <= 10 
// 0 <= needs[i] <= 10 
// 1 <= special.length <= 100 
// special[i].length == n + 1 
// 0 <= special[i][j] <= 50 
// 
// Related Topics 位运算 记忆化搜索 数组 动态规划 回溯 状态压缩 👍 198 👎 0

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {    // 记忆化搜索(回溯见cpp)
    int ans = -1, length = 0;
    // 剩余need所需消耗
    HashMap<List<Integer>, Integer> container = new HashMap<>();
    List<Integer> prices;
    List<List<Integer>> specials;
    List<Boolean> available;

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        length = price.size();
        prices = price;
        specials = special;
        available = new ArrayList<>();
        for (int i = 0; i < special.size(); i++) {
            available.add(true);
        }
        dfs(needs, 0);
        return ans;
    }

    // 返回剩余need所需消耗,cumsum为原始状态到当前need累积消耗(用于剪枝)
    private int dfs(List<Integer> need, int cumsum) {
        if (container.containsKey(need)) {
            return container.get(need);
        }
        // 初始化为零售价组合
        int count = 0;
        for (int i = 0; i < length; i++) {
            count += prices.get(i) * need.get(i);
        }
        // 考虑不同special
        List<Integer> change = new LinkedList<>();
        for (int i = 0; i < specials.size(); i++) {
            List<Integer> special = specials.get(i);
            // 剪枝
            if (!available.get(i) ||
                    special.get(length) > count ||
                    ans != -1 && cumsum + special.get(length) > ans) {
                continue;
            }
            List<Integer> nextNeed = new ArrayList<>();
            for (int j = 0; j < length; j++) {
                if (special.get(j) > need.get(j)) {
                    available.set(i, false);
                    if (cumsum != 0) {
                        change.add(i);
                    }
                    break;
                }
                nextNeed.add(need.get(j) - special.get(j));
                if (j == length - 1) {
                    count = Math.min(count, special.get(length) +
                            dfs(nextNeed, cumsum + special.get(length)));
                }
            }
        }
        // 本轮判定
        for (int idx: change) {
            available.set(idx, true);
        }
        container.put(need, count);
        // 更新结果
        if (ans == -1) {
            ans = cumsum + count;
        }
        else {
            ans = Math.min(ans, cumsum + count);
        }
        return count;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
