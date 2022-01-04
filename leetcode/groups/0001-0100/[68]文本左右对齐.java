//给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。 
//
// 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。 
//
// 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。 
//
// 文本的最后一行应为左对齐，且单词之间不插入额外的空格。 
//
// 说明: 
//
// 
// 单词是指由非空格字符组成的字符序列。 
// 每个单词的长度大于 0，小于等于 maxWidth。 
// 输入单词数组 words 至少包含一个单词。 
// 
//
// 示例: 
//
// 输入:
//words = ["This", "is", "an", "example", "of", "text", "justification."]
//maxWidth = 16
//输出:
//[
//   "This    is    an",
//   "example  of text",
//   "justification.  "
//]
// 
//
// 示例 2: 
//
// 输入:
//words = ["What","must","be","acknowledgment","shall","be"]
//maxWidth = 16
//输出:
//[
//  "What   must   be",
//  "acknowledgment  ",
//  "shall be        "
//]
//解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
//     因为最后一行应为左对齐，而不是左右两端对齐。       
//     第二行同样为左对齐，这是因为这行只包含一个单词。
// 
//
// 示例 3: 
//
// 输入:
//words = ["Science","is","what","we","understand","well","enough","to",
//"explain",
//         "to","a","computer.","Art","is","everything","else","we","do"]
//maxWidth = 20
//输出:
//[
//  "Science  is  what we",
//  "understand      well",
//  "enough to explain to",
//  "a  computer.  Art is",
//  "everything  else  we",
//  "do                  "
//]
// 
// Related Topics 字符串 模拟 👍 152 👎 0


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int total, ans;

    public int findMinStep(String board, String hand) {
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
        if (numUsed == ans - 1) {   // 剪枝
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

//leetcode submit region end(Prohibit modification and deletion)
