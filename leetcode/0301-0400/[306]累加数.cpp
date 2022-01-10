//累加数 是一个字符串，组成它的数字可以形成累加序列。 
//
// 一个有效的 累加序列 必须 至少 包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。 
//
// 给你一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是 累加数 。如果是，返回 true ；否则，返回 false 。 
//
// 说明：累加序列里的数 不会 以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。 
//
// 
//
// 示例 1： 
//
// 
//输入："112358"
//输出：true 
//解释：累加序列为: 1, 1, 2, 3, 5, 8 。1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
// 
//
// 示例 2： 
//
// 
//输入："199100199"
//输出：true 
//解释：累加序列为: 1, 99, 100, 199。1 + 99 = 100, 99 + 100 = 199 
//
// 
//
// 提示： 
//
// 
// 1 <= num.length <= 35 
// num 仅由数字（0 - 9）组成 
// 
//
// 
//
// 进阶：你计划如何处理由过大的整数输入导致的溢出? 
// Related Topics 字符串 回溯 👍 247 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    bool isAdditiveNumber(string &num) {
        // 枚举前两个数字长度
        int length = (int) num.size(), half = (length >> 1) + (length & 1 ? 1 : 0);
        for (int len1 = 1; len1 < half; len1++) {
            for (int len2 = 1; len2 < length - len1; len2++) {
                if (length - len1 - len2 < max(len1, len2)) {
                    break;
                }
                if (check(len1, len2, num)) {
                    return true;
                }
            }
        }
        return false;
    }

    bool check(int len1, int len2, string &num) {
        if (num[0] == '0' && len1 > 1 || num[len1] == '0' && len2 > 1) {
            return false;
        }
        int idx = len1 + len2, length = (int) num.size();
        long long num1 = 0, num2 = 0;
        for (int i = 0; i < len1; i++) {
            num1 = num1 * 10 + num[i] - '0';
        }
        for (int i = len1; i < len1 + len2; i++) {
            num2 = num2 * 10 + num[i] - '0';
        }
        while (idx < length) {
            long long temp = 0, target = num1 + num2;
            if (num[idx] == '0' && target > 0) {
                return false;
            }
            while (idx < length && temp < target) {
                temp = temp * 10 + num[idx++] - '0';
            }
            if (temp != target || target == 0 && num[idx++] != '0') {
                return false;
            }
            num1 = num2;
            num2 = target;
        }
        return true;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
