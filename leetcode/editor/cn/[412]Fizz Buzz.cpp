//写一个程序，输出从 1 到 n 数字的字符串表示。 
//
// 1. 如果 n 是3的倍数，输出“Fizz”； 
//
// 2. 如果 n 是5的倍数，输出“Buzz”； 
//
// 3.如果 n 同时是3和5的倍数，输出 “FizzBuzz”。 
//
// 示例： 
//
// n = 15,
//
//返回:
//[
//    "1",
//    "2",
//    "Fizz",
//    "4",
//    "Buzz",
//    "Fizz",
//    "7",
//    "8",
//    "Fizz",
//    "Buzz",
//    "11",
//    "Fizz",
//    "13",
//    "14",
//    "FizzBuzz"
//]
// 
// Related Topics 数学 字符串 模拟 👍 113 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    vector<string> fizzBuzz(int n) {
        vector<string> ans(n);
        for (int idx = 0; idx < n; idx++) {
            int count = idx + 1;
            if (count % 3 == 0) {
                if (count % 5 == 0) {
                    ans[idx] = "FizzBuzz";
                }
                else {
                    ans[idx] = "Fizz";
                }
            }
            else if (count % 5 == 0) {
                ans[idx] = "Buzz";
            }
            else {
                ans[idx] = to_string(count);
            }
        }
        return ans;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
