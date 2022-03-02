//给定一个表示整数的字符串 n ，返回与它最近的回文整数（不包括自身）。如果不止一个，返回较小的那个。 
//
// “最近的”定义为两个整数差的绝对值最小。 
//
// 
//
// 示例 1: 
//
// 
//输入: n = "123"
//输出: "121"
// 
//
// 示例 2: 
//
// 
//输入: n = "1"
//输出: "0"
//解释: 0 和 2是最近的回文，但我们返回最小的，也就是 0。
// 
//
// 
//
// 提示: 
//
// 
// 1 <= n.length <= 18 
// n 只由数字组成 
// n 不含前导 0 
// n 代表在 [1, 10¹⁸ - 1] 范围内的整数 
// 
// Related Topics 数学 字符串 👍 202 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String nearestPalindromic(String n) {
        StringBuilder sb = new StringBuilder(n);
        if (n.length() == 1) {
            sb.setCharAt(0, (char) (sb.charAt(0) - 1));
            return sb.toString();
        }
        for (int left = 0, right = n.length() - 1; left < right; left++, right--) {
            sb.setCharAt(right, sb.charAt(left));
        }
        String word = sb.toString(), num1, num2;
        long num0 = Long.parseLong(n), num = Long.parseLong(word);
        if (num < num0) {
            num1 = word;
            num2 = up(word);
        } else if (num > num0) {
            num1 = down(word);
            num2 = word;
        } else {
            num1 = down(word);
            num2 = up(word);
        }
        long numDown = Long.parseLong(num1), numUp = Long.parseLong(num2);
        if (numUp - num0 < num0 - numDown) {
            return num2;
        }
        return num1;
    }

    String down(String word) {
        int length = word.length();
        StringBuilder sb = new StringBuilder();
        if (word.charAt(0) == '1') {
            boolean all0 = true;
            for (int i = 1, limit = (length - 1) >> 1; i <= limit; i++) {
                if (word.charAt(i) != '0') {
                    all0 = false;
                    break;
                }
            }
            if (all0) {
                for (int i = 1; i < length; i++) {
                    sb.append('9');
                }
                return sb.toString();
            }
        }
        sb.append(word);
        for (int left = (length - 1) >> 1; left >= 0; left--) {
            if (sb.charAt(left) != '0') {
                sb.setCharAt(left, (char) (sb.charAt(left) - 1));
                if (left != length - 1 - left) {
                    sb.setCharAt(length - 1 - left, sb.charAt(left));
                }
                break;
            }
        }
        return sb.toString();
    }

    String up(String word) {
        int length = word.length();
        StringBuilder sb = new StringBuilder();
        boolean all9 = true;
        for (int i = 0, limit = (length - 1) >> 1; i <= limit; i++) {
            if (word.charAt(i) != '9') {
                all9 = false;
                break;
            }
        }
        if (all9) {
            sb.append('1');
            for (int i = 1; i < length; i++) {
                sb.append('0');
            }
            sb.append('1');
            return sb.toString();
        }
        sb.append(word);
        for (int left = (length - 1) >> 1; left >= 0; left--) {
            if (sb.charAt(left) != '9') {
                sb.setCharAt(left, (char) (sb.charAt(left) + 1));
                if (left != length - 1 - left) {
                    sb.setCharAt(length - 1 - left, sb.charAt(left));
                }
                break;
            }
        }
        return sb.toString();
    }
}

//leetcode submit region end(Prohibit modification and deletion)
