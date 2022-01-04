//给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。 
//
// 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。 
//
// 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。 
//
// 注意：不允许旋转信封。 
// 
//
// 示例 1： 
//
// 
//输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
//输出：3
//解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。 
//
// 示例 2： 
//
// 
//输入：envelopes = [[1,1],[1,1],[1,1]]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= envelopes.length <= 5000 
// envelopes[i].length == 2 
// 1 <= wi, hi <= 10⁴ 
// 
// Related Topics 数组 二分查找 动态规划 排序 👍 584 👎 0

import java.util.ArrayList;
import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    ArrayList<Integer> container;

    public int maxEnvelopes(int[][] envelopes) {    // 二分(DP见cpp)
        int length = envelopes.length;
        if (length <= 1) {
            return length;
        }
        container = new ArrayList<>();
        Arrays.sort(envelopes, (o1, o2) -> {
            if (o1[1] != o2[1]) {
                return o1[1] - o2[1];   // 按其中一个分量优先排序(从小到大)
            }
            return o2[0] - o1[0];   // 另一个分量从大到小排序
        });
        container.add(envelopes[0][0]);
        for (int i = 1; i < length; i++) {
            int[] envelope = envelopes[i];
            // 当排序次优先变量较大(且出现在已有元素后面),说明优先变量也较大
            // 同等[1]条件下优先加入较大[0],则加入到末尾时一定是当前[1]对应最大[0],不可能已有相同[1]
            if (envelope[0] > container.get(container.size() - 1)) {
                container.add(envelope[0]);
            }
            else {  // 否则考虑用次优先变量较小的元素代替已有元素
                // 同等[1]条件下优先加入较大[0],则后续加入元素会在[1]分量不变小的条件下优先选择较小[0](后进替代先进)
                container.set(bisect(envelope), envelope[0]);
            }
        }
        return container.size();
    }

    private int bisect(int[] envelope) {    // 返回小于当前envelope[0]的元素数量
        int left = 0, right = container.size() - 1, pivot = envelope[0];
        while (left <= right) {
            int mid = (left + right) >> 1, numMid = container.get(mid);
            if (numMid < pivot) {
                left = mid + 1;
            }
            else if (numMid > pivot){
                right = mid - 1;
            }
            else {
                return mid;
            }
        }
        return left;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
