//几乎每一个人都用 乘法表。但是你能在乘法表中快速找到第k小的数字吗？ 
//
// 给定高度m 、宽度n 的一张 m * n的乘法表，以及正整数k，你需要返回表中第k 小的数字。 
//
// 例 1： 
//
// 
//输入: m = 3, n = 3, k = 5
//输出: 3
//解释: 
//乘法表:
//1	2	3
//2	4	6
//3	6	9
//
//第5小的数字是 3 (1, 2, 2, 3, 3).
// 
//
// 例 2： 
//
// 
//输入: m = 2, n = 3, k = 6
//输出: 6
//解释: 
//乘法表:
//1	2	3
//2	4	6
//
//第6小的数字是 6 (1, 2, 2, 3, 4, 6).
// 
//
// 注意： 
//
// 
// m 和 n 的范围在 [1, 30000] 之间。 
// k 的范围在 [1, m * n] 之间。 
// 
// Related Topics 二分查找 👍 152 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findKthNumber(int m, int n, int k) { // 二分
        if (m > n) {
            int temp = m;
            m = n;
            n = temp;
        }
        int left = 1, right = m * n;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (check(mid, m, n, k)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    // 检查num是否可以大于等于k-1个数
    boolean check(int num, int m, int n, int k) {
        int count = 0;
        // 枚举第一个乘数i=[1,m],则num/i为第二个乘数的取值上限
        // 即对于i,有1~num/i,共num/i个数小于等于num
        for (int i = 1; i <= m && num / i > 0; i++) {
            count += Math.min(num / i, n);
            if (count >= k) {
                return true;
            }
        }
        return false;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
