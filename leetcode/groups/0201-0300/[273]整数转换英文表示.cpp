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
// Related Topics 递归 数学 字符串 👍 177 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int length;
    string target;
    string ans;
    vector <string> map13 = {"", "One ", "Two ", "Three ", "Four ",
                             "Five ", "Six ", "Seven ", "Eight ", "Nine "};
    vector <string> map1x = {"Ten ", "Eleven ", "Twelve ", "Thirteen ", "Fourteen ",
                             "Fifteen ", "Sixteen ", "Seventeen ", "Eighteen ", "Nineteen "};
    vector <string> map2 = {"", "", "Twenty ", "Thirty ", "Forty ",
                            "Fifty ", "Sixty ", "Seventy ", "Eighty ", "Ninety "};
    vector <string> suffix = {"", "Thousand ", "Million ", "Billion "};

    string numberToWords(int num) {     // 解决3位以内数字表示,则可直接得到答案
        if (num == 0) {
            return "Zero";
        }
        target = to_string(num);
        reverse(target.begin(), target.end());
        length = (int) target.length();

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

        ans.erase((int) ans.length() - 1);
        return ans;
    }

    void transform(int start) {
        int prevLength = (int) ans.length();
        int subLength = min(length - 1, start + 2) - start + 1;
        if (subLength >= 3) {
            int idx = target[start + 2] - '0';
            if (idx > 0) {
                ans.append(map13[idx]);
                ans.append("Hundred ");
            }
        }
        if (subLength >= 2) {
            if (target[start + 1] == '1') {
                ans.append(map1x[target[start] - '0']);
                ans.append(suffix[start / 3]);
                return;
            }
            ans.append(map2[target[start + 1] - '0']);
        }
        ans.append(map13[target[start] - '0']);
        if (ans.length() == prevLength) {
            return;
        }
        ans.append(suffix[start / 3]);
    }
};

//leetcode submit region end(Prohibit modification and deletion)
