//将非负整数 num 转换为其对应的英文表示。 
//
// 
//
// 示例 1： 
//
// 
//输入：num = 123
//输出："One Hundred Twenty Three"
// 
//
// 示例 2： 
//
// 
//输入：num = 12345
//输出："Twelve Thousand Three Hundred Forty Five"
// 
//
// 示例 3： 
//
// 
//输入：num = 1234567
//输出："One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
// 
//
// 示例 4： 
//
// 
//输入：num = 1234567891
//输出："One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven 
//Thousand Eight Hundred Ninety One"
// 
//
// 
//
// 提示： 
//
// 
// 0 <= num <= 2³¹ - 1 
// 
// Related Topics 递归 数学 字符串 👍 170 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int length;
    String target;
    StringBuilder sb;
    String[] map13 = {"", "One ", "Two ", "Three ", "Four ",
            "Five ", "Six ", "Seven ", "Eight ", "Nine "};
    String[] map1x = {"Ten ", "Eleven ", "Twelve ", "Thirteen ", "Fourteen ",
            "Fifteen ", "Sixteen ", "Seventeen ", "Eighteen ", "Nineteen "};
    String[] map2 = {"", "", "Twenty ", "Thirty ", "Forty ",
            "Fifty ", "Sixty ", "Seventy ", "Eighty ", "Ninety "};
    String[] suffix = {"", "Thousand ", "Million ", "Billion "};

    public String numberToWords(int num) {      // 解决3位以内数字表示,则可直接得到答案
        if (num == 0) {
            return "Zero";
        }
        // num的字符串反转表示
        target = (new StringBuilder(String.valueOf(num))).reverse().toString();
        sb = new StringBuilder();
        length = target.length();

        // 每3位判断一次
        if (length > 9) {
            transform(9);
        }
        if (length > 6) {
            transform(6);
        }
        if (length > 3) {
            transform(3);
        }
        transform(0);

        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private void transform(int start) {
        int prevLength = sb.length();
        int subLength = Math.min(length - 1, start + 2) - start + 1;
        if (subLength >= 3) {
            int idx = target.charAt(start + 2) - '0';
            if (idx > 0) {
                sb.append(map13[idx]);
                sb.append("Hundred ");
            }
        }
        if (subLength >= 2) {
            if (target.charAt(start + 1) == '1') {
                sb.append(map1x[target.charAt(start) - '0']);
                sb.append(suffix[start / 3]);
                return;
            }
            sb.append(map2[target.charAt(start + 1) - '0']);
        }
        sb.append(map13[target.charAt(start) - '0']);
        if (sb.length() == prevLength) {
            return;
        }
        sb.append(suffix[start / 3]);
    }
}

//leetcode submit region end(Prohibit modification and deletion)
