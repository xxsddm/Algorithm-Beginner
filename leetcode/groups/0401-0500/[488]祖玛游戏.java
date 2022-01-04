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

import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int ans = 100, length, total, rest;
    int[] status;
    int[] counterHand = new int[5], counterBoard = new int[5];

    public int findMinStep(String board, String hand) {
        length = board.length() + Math.max(board.length() - 1, hand.length());
        total = hand.length();
        rest = board.length();
        status = new int[length];
        for (int i = 0; i < board.length(); i++) {
            counterBoard[color2Idx(board.charAt(i)) - 1]++;
            status[i << 1] = color2Idx(board.charAt(i));
        }
        for (int i = 0; i < total; i++) {
            counterHand[color2Idx(hand.charAt(i)) - 1]++;
        }
        backtrack(0);
        return ans == 100 ? -1 : ans;
    }

    int color2Idx(char letter) {
        String s = "BGRW";
        for (int i = 0; i < 4; i++) {
            if (letter == s.charAt(i)) {
                return i + 1;
            }
        }
        return 5;
    }

    void backtrack(int numUsed) {
        if (numUsed == ans) {
            return;
        }
        if (rest == 0) {
            ans = numUsed;
            return;
        }
        if (numUsed == ans - 1 || numUsed == total) {
            return;
        }
        for (int color = 1; color <= 5; color++) {
            if (counterBoard[color - 1] > 0 && counterBoard[color - 1] + counterHand[color - 1] < 3) {
                return;
            }
        }
        int last = -1;
        for (int i = 0; i < length; i++) {
            if ((i + 1 < length && status[i + 1] != 0 || i == length - 1) && status[i] == 0) {
                for (int color = 1; color <= 5; color++) {
                    if (counterHand[color - 1] == 0) {
                        continue;
                    }
                    if (last == color) {
                        boolean skip = false;
                        for (int j = i - 1; j >= 0; j--) {
                            if (status[j] != 0 && status[j] != last) {
                                break;
                            } else if (status[j] == last && status[j - 1] == 0) {
                                skip = true;
                            }
                        }
                        if (skip) {
                            continue;
                        }
                    }
                    if (i + 1 < length) {
                        last = status[i + 1];
                    }
                    int tempRest = rest++;
                    int[] tempCounterBoard = counterBoard.clone();

                    counterHand[color - 1]--;
                    counterBoard[color - 1]++;
                    status[i] = color;
                    List<int[]> back = merge(i);
                    backtrack(numUsed + 1);

                    counterHand[color - 1]++;
                    rest = tempRest;
                    counterBoard = tempCounterBoard;
                    for (int[] idxAndColor: back) {
                        status[idxAndColor[0]] = idxAndColor[1];
                    }
                    status[i] = 0;
                }
            }
        }
    }

    List<int[]> merge(int idx) {
        int left = idx, right = idx;
        // 优先以左侧元素为准
        boolean tryRight = false, first = true;
        LinkedList<int[]> ans = new LinkedList<>();
        while (true) {
            if (left < 0) {
                tryRight = true;
            }
            if (tryRight && right == length) {
                return ans;
            }
            int tempLeft = left, color = tryRight ? status[right] : status[left];
            if (color == 0) {
                return ans;
            }
            tryRight |= (left >= 0 && right < length && status[left] == status[right]);
            LinkedList<Integer> changing = new LinkedList<>();
            while (left >= 0) {
                if (status[left] == color) {
                    changing.add(left--);
                } else if (status[left] == 0) {
                    left--;
                } else {
                    break;
                }
            }
            while (right < length) {
                if (status[right] == color) {
                    changing.add(right++);
                } else if (status[right] == 0) {
                    right++;
                } else {
                    break;
                }
            }
            if (first) {
                changing.pollFirst();
            }
            if (changing.size() < 3) {
                if (tryRight || first) {
                    return ans;
                } else {
                    left = tempLeft;
                    tryRight = true;
                    continue;
                }
            }
            tryRight = false;
            first = false;
            rest -= changing.size();
            for (int loc: changing) {
                ans.add(new int[] {loc, color});
                counterBoard[color - 1]--;
                status[loc] = 0;
            }
        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)

class Solution {
    int ans = 10;
    int length;
    int total;
    int status;
    int[] counter;

    public int findMinStep(String board, String hand) { // 回溯(位运算表示当前球的位置)
        // 可以排除两端插入的情况, 这种情况可以被其他表示, 故可以考虑位运算
        // 加入间隙, 但board可能很短, 需额外考虑
        length = Math.max((board.length() << 1) - 1, board.length() + hand.length());
        total = hand.length();
        counter = new int[6];   // 0代表间隙, 其余代表颜色
        byte[] balls = new byte[length];
        for (int i = 0; i < board.length(); i++) {
            status |= 1 << (i << 1);
            balls[i << 1] = color2Idx(board.charAt(i));
        }
        for (int i = 0; i < hand.length(); i++) {
            counter[color2Idx(hand.charAt(i))]++;
        }
        backtrack(0, balls);
        return ans == 10 ? -1 : ans;
    }

    private void backtrack(int numUsed, byte[] balls) {
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
                for (byte color = 1; color <= 5; color++) {
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

    public byte[] merge(byte[] balls, int left, int right) {
        byte[] container = balls.clone();
        if (left < 0 || right >= length) {
            return container;
        }
        byte color = container[left];
        if (container[right] != color) {
            return container;
        }
        // 记录可能消除的元素索引
        LinkedList<Integer> idxs = new LinkedList<>();
        idxs.add(left);
        if (left != right) {
            idxs.add(right);
        }
        left--;
        right++;
        while (left >= 0) {
            if (container[left] == 0) {
                left--;
            }
            else if (container[left] == color) {
                idxs.add(left--);
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
                idxs.add(right++);
            }
            else {
                break;
            }
        }
        // left+1~right-1重复达到3次, 递归消除
        if (idxs.size() >= 3) {
            for (int idx: idxs) {
                container[idx] = 0;
                status ^= 1 << idx;
            }
            return merge(container, left, right);
        }
        return container;
    }

    private byte color2Idx(char c) {
        char[] colors = {'B', 'G', 'R', 'W', 'Y'};
        for (byte i = 1; i < 5; i++) {
            if (c == colors[i - 1]) {
                return i;
            }
        }
        return 5;
    }
}

class Solution {
    int total, ans;

    public int findMinStep(String board, String hand) { // 回溯(疯狂剪枝, merge部分写得不好, 很慢)
        total = hand.length();
        ans = Integer.MAX_VALUE;

        ArrayList<Byte> balls = new ArrayList<>(board.length());
        for (int i = 0; i < board.length(); i++) {
            balls.add(color2Idx(board.charAt(i)));
        }

        int[] counter = new int[5]; // 'B','G','R','W','Y' 球的颜色用索引代替
        for (int i = 0; i < hand.length(); i++) {
            counter[color2Idx(hand.charAt(i))]++;
        }

        backtrack(balls, counter, 0);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private void backtrack(ArrayList<Byte> balls, int[] counter, int numUsed) {
        if (numUsed == ans) {   // 补充的剪枝, 可能较大结果在后面判断之前就修改了ans
            return;
        }
        if (balls.isEmpty()) {
            ans = numUsed;
            return;
        }
        if (numUsed == ans - 1 || numUsed == total) {   // 剪枝
            return;
        }
        for (int i = 0; i <= balls.size(); i++) {
            for (byte color = 0; color < 5; color++) {
                if (counter[color] == 0) {
                    continue;
                }
                counter[color]--;
                // 不用真的加进去, 合并的时候作为参数输入插入位置和颜色即可
                backtrack(merge(balls, i, color), counter, numUsed + 1);
                counter[color]++;
            }
        }
    }

    private ArrayList<Byte> merge(ArrayList<Byte> balls, int loc, byte color) { // 这里没有修改输入balls
        ArrayList<Byte> container = new ArrayList<>(balls.size() + 1);
        // 插入点前(如果之前步骤合并正确, 插入点前不可能有可合并区域)
        for (int i = 0; i < loc; i++) {
            container.add(balls.get(i));
        }
        // 插入点(从插入点开始考虑合并区域)
        if (!container.isEmpty() && container.get(container.size() - 1) != color) {
            removeBall(container);
        }
        container.add(color);
        // 插入点后
        for (int i = loc; i < balls.size(); i++) {
            // 不相同则考虑合并前面的区域
            if (!container.isEmpty() && container.get(container.size() - 1) != balls.get(i)) {
                removeBall(container);
            }
            container.add(balls.get(i));
        }

        removeBall(container);
        return container;
    }

    private void removeBall(List<Byte> container) {
        if (container.size() < 3) {
            return;
        }
        byte temp = container.get(container.size() - 1);
        if (container.get(container.size() - 2) != temp
                || container.get(container.size() - 3) != temp) {
            return;
        }
        int idx = container.size() - 4, count = 3;
        while (idx >= 0 && container.get(idx) == temp) {
            count++;
            idx--;
        }
        // 消除
        for (int j = 0; j < count; j++) {
            container.remove(container.size() - 1);
        }
    }

    private byte color2Idx(char c) {
        char[] colors = {'B', 'G', 'R', 'W', 'Y'};
        for (byte i = 0; i < 4; i++) {
            if (c == colors[i]) {
                return i;
            }
        }
        return 4;
    }
}
