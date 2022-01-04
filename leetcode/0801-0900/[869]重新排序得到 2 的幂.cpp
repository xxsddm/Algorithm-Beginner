//给定正整数 N ，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。 
//
// 如果我们可以通过上述方式得到 2 的幂，返回 true；否则，返回 false。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 输入：1
//输出：true
// 
//
// 示例 2： 
//
// 输入：10
//输出：false
// 
//
// 示例 3： 
//
// 输入：16
//输出：true
// 
//
// 示例 4： 
//
// 输入：24
//输出：false
// 
//
// 示例 5： 
//
// 输入：46
//输出：true
// 
//
// 
//
// 提示： 
//
// 
// 1 <= N <= 10^9 
// 
// Related Topics 数学 计数 枚举 排序 👍 116 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int length = 0;
    bool ans = false;
    vector<int> counter;

    bool reorderedPowerOf2(int n) { // 回溯
        if (n == (n & (-n))) {
            return true;
        }
        counter = vector<int>(10);
        while (n > 0) {
            counter[n % 10]++;
            n /= 10;
            length++;
        }
        backtrack(0, 0, -1);
        return ans;
    }

    void backtrack(int cumsum, int idx, int back) { // 可提前确定最后一位为偶数以剪枝
        cumsum *= 10;
        if (back == -1) {
            for (int num = 0; num < 10; num += 2) {
                if (counter[num] == 0) {
                    continue;
                }
                counter[num]--;
                backtrack(cumsum, idx, num);
                counter[num]++;
            }
            return;
        }
        if (idx == length - 1) {
            cumsum += back;
            ans = (cumsum == (cumsum & (-cumsum)));
            return;
        }
        for (int nextNum = (cumsum == 0 ? 1 : 0); nextNum < 10; nextNum++) {
            if (counter[nextNum] == 0) {
                continue;
            }
            counter[nextNum]--;
            backtrack(cumsum + nextNum, idx + 1, back);
            if (ans) {
                return;
            }
            counter[nextNum]++;
        }
    }
};

//leetcode submit region end(Prohibit modification and deletion)
