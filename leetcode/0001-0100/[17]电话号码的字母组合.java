//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。 
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：digits = "23"
//输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
// 
//
// 示例 2： 
//
// 
//输入：digits = ""
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：digits = "2"
//输出：["a","b","c"]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= digits.length <= 4 
// digits[i] 是范围 ['2', '9'] 的一个数字。 
// 
// Related Topics 哈希表 字符串 回溯 👍 1456 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    static char[][] map = {
            {'a', 'b', 'c'}, {'d', 'e', 'f'},
            {'g', 'h', 'i'}, {'j', 'k', 'l'}, {'m', 'n', 'o'},
            {'p', 'q', 'r', 's'}, {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}

    };
    String nums;
    StringBuilder sb = new StringBuilder();
    List<String> container = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return container;
        }
        nums = digits;
        backtrack(0);
        return container;
    }

    private void backtrack(int length) {        // length: 当前sb长度
        if (length == nums.length()) {
            container.add(sb.toString());
            return;
        }
        char[] temp = map[nums.charAt(length) - 50];        // nums.charAt(length) - '2'
        for (int idx = 0; idx < temp.length; idx++) {
            sb.append(temp[idx]);
            backtrack(length + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)
