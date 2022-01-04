//给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。 
//
// 
//
// 示例： 
//
// 输入: "sea", "eat"
//输出: 2
//解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
// 
//
// 
//
// 提示： 
//
// 
// 给定单词的长度不超过500。 
// 给定单词中的字符只含有小写字母。 
// 
// Related Topics 字符串 动态规划 👍 219 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minDistance(String word1, String word2) {    // DP(空间优化, 观察原版可得)
        if (word1.length() > word2.length()) {
            String temp = word1;
            word1 = word2;
            word2 = temp;
        }

        int[] dp = new int[word1.length() + 1];

        // 类比原版初始化时j=0时
        for (int i = 0; i < word1.length() + 1; i++) {
            dp[i] = i;
        }

        // 观察原版可得(不能改为从后向前遍历)
        for (int j = 0; j < word2.length(); j++) {
            int[] temp = new int[word1.length() + 1];
            temp[0] = j + 1;    // 即word1尚未考虑元素, word2考虑0~j共j+1元素
            for (int i = 0; i < word1.length(); i++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    temp[i + 1] = dp[i];
                }
                else {
                    temp[i + 1] = Math.min(temp[i], dp[i + 1]) + 1;
                }
            }
            dp = temp;
        }
        return dp[word1.length()];
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    public int minDistance(String word1, String word2) {    // DP
        // 考虑前i+1, j+1元素中, 不相同元素(差集)数量
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        // 初始化
        for (int i = 0; i < word1.length() + 1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < word2.length() + 1; j++) {
            dp[0][j] = j;
        }

        for (int i = 0; i < word1.length(); i++) {
            for (int j = 0; j < word2.length(); j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j];
                }
                else {
                    // 取差集数量较小的一边+1 (选择更小的一方可以保证存在+1关系)
                    // i+1!=j+1但可能i+1=j/i=j+1, 即新引进的可能和前面元素相同
                    dp[i + 1][j + 1] = Math.min(dp[i + 1][j], dp[i][j + 1]) + 1;
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
}


class Solution {
    public int minDistance(String word1, String word2) {    // DP找到最大子公共序列(1143)
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i < word1.length(); i++) {
            for (int j = 0; j < word2.length(); j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                }
                else {
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        return word1.length() + word2.length() - 2 * dp[word1.length()][word2.length()];
    }
}
