//给你一个下标从 0 开始且长度为 n 的整数数组 nums 。分割 数组 nums 的方案数定义为符合以下两个条件的 pivot 数目： 
//
// 
// 1 <= pivot < n 
// nums[0] + nums[1] + ... + nums[pivot - 1] == nums[pivot] + nums[pivot + 1] + 
//... + nums[n - 1] 
// 
//
// 同时给你一个整数 k 。你可以将 nums 中 一个 元素变为 k 或 不改变 数组。 
//
// 请你返回在 至多 改变一个元素的前提下，最多 有多少种方法 分割 nums 使得上述两个条件都满足。 
//
// 
//
// 示例 1： 
//
// 输入：nums = [2,-1,2], k = 3
//输出：1
//解释：一个最优的方案是将 nums[0] 改为 k 。数组变为 [3,-1,2] 。
//有一种方法分割数组：
//- pivot = 2 ，我们有分割 [3,-1 | 2]：3 + -1 == 2 。
// 
//
// 示例 2： 
//
// 输入：nums = [0,0,0], k = 1
//输出：2
//解释：一个最优的方案是不改动数组。
//有两种方法分割数组：
//- pivot = 1 ，我们有分割 [0 | 0,0]：0 == 0 + 0 。
//- pivot = 2 ，我们有分割 [0,0 | 0]: 0 + 0 == 0 。
// 
//
// 示例 3： 
//
// 输入：nums = [22,4,-25,-20,-15,15,-16,7,19,-10,0,-13,-14], k = -33
//输出：4
//解释：一个最优的方案是将 nums[2] 改为 k 。数组变为 [22,4,-33,-20,-15,15,-16,7,19,-10,0,-13,-14] 。
//
//有四种方法分割数组。
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 2 <= n <= 10⁵ 
// -10⁵ <= k, nums[i] <= 10⁵ 
// 
// Related Topics 数组 哈希表 计数 枚举 前缀和 👍 13 👎 0

import java.util.HashMap;
import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int waysToPartition(int[] nums, int k) {
        // i -> 修改当前i元素为k的方案数
        int[] counter = new int[nums.length];
        // 左部分和,右部分和
        long leftSum = 0, rightSum = 0, total = 0;
        int ans = 0;
        for (int num: nums) {
            total += num;
        }

        // 左部分可行的变化幅度和数量
        HashMap<Long, LinkedList<Integer>> container = new HashMap<>();
        // 不修改的方案数,及修改划入左侧区域的部分方案数
        rightSum = total;
        for (int i = 0; i < nums.length - 1; i++) {
            int num = nums[i];
            leftSum += num;
            rightSum -= num;
            if (leftSum == rightSum) {
                ans++;
            }
            // 修改当前num为k,则leftSum变化delta=k-num
            long delta = k - num;
            if (container.containsKey(delta)) {
                container.get(delta).add(i);
            }
            else {
                LinkedList<Integer> temp = new LinkedList<>();
                temp.add(i);
                container.put(delta, temp);
            }
            // 当前左侧所需变化幅度和可行的数量
            long temp = rightSum - leftSum;
            if (container.containsKey(temp)) {
                for (int idx: container.get(temp)) {
                    counter[idx]++;
                }
            }
        }

        container = new HashMap<>();
        // 修改划入右侧区域的部分方案数
        leftSum = total;
        rightSum = 0;
        for (int i = nums.length - 1; i > 0; i--) {
            int num = nums[i];
            leftSum -= num;
            rightSum += num;
            // 修改当前num为k,则rightSum变化delta=k-num
            long delta = k - num;
            if (container.containsKey(delta)) {
                container.get(delta).add(i);
            }
            else {
                LinkedList<Integer> temp = new LinkedList<>();
                temp.add(i);
                container.put(delta, temp);
            }
            // 当前右侧所需变化幅度和可行的数量
            long temp = leftSum - rightSum;
            if (container.containsKey(temp)) {
                for (int idx: container.get(temp)) {
                    counter[idx]++;
                }
            }
        }

        for (int count: counter) {
            ans = Math.max(ans, count);
        }

        return ans;
    }
}

//leetcode submit region end(Prohibit modification and deletion)

class Solution {
    public int waysToPartition(int[] nums, int k) {
        // i -> 修改当前i元素为k的方案数
        int[] counter = new int[nums.length];
        // 左部分和,右部分和
        long leftSum = 0, rightSum = 0;
        int ans = 0;
        for (int num: nums) {
            rightSum += num;
        }

        // 左部分可行的变化幅度和相应位置
        HashMap<Long, LinkedList<Integer>> changeLeft = new HashMap<>();
        // 划入左部分前,该部分归属右部分时,右部分所需的变化幅度和数量
        HashMap<Long, Integer> changeRight = new HashMap<>();

        for (int i = 0; i < nums.length; i++) { // 修改索引i
            if (i > 0) {
                changeRight.put(leftSum - rightSum,
                        changeRight.getOrDefault(leftSum - rightSum, 0) + 1);
            }
            int num = nums[i];
            // 修改当前num为k,则变化delta=k-num
            long delta = k - num;
            counter[i] += changeRight.getOrDefault(delta, 0);
            if (i == nums.length - 1) {
                break;
            }
            leftSum += num;
            rightSum -= num;
            if (leftSum == rightSum) {
                ans++;
            }
            if (changeLeft.containsKey(delta)) {
                changeLeft.get(delta).add(i);
            }
            else {
                LinkedList<Integer> list = new LinkedList<>();
                list.add(i);
                changeLeft.put(delta, list);
            }
            // 当前左侧所需变化幅度和可行的数量
            if (changeLeft.containsKey(rightSum - leftSum)) {
                for (int idx: changeLeft.get(rightSum - leftSum)) {
                    counter[idx]++;
                }
            }
        }

        for (int count: counter) {
            ans = Math.max(ans, count);
        }

        return ans;
    }
}
