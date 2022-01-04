//给定一个整数数组 A，我们只能用以下方法修改该数组：我们选择某个索引 i 并将 A[i] 替换为 -A[i]，然后总共重复这个过程 K 次。（我们可以多次选
//择同一个索引 i。） 
//
// 以这种方式修改数组后，返回数组可能的最大和。 
//
// 
//
// 示例 1： 
//
// 输入：A = [4,2,3], K = 1
//输出：5
//解释：选择索引 (1,) ，然后 A 变为 [4,-2,3]。
// 
//
// 示例 2： 
//
// 输入：A = [3,-1,0,2], K = 3
//输出：6
//解释：选择索引 (1, 2, 2) ，然后 A 变为 [3,1,0,2]。
// 
//
// 示例 3： 
//
// 输入：A = [2,-3,-1,5,-4], K = 2
//输出：13
//解释：选择索引 (1, 4) ，然后 A 变为 [2,3,-1,5,4]。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= A.length <= 10000 
// 1 <= K <= 10000 
// -100 <= A[i] <= 100 
// 
// Related Topics 贪心 数组 排序 👍 99 👎 0


import java.util.ArrayList;
import java.util.Collections;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int largestSumAfterKNegations(int[] nums, int k) {       // 也可以考虑直接对整个nums排序
        // 最小非负数和最大负数, 绝对值较小的负责反复横跳
        int cumsum = 0, numNeg = 0, maxNeg = -100, minNoneNeg = 100, temp;
        ArrayList<Integer> neg = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            temp = nums[i];
            cumsum += temp;
            if (temp < 0) {
                neg.add(temp);
                numNeg++;
                if (temp > maxNeg) {
                    maxNeg = temp;
                }
            }
            else if (temp < minNoneNeg) {
                minNoneNeg = temp;
            }
        }

        if (k < numNeg) {   // k小于负数数量, 则直接取前k个较小负数
            Collections.sort(neg);      // 只需要对负数部分排序
            for (int i = 0; i < k; i++) {
                cumsum -= 2 * neg.get(i);
            }
        }
        else {             // k不小于负数数量, 则负数全部转正, 再反复横跳绝对值最小的数
            for (int num: neg) {
                cumsum -= 2 * num;
            }
            cumsum -= ((k - numNeg) % 2) * 2 * Math.min(minNoneNeg, -maxNeg);
        }
        return cumsum;
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    public int largestSumAfterKNegations(int[] nums, int k) {       // 硬刚, 很慢
        PriorityQueue<Integer> kIndex = new PriorityQueue<>(Comparator.comparingInt(o -> nums[o]));
        int cumsum = 0;
        for (int i = 0; i < nums.length; i++) {
            kIndex.add(i);
            cumsum += nums[i];
        }
        for (int i = 0; i < k; i++) {
            int idx = kIndex.poll();
            nums[idx] = - nums[idx];
            cumsum += 2 * nums[idx];
            kIndex.add(idx);
        }
        return cumsum;
    }
}
