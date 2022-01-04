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
// Related Topics 数学 字符串 模拟 👍 714 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    string multiply(string num1, string num2) {
        if (num1 == "0" || num2 == "0") {
            return "0";
        }
        // 低位(个位)在后, 高位在前
        vector<int> res(num1.size() + num2.size(), 0);
        for (int i = num1.size()- 1; i >= 0; i--) {
            int n1 = num1[i] - '0';
            for (int j = num2.size() - 1; j >= 0; j--) {
                int n2 = num2[j] - '0';
                int sum = (res[i + j + 1] + n1 * n2);
                res[i + j + 1] = sum % 10;
                res[i + j] += sum / 10;
            }
        }

        string ans;
        for (int i = 0; i < res.size(); i++) {
            // 最高位单独考虑
            if (i == 0 && res[i] == 0) {
                continue;
            }
            ans += res[i];  // to_string(res[i]);
        }
        return ans;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
