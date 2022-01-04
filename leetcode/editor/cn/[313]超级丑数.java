//超级丑数 是一个正整数，并满足其所有质因数都出现在质数数组 primes 中。 
//
// 给你一个整数 n 和一个整数数组 primes ，返回第 n 个 超级丑数 。 
//
// 题目数据保证第 n 个 超级丑数 在 32-bit 带符号整数范围内。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 12, primes = [2,7,13,19]
//输出：32 
//解释：给定长度为 4 的质数数组 primes = [2,7,13,19]，前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,
//28,32] 。 
//
// 示例 2： 
//
// 
//输入：n = 1, primes = [2,3,5]
//输出：1
//解释：1 不含质因数，因此它的所有质因数都在质数数组 primes = [2,3,5] 中。
// 
// 
//
// 
// 
// 
// 提示： 
//
// 
// 1 <= n <= 106 
// 1 <= primes.length <= 100 
// 2 <= primes[i] <= 1000 
// 题目数据 保证 primes[i] 是一个质数 
// primes 中的所有值都 互不相同 ，且按 递增顺序 排列 
// 
// 
// 
// 
// Related Topics 数组 哈希表 数学 动态规划 堆（优先队列） 
// 👍 183 👎 0


import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] idx = new int[primes.length];
        int[] container = new int[n];
        container[0] = 1;
        for (int i = 0; i < n - 1; i++) {
            int[] nums = new int[primes.length];
            for (int j = 0; j < primes.length; j++)    nums[j] = container[idx[j]] * primes[j];
            int temp = nums[0];
            ArrayList<Integer> move = new ArrayList<>();
            move.add(0);

            for (int j = 1; j < primes.length; j++) {
                if (nums[j] == temp) {
                    move.add(j);    // 避免重复值影响
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


class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {    // 注意重复值, 不过题目也没说明不能有重复值
        PriorityQueue<Long> container = new PriorityQueue<>();
        HashSet<Long> nums = new HashSet<>();
        long ans, temp;    // int会溢出
        container.add((long) 1);
        for (int i = 1; i < n; i++) {
            ans = container.poll();
            for (int prime: primes) {
                temp = ans * prime;
                if (!nums.contains(temp) && temp > 0) {
                    container.add(temp);
                    nums.add(temp);
                }
            }
        }
        return container.poll().intValue();
    }
}
