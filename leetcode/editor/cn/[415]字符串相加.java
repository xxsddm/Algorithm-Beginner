//给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。 
//
// 
//
// 提示： 
//
// 
// num1 和num2 的长度都小于 5100 
// num1 和num2 都只包含数字 0-9 
// num1 和num2 都不包含任何前导零 
// 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式 
// 
// Related Topics 数学 字符串 模拟 👍 438 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String addStrings(String num1, String num2) {
        if (num1.length() < num2.length()) {    // num1取较长字符串
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }
        // 累和进位保存在prev
        int length1 = num1.length(), length2 = num2.length(), prev = 0;
        StringBuilder sb = new StringBuilder(length1 + 1);
        for (int i = length2 - 1; i >= 0; i--) {
            int j = length1 - length2 + i;
            int temp = num1.charAt(j) - '0' + num2.charAt(i) - '0' + prev;
            prev = temp / 10;
            temp %= 10;
            sb.append((char) (temp + '0'));
        }
        for (int i = length1 - length2 - 1; i >= 0; i--) {
            int temp = num1.charAt(i) - '0' + prev;
            prev = temp / 10;
            temp %= 10;
            sb.append((char) (temp + '0'));
        }
        if (prev > 0) {
            sb.append((char) (prev + '0'));
        }
        sb.reverse();   // 反转
        return sb.toString();
    }
}

//leetcode submit region end(Prohibit modification and deletion)
