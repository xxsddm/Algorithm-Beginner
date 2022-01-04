//给你一个长度为 2 * n 的整数数组。你需要将 nums 分成 两个 长度为 n 的数组，分别求出两个数组的和，并 最小化 两个数组和之 差的绝对值 。
//nums 中每个元素都需要放入两个数组之一。 
//
// 请你返回 最小 的数组和之差。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：nums = [3,9,7,3]
//输出：2
//解释：最优分组方案是分成 [3,9] 和 [7,3] 。
//数组和之差的绝对值为 abs((3 + 9) - (7 + 3)) = 2 。
// 
//
// 示例 2： 
//
// 输入：nums = [-36,36]
//输出：72
//解释：最优分组方案是分成 [-36] 和 [36] 。
//数组和之差的绝对值为 abs((-36) - (36)) = 72 。
// 
//
// 示例 3： 
//
// 
//
// 输入：nums = [2,-1,0,4,-2,-9]
//输出：0
//解释：最优分组方案是分成 [2,4,-9] 和 [-1,0,-2] 。
//数组和之差的绝对值为 abs((2 + 4 + -9) - (-1 + 0 + -2)) = 0 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 15 
// nums.length == 2 * n 
// -10⁷ <= nums[i] <= 10⁷ 
// 
// Related Topics 位运算 数组 双指针 二分查找 动态规划 状态压缩 有序集合 👍 17 👎 0

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int halfLength, total, halfTotal, ans = Integer.MAX_VALUE;
    int[] leftPart, rightPart;

    public int minimumDifference(int[] nums) {  // 分治+回溯+二分
        halfLength = nums.length >> 1;
        leftPart = new int[halfLength];
        rightPart = new int[halfLength];
        for (int num: nums) {
            total += num;
        }
        halfTotal = total >> 1;
        // nums平均划分为左右两部分
        for (int i = 0; i < halfLength; i++) {
            leftPart[i] = nums[i];
            rightPart[i] = nums[i + halfLength];
        }
        Arrays.sort(leftPart);
        Arrays.sort(rightPart);
        // 枚举左侧记入总和的元素数量numLeft,则右侧记入的元素数量为halfLength-numLeft(不计入的被扣除)
        for (int numLeft = 0; numLeft <= halfLength; numLeft++) {
            int numRight = halfLength - numLeft;
            ArrayList<Integer> sumsLeft = new ArrayList<>();
            ArrayList<Integer> sumsRight = new ArrayList<>();
            backtrack(sumsLeft, leftPart, 0, 0, 0, numLeft, 0);
            backtrack(sumsRight, rightPart, 0, 0, 0, numRight, 0);
            Collections.sort(sumsRight);    // 对右侧部分记入的元素和排序
            for (int sumLeft: sumsLeft) {   // 对所有左侧部分元素和sumLeft,二分搜索halfLengthTotal-sumLeft
                int temp = Math.abs(total -
                        (sumLeft + sumsRight.get(
                                bisect(sumsRight, halfTotal - sumLeft))) * 2);
                if (temp < ans) {
                    ans = temp;
                    if (ans == 0) {
                        return ans;
                    }
                }
            }
        }
        return ans;
    }

    private void backtrack(List<Integer> list, int[] nums,
                           int start, int count,
                           int used, int limit, int cumsum) {
        if (count == limit) {
            list.add(cumsum);
            return;
        }
        for (int idx = start, XOR = 1 << start; idx <= halfLength - limit + count; idx++, XOR <<= 1) {
            // 重复值剪枝
            if (idx > 0 && nums[idx - 1] == nums[idx] && (used & (XOR >> 1)) == 0) {
                continue;
            }
            backtrack(list, nums,
                    idx + 1, count + 1,
                    used ^ XOR, limit, cumsum + nums[idx]);
        }
    }

    // 返回list中小于target的元素数量(全部小于target则考虑最大元素)
    private int bisect(List<Integer> list, int target) {
        int left = 0, right = list.size() - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (list.get(mid) > target) {
                right = mid - 1;
            }
            else if (list.get(mid) < target) {
                left = mid + 1;
            }
            else {
                return mid;
            }
        }
        return left == list.size() ? list.size() - 1 : left;
    }
}


//leetcode submit region end(Prohibit modification and deletion)
