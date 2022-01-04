//给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 13
//输出：6
// 
//
// 示例 2： 
//
// 
//输入：n = 0
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= n <= 2 * 109 
// 
// Related Topics 递归 数学 动态规划 
// 👍 237 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int countDigitOne(int n) {
        int temp = n, count = 0, time = 0, num;
        while (temp > 0) {
            num = temp / 10 + 1;
            if (temp % 10 > 1) {
                num *= Math.pow(10, time);
            }
            else {
                num = (int) ((temp % 10) * (n % Math.pow(10, time) + 1) + (num - 1) * Math.pow(10, time));
            }
            count += num;
            temp /= 10;
            time++;
        }
        return count;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
