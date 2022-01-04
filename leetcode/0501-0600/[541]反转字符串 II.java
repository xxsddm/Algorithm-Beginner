//给定一个字符串 s 和一个整数 k，从字符串开头算起，每 2k 个字符反转前 k 个字符。 
//
// 
// 如果剩余字符少于 k 个，则将剩余字符全部反转。 
// 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "abcdefg", k = 2
//输出："bacdfeg"
// 
//
// 示例 2： 
//
// 
//输入：s = "abcd", k = 2
//输出："bacd"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 104 
// s 仅由小写英文组成 
// 1 <= k <= 104 
// 
// Related Topics 双指针 字符串 
// 👍 138 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String reverseStr(String s, int k) {
        StringBuilder container = new StringBuilder(s.length());
        int length1 = s.length(), start = 0;
        while (start + 2 * k - 1 < length1){
            for (int i = 0; i < k; i++)    container.append(s.charAt(start + k - 1 - i));
            for (int i = k; i < 2 * k; i++)    container.append(s.charAt(start + i));
            start += 2 * k;
        }

        int length2 = container.length();
        if (length1 - length2 > k){
            for (int i = 0; i < k; i++)    container.append(s.charAt(start + k - 1 - i));
            for (int i = k; i < length1 - length2; i++)    container.append(s.charAt(start + i));
        }
        else {
            for (int i = 0; i < length1 - length2; i++)    container.append(s.charAt(start + length1 - length2 - 1 - i));
        }
        return container.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
