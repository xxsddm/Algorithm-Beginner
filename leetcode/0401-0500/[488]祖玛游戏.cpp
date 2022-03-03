//回忆一下祖玛游戏。现在桌上有一串球，颜色有红色(R)，黄色(Y)，蓝色(B)，绿色(G)，还有白色(W)。 现在你手里也有几个球。
//
// 每一次，你可以从手里的球选一个，然后把这个球插入到一串球中的某个位置上（包括最左端，最右端）。接着，如果有出现三个或者三个以上颜色相同的球相连的话，就把它
//们移除掉。重复这一步骤直到桌上所有的球都被移除。
//
// 找到插入并可以移除掉桌上所有球所需的最少的球数。如果不能移除桌上所有的球，输出 -1 。
//
//
//
// 示例 1：
//
//
//输入：board = "WRRBBW", hand = "RB"
//输出：-1
//解释：WRRBBW -> WRR[R]BBW -> WBBW -> WBB[B]W -> WW
//
//
// 示例 2：
//
//
//输入：board = "WWRRBBWW", hand = "WRBRW"
//输出：2
//解释：WWRRBBWW -> WWRR[R]BBWW -> WWBBWW -> WWBB[B]WW -> WWWW -> empty
//
//
// 示例 3：
//
//
//输入：board = "G", hand = "GGGGG"
//输出：2
//解释：G -> G[G] -> GG[G] -> empty
//
//
// 示例 4：
//
//
//输入：board = "RBYYBBRRB", hand = "YRBGB"
//输出：3
//解释：RBYYBBRRB -> RBYY[Y]BBRRB -> RBBBRRB -> RRRB -> B -> B[B] -> BB[B] ->
//empty
//
//
//
//
// 提示：
//
//
// 你可以假设桌上一开始的球中，不会有三个及三个以上颜色相同且连着的球。
// 1 <= board.length <= 16
// 1 <= hand.length <= 5
// 输入的两个字符串均为非空字符串，且只包含字符 'R','Y','B','G','W'。
//
// Related Topics 字符串 回溯 👍 88 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int ans = 10, mod = 100007, used[150008];

    int findMinStep(string &board, string &hand) { // DFS(BFS未通过)
        long long status = 0;   // 二进制3位数表示一个球, 最多需要63位需long long
        int rest = 0, counter[5];   // 十进制每位表示一种可使用球的数量
        memset(counter, 0, sizeof(counter));
        memset(used, 0, sizeof(used));
        for (int i = (int) board.size() - 1; i >= 0; i--) {
            status <<= 3;
            status ^= word2idx(board[i]);
        }
        for (char &c : hand) {
            counter[word2idx(c) - 1]++;
        }
        for (int i = 4; i >= 0; i--) {
            rest *= 10;
            rest += counter[i];
        }
        dfs(status, rest, 0);
        return ans == 10 ? -1 : ans;
    }

    int dfs(long long cur, int container, int num) {
        if (num >= ans - 1) {
            return 10;
        }
        int key = cur % mod + container;
        if (used[key]) {
            return used[key];
        }
        int res = 10, count = 0;
        long long prev = 0, left = 0;
        while (cur) {
            int back = container, base = 1;
            long long next = cur & 7, node = 1;
            while (back) {
                // 若当前球与前面球相同则已访问, 剪枝
                // 若当前球与后方相同或前后相同, 则可递归
                if (back % 10 && prev != node && (node == next || prev == next)) {
                    long long nextStatus = left ^ (node << (3 * count)) ^ (cur << (3 * (count + 1)));
                    nextStatus = merge(nextStatus);
                    if (!nextStatus) {
                        used[key] = 1;
                        ans = num + 1;
                        return 1;
                    }
                    int nextRest = container - base;
                    res = min(res, dfs(nextStatus, nextRest, num + 1) + 1);
                }
                node++;
                base *= 10;
                back /= 10;
            }
            prev = next;
            left ^= next << (3 * count++);
            cur >>= 3;
        }
        used[key] = res;
        return res;
    }

    long long merge(long long &num) {
        stack<pair<long long, int>> nums;
        long long ans = 0;
        while (num) {
            long long nextNum = num & 7;
            num >>= 3;
            if (nums.empty()) {
                nums.emplace(nextNum, 1);
                continue;
            }
            if (nextNum == nums.top().first) {
                nums.top().second++;
            } else {
                if (nums.top().second >= 3) {
                    nums.pop();
                    if (!nums.empty() && nextNum == nums.top().first) {
                        nums.top().second++;
                    } else {
                        nums.emplace(nextNum, 1);
                    }
                } else {
                    nums.emplace(nextNum, 1);
                }
            }
        }
        if (!nums.empty() && nums.top().second >= 3) {
            nums.pop();
        }
        while (!nums.empty()) {
            long long temp = nums.top().first;
            int size = nums.top().second;
            nums.pop();
            for (int i = 0; i < size; i++) {
                ans <<= 3;
                ans ^= temp;
            }
        }
        return ans;
    }

    int word2idx(char &c) {
        string word = "BGRW";
        for (int i = 0; i < 4; i++) {
            if (word[i] == c) {
                return i + 1;
            }
        }
        return 5;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
