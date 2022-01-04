//有台奇怪的打印机有以下两个特殊要求： 
//
// 
// 打印机每次只能打印由 同一个字符 组成的序列。 
// 每次可以在任意起始和结束位置打印新字符，并且会覆盖掉原来已有的字符。 
// 
//
// 给你一个字符串 s ，你的任务是计算这个打印机打印它需要的最少打印次数。 
// 
//
// 示例 1： 
//
// 
//输入：s = "aaabbb"
//输出：2
//解释：首先打印 "aaa" 然后打印 "bbb"。
// 
//
// 示例 2： 
//
// 
//输入：s = "aba"
//输出：2
//解释：首先打印 "aaa" 然后在第二个位置打印 "b" 覆盖掉原来的字符 'a'。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 100 
// s 由小写英文字母组成 
// 
// Related Topics 字符串 动态规划 👍 222 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int strangePrinter(string s) {
        int length = (int) s.length();
        // left,right -> [left,right]最少操作次数
        vector<vector<int>> dp(length, vector<int>(length));
        // 初始化
        for (int i = 0; i < length; i++) {
            dp[i][i] = 1;
        }
        // 需要左区间和右区间值,故left从右往左(右区间),right从左往右(左区间)
        for (int left = length - 2; left >= 0; left--) {
            for (int right = left + 1; right < length; right++) {
                // 可白嫖right,根据dp定义已是[left,right-1]最少操作次数,不需要找切分点
                if (s[left] == s[right]) {
                    dp[left][right] = dp[left][right - 1];  // left=right则[left,right]=[left,right-1](=...)
                    continue;
                }
                // 不可白嫖right,需要找最优切分点(最多情况为[left,right-1]+[right,right])
                int temp = dp[left][left] + dp[left + 1][right], limit = dp[left][right - 1];
                for (int mid = left + 1; mid < right && temp > limit; mid++) {  // temp达到下界limit则可终止
                    temp = min(temp, dp[left][mid] + dp[mid + 1][right]);
                }
                dp[left][right] = temp;
            }
        }
        return dp[0][length - 1];
    }
};

//leetcode submit region end(Prohibit modification and deletion)
