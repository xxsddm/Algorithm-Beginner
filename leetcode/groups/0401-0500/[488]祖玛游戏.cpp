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
private:
    int ans = 10;
    int length;
    int total;
    int status;
    vector<int> counter;

    static int8_t color2Idx(char c) {
        vector<char> colors = {'B', 'G', 'R', 'W', 'Y'};
        for (int8_t i = 1; i < 5; i++) {
            if (c == colors[i - 1]) {
                return i;
            }
        }
        return 5;
    }

    void backtrack(int numUsed, vector<int8_t> balls) {
        if (numUsed == ans) {
            return;
        }
        if (status == 0) {
            ans = numUsed;
            return;
        }
        if (numUsed == ans - 1 || numUsed == total) {
            return;
        }
        int temp = status;
        for (int i = 0; i < length; i++) {
            // 考虑在空位点插入(仅考虑与球相邻的间隙)
            if (balls[i] == 0 && (i > 0 && balls[i - 1] > 0
                                  || i < length - 1 && balls[i + 1] > 0)) {
                for (int8_t color = 1; color <= 5; color++) {
                    if (counter[color] == 0) {
                        continue;
                    }
                    counter[color]--;
                    balls[i] = color;
                    status |= 1 << i;
                    backtrack(numUsed + 1, merge(balls, i, i));
                    status = temp;
                    counter[color]++;
                    balls[i] = 0;
                }
            }
        }
    }

    vector<int8_t> merge(vector<int8_t>& balls, int left, int right) {
        vector<int8_t> container(balls);
        if (left < 0 || right >= length) {
            return container;
        }
        int8_t color = container[left];
        if (container[right] != color) {
            return container;
        }
        // 记录可能消除的元素索引
        queue<int> idxs;
        idxs.push(left);
        if (left != right) {
            idxs.push(right);
        }
        left--;
        right++;
        while (left >= 0) {
            if (container[left] == 0) {
                left--;
            }
            else if (container[left] == color) {
                idxs.push(left--);
            }
            else {
                break;
            }
        }
        while (right < length) {
            if (container[right] == 0) {
                right++;
            }
            else if (container[right] == color) {
                idxs.push(right++);
            }
            else {
                break;
            }
        }
        // left+1~right-1重复达到3次, 递归消除
        if (idxs.size() >= 3) {
            while (!idxs.empty()) {
                int idx = idxs.front();
                idxs.pop();
                container[idx] = 0;
                status ^= 1 << idx;
            }
            return merge(container, left, right);
        }
        return container;
    }

public:
    int findMinStep(string board, string hand) {
        length = max((board.size() << 1) - 1, board.size() + hand.size());
        total = hand.size();
        counter = vector<int> (6);
        vector<int8_t> balls(length);
        for (int i = 0; i < board.length(); i++) {
            status |= 1 << (i << 1);
            balls[i << 1] = color2Idx((char) board[i]);
        }
        for (char i : hand) {
            counter[color2Idx(i)]++;
        }
        backtrack(0, balls);
        return ans == 10 ? -1 : ans;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
