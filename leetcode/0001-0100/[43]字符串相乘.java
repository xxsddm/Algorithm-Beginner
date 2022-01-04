//给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。 
//
// 示例 1: 
//
// 输入: num1 = "2", num2 = "3"
//输出: "6" 
//
// 示例 2: 
//
// 输入: num1 = "123", num2 = "456"
//输出: "56088" 
//
// 说明： 
//
// 
// num1 和 num2 的长度小于110。 
// num1 和 num2 只包含数字 0-9。 
// num1 和 num2 均不以零开头，除非是数字 0 本身。 
// 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。 
// 
// Related Topics 数学 字符串 模拟 👍 713 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        // 低位(个位)在后, 高位在前
        int[] res = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            int n1 = num1.charAt(i) - '0';
            for (int j = num2.length() - 1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0';
                int sum = (res[i + j + 1] + n1 * n2);
                res[i + j + 1] = sum % 10;
                res[i + j] += sum / 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            // 最高位单独考虑
            if (i == 0 && res[i] == 0) {
                continue;
            }
            sb.append(res[i]);
        }
        return sb.toString();
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        if (num1.length() > num2.length()) {
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }
        int length = num1.length();
        String cumsum = "0";
        String container;
        for (int i = 0; i < length; i++) {
            // 用较短字符串末位开始与较长字符串相乘
            int num = num1.charAt(length - 1 - i) - '0';
            // 构建乘积字符串
            StringBuilder sb = new StringBuilder(num2.length() + 1);
            for (int j = 0; j < i; j++) {
                sb.append('0');
            }
            int prev = 0, idx = num2.length() - 1;
            while (idx >= 0 || prev > 0) {
                int temp = prev + (idx >= 0 ? num2.charAt(idx--) - '0' : 0) * num;
                prev = temp / 10;
                sb.append((char) (temp % 10 + '0'));
            }
            container = sb.reverse().toString();
            // 字符串相加
            sb = new StringBuilder(num2.length() + 1);
            int idx1 = cumsum.length() - 1, idx2 = container.length() - 1;
            while (idx1 >= 0 || idx2 >= 0 || prev > 0) {
                int temp = prev
                        + (idx1 >= 0 ? cumsum.charAt(idx1--) - '0' : 0)
                        + (idx2 >= 0 ? container.charAt(idx2--) - '0' : 0);
                prev = temp / 10;
                sb.append((char) (temp % 10 + '0'));
            }
            cumsum = sb.reverse().toString();
        }
        return cumsum;
    }
}
