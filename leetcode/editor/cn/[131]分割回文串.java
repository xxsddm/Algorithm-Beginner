//给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。 
//
// 回文串 是正着读和反着读都一样的字符串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "aab"
//输出：[["a","a","b"],["aa","b"]]
// 
//
// 示例 2： 
//
// 
//输入：s = "a"
//输出：[["a"]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 16 
// s 仅由小写英文字母组成 
// 
// Related Topics 字符串 动态规划 回溯 👍 808 👎 0


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<String>> container = new ArrayList<>();
    LinkedList<String> sublist = new LinkedList<>();
    boolean[][] judge;
    String target;

    public List<List<String>> partition(String s) {     // 回溯, 利用DP保存子串是否回文, 更快更占空间
        judge = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            judge[i][i] = true;
        }
        target = s;
        for (int start = s.length() - 1; start >= 0; start--) {
            for (int end = start; end < s.length() - 1; end++) {
                judge[start][end + 1] = (start + 1 >= end || judge[start + 1][end]) && s.charAt(start) == s.charAt(end + 1);
            }
        }
        backtrack(0);
        return container;
    }

    private void backtrack(int start) {
        if (start == target.length()) {
            container.add(new ArrayList<>(sublist));
            return;
        }
        for (int end = start; end < target.length(); end++) {
            if (judge[start][end]) {
                sublist.add(target.substring(start, end + 1));
                backtrack(end + 1);
                sublist.removeLast();
            }
        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    List<List<String>> container = new ArrayList<>();
    LinkedList<String> sublist = new LinkedList<>();
    String target;

    public List<List<String>> partition(String s) {     // 回溯, 无记忆化, 节省空间
        target = s;
        backtrack(0);
        return container;
    }

    private void backtrack(int start) {
        if (start == target.length()) {
            container.add(new ArrayList<>(sublist));
            return;
        }
        for (int end = start; end < target.length(); end++) {
            if (judge(start, end)) {
                sublist.add(target.substring(start, end + 1));
                backtrack(end + 1);
                sublist.removeLast();
            }
        }
    }

    private boolean judge(int start, int end) {
        for (int i = start; i <= (start + end) >> 1; i++) {
            if (target.charAt(i) != target.charAt(end + start - i)) {
                return false;
            }
        }
        return true;
    }
}
