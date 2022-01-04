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
public:
    string addStrings(string num1, string num2) {   // 双指针
        int idx1 = num1.size() - 1, idx2 = num2.size() - 1, prev = 0;
        string ans = "";
        while (idx1 >= 0 || idx2 >= 0 || prev != 0) {
            int temp = prev;
            if (idx1 >= 0) {
                temp += num1[idx1--] - '0';
            }
            if (idx2 >= 0) {
                temp += num2[idx2--] - '0';
            }
            ans.push_back(temp % 10 + '0');
            prev = temp / 10;
        }
        reverse(ans.begin(), ans.end());
        return ans;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
