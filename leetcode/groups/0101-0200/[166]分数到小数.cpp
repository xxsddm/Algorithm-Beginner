//给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以 字符串形式返回小数 。 
//
// 如果小数部分为循环小数，则将循环的部分括在括号内。 
//
// 如果存在多个答案，只需返回 任意一个 。 
//
// 对于所有给定的输入，保证 答案字符串的长度小于 10⁴ 。 
//
// 
//
// 示例 1： 
//
// 
//输入：numerator = 1, denominator = 2
//输出："0.5"
// 
//
// 示例 2： 
//
// 
//输入：numerator = 2, denominator = 1
//输出："2"
// 
//
// 示例 3： 
//
// 
//输入：numerator = 2, denominator = 3
//输出："0.(6)"
// 
//
// 示例 4： 
//
// 
//输入：numerator = 4, denominator = 333
//输出："0.(012)"
// 
//
// 示例 5： 
//
// 
//输入：numerator = 1, denominator = 5
//输出："0.2"
// 
//
// 
//
// 提示： 
//
// 
// -2³¹ <= numerator, denominator <= 2³¹ - 1 
// denominator != 0 
// 
// Related Topics 哈希表 数学 字符串 👍 271 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    string fractionToDecimal(int numerator, int denominator) {
        string ans;
        long nume = abs((long) numerator), deno = abs((long) denominator);

        // 符号和整数部分
        if (numerator < 0 && denominator > 0 || numerator > 0 && denominator < 0) {
            ans.append("-");
        }
        ans.append(to_string(nume / deno));
        nume %= deno;
        if (nume == 0) {
            return ans;
        }

        // 小数部分
        ans.append(".");
        // 已有余数 -> ans索引
        unordered_map<long, int> mod2Idx;
        int idx = (int) ans.length();
        while (nume > 0) {
            // 分母相同,出现重复余数,说明出现循环
            if (mod2Idx.find(nume) != mod2Idx.end()) {
                ans.insert(mod2Idx[nume], "(");
                ans.append(")");
                return ans;
            }
            // 取余运算前保存(余数,ans索引)
            mod2Idx[nume] =  idx++;
            long temp = 10 * nume;  // 余数*10使除法运算得到idx索引小数的整数表示
            nume = temp % deno;
            ans.append(to_string(temp / deno));
        }

        return ans;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
