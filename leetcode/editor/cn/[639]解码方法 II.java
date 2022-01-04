//一条包含字母 A-Z 的消息通过以下的方式进行了编码： 
//
// 
//'A' -> 1
//'B' -> 2
//...
//'Z' -> 26
// 
//
// 要 解码 一条已编码的消息，所有的数字都必须分组，然后按原来的编码方案反向映射回字母（可能存在多种方式）。例如，"11106" 可以映射为： 
//
// 
// "AAJF" 对应分组 (1 1 10 6) 
// "KJF" 对应分组 (11 10 6) 
// 
//
// 注意，像 (1 11 06) 这样的分组是无效的，因为 "06" 不可以映射为 'F' ，因为 "6" 与 "06" 不同。 
//
// 除了 上面描述的数字字母映射方案，编码消息中可能包含 '*' 字符，可以表示从 '1' 到 '9' 的任一数字（不包括 '0'）。例如，编码字符串 "1*
//" 可以表示 "11"、"12"、"13"、"14"、"15"、"16"、"17"、"18" 或 "19" 中的任意一条消息。对 "1*" 进行解码，相当于解码
//该字符串可以表示的任何编码消息。 
//
// 给你一个字符串 s ，由数字和 '*' 字符组成，返回 解码 该字符串的方法 数目 。 
//
// 由于答案数目可能非常大，返回对 10⁹ + 7 取余 的结果。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "*"
//输出：9
//解释：这一条编码消息可以表示 "1"、"2"、"3"、"4"、"5"、"6"、"7"、"8" 或 "9" 中的任意一条。
//可以分别解码成字符串 "A"、"B"、"C"、"D"、"E"、"F"、"G"、"H" 和 "I" 。
//因此，"*" 总共有 9 种解码方法。
// 
//
// 示例 2： 
//
// 
//输入：s = "1*"
//输出：18
//解释：这一条编码消息可以表示 "11"、"12"、"13"、"14"、"15"、"16"、"17"、"18" 或 "19" 中的任意一条。
//每种消息都可以由 2 种方法解码（例如，"11" 可以解码成 "AA" 或 "K"）。
//因此，"1*" 共有 9 * 2 = 18 种解码方法。
// 
//
// 示例 3： 
//
// 
//输入：s = "2*"
//输出：15
//解释：这一条编码消息可以表示 "21"、"22"、"23"、"24"、"25"、"26"、"27"、"28" 或 "29" 中的任意一条。
//"21"、"22"、"23"、"24"、"25" 和 "26" 由 2 种解码方法，但 "27"、"28" 和 "29" 仅有 1 种解码方法。
//因此，"2*" 共有 (6 * 2) + (3 * 1) = 12 + 3 = 15 种解码方法。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10⁵ 
// s[i] 是 0 - 9 中的一位数字或字符 '*' 
// 
// Related Topics 字符串 动态规划 👍 108 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    final int mod = 1000000007;

    public int numDecodings(String s) { // DP
        if (s.charAt(0) == '0') {
            return 0;
        }
        if (s.length() == 1) {
            return s.charAt(0) == '*' ? 9 : 1;
        }
        // 初始化
        long[] dp = new long[2];
        if (s.charAt(0) != '*') {
            dp[0] = 1;
            if (s.charAt(1) != '*') {
                dp[1] = (s.charAt(1) == '0' ? 0 : 1);
                if (s.charAt(0) == '1' || s.charAt(0) == '2' && s.charAt(1) <= '6') {
                    dp[1]++;
                }
            }
            else {
                if (s.charAt(0) == '1') {
                    dp[1] = 9 + 9;
                }
                else if (s.charAt(0) == '2') {
                    dp[1] = 9 + 6;
                }
                else {
                    dp[1] = 9;
                }
            }
        }
        else {
            dp[0] = 9;
            if (s.charAt(1) != '*') {
                dp[1] = (s.charAt(1) == '0' ? 0 : 1) * 9 + (s.charAt(1) <= '6' ? 2 : 1);
            }
            else {
                dp[1] = 9 * 9 + 9 + 6;
            }
        }
        for (int i = 2; i < s.length(); i++) {
            calculate(dp, i, s);
        }
        return (int) dp[1];
    }

    // dp[0]:0~idx-2组合数;dp[1]:0~idx-1组合数
    private void calculate(long[] dp, int idx, String s) {
        long count;
        char prev = s.charAt(idx - 1), temp = s.charAt(idx);
        if (temp == '*') {
            count = (dp[1] * 9) % mod;  // idx单独作为元素
            // idx和idx-1作为两位数
            if (prev == '1') {
                count = (count + (dp[0] * 9) % mod) % mod;
            }
            else if (prev == '2') {
                count = (count + (dp[0] * 6) % mod) % mod;
            }
            else if (prev == '*') {
                count = (count + (dp[0] * 15) % mod) % mod;
            }
        }
        else {
            count = temp == '0' ? 0 : dp[1];   // idx单独作为元素
            // idx和idx-1作为两位数
            if (prev == '1') {
                count = (count + dp[0]) % mod;
            }
            else if (prev == '2' && temp <= '6') {
                count = (count + dp[0]) % mod;
            }
            else if (prev == '*') {
                count = (count + (dp[0] * (temp <= '6' ? 2 : 1)) % mod) % mod;
            }
        }
        dp[0] = dp[1];
        dp[1] = count;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
