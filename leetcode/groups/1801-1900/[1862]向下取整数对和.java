//给你一个整数数组 nums ，请你返回所有下标对 0 <= i, j < nums.length 的 floor(nums[i] / nums[j]) 结果
//之和。由于答案可能会很大，请你返回答案对10⁹ + 7 取余 的结果。 
//
// 函数 floor() 返回输入数字的整数部分。 
//
// 
//
// 示例 1： 
//
// 输入：nums = [2,5,9]
//输出：10
//解释：
//floor(2 / 5) = floor(2 / 9) = floor(5 / 9) = 0
//floor(2 / 2) = floor(5 / 5) = floor(9 / 9) = 1
//floor(5 / 2) = 2
//floor(9 / 2) = 4
//floor(9 / 5) = 1
//我们计算每一个数对商向下取整的结果并求和得到 10 。
// 
//
// 示例 2： 
//
// 输入：nums = [7,7,7,7,7,7,7]
//输出：49
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// 1 <= nums[i] <= 10⁵ 
// 
// Related Topics 数组 数学 二分查找 前缀和 👍 22 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public static int sumOfFlooredPairs(int[] nums) {   // 计数
        int maxnum = nums[0], mod = 1000000007;
        for (int num : nums) {
            maxnum = Math.max(maxnum, num);
        }

        // i -> nums中不大于i的数的数量
        int[] counter = new int[maxnum + 1];
        for (int num : nums) {
            counter[num]++;
        }
        for (int num = 1; num <= maxnum; num++) {
            counter[num] += counter[num - 1];
        }

        long ans = 0;
        for (int num = 1; num <= maxnum; num++) {
            // num的数量
            int count = counter[num] - counter[num - 1];
            if (count == 0) {
                continue;
            }
            // 枚举所有的商quotient
            for (int quotient = 1; quotient <= maxnum / num; quotient++) {
                // x/num=quotient,则x范围为 quotient*num-1 ~ (quotient*num)+num-1 (考虑上界可能溢出maxnum)
                int lowerLimit = quotient * num - 1, upperLimit = Math.min((quotient * num) + num - 1, maxnum);
                ans += (long) count * quotient * (counter[upperLimit] - counter[lowerLimit]);
                ans %= mod;
            }
        }

        return (int) ans;
    }
}

//leetcode submit region end(Prohibit modification and deletion)

class Solution {
    int[] nums;

    public int sumOfFlooredPairs(int[] nums) {  // 二分
        int mod = 1000000007;
        long ans = 0, prev = 0;
        Arrays.sort(nums);
        this.nums = nums;
        int maxnum = nums[nums.length - 1];
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                ans += prev;
                ans %= mod;
                continue;
            }
            prev = 0;
            int start = i;
            for (int num = 1; num <= maxnum / nums[i]; num++) {
                if (nums[start] / nums[i] > num) {  // 如果无法找到商为num的数
                    continue;
                }
                int end = bisect(num, start, nums[i]);
                prev += (((long) (end - start + 1)) * num) % mod;
                start = end + 1;
            }
            ans += prev;
            ans %= mod;
        }
        return (int) ans;
    }

    // 返回[start,nums.length-1]最右侧除以nums[i]得到target的索引
    private int bisect(int target, int start, int divisor) {
        int left = start, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (nums[mid] / divisor <= target) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return right;
    }
}
