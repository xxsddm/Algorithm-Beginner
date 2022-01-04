//给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。 
//
// 返回被除数 dividend 除以除数 divisor 得到的商。 
//
// 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2 
//
// 
//
// 示例 1: 
//
// 输入: dividend = 10, divisor = 3
//输出: 3
//解释: 10/3 = truncate(3.33333..) = truncate(3) = 3 
//
// 示例 2: 
//
// 输入: dividend = 7, divisor = -3
//输出: -2
//解释: 7/-3 = truncate(-2.33333..) = -2 
//
// 
//
// 提示： 
//
// 
// 被除数和除数均为 32 位有符号整数。 
// 除数不为 0。 
// 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231, 231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。 
// 
// Related Topics 位运算 数学 
// 👍 628 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int divide(int a, int b) {
        boolean negative = false;
        if ((a > 0 && b < 0) || (a < 0 && b > 0))    negative = true;
        long temp1 = Math.abs((long) a), temp2 = Math.abs((long) b);    // 没想到不用long的办法
        int count = 31;
        long temptemp2 = temp2 << count, ans = 0;
        while (temp1 >= temp2) {
            while (temp1 < temptemp2) {
                temptemp2 >>= 1;
                count--;
            }
            temp1 -= temptemp2;
            ans += (long) 1 << count;
        }
        if (ans > Integer.MAX_VALUE && !negative)            return Integer.MAX_VALUE;
        else if (ans - 1 > Integer.MAX_VALUE && negative)    return Integer.MIN_VALUE;
        else if (negative)                                   return - (int) ans;
        else                                                 return (int) ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
