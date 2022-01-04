//给你一个字符串 s ，考虑其所有 重复子串 ：即，s 的连续子串，在 s 中出现 2 次或更多次。这些出现之间可能存在重叠。 
//
// 返回 任意一个 可能具有最长长度的重复子串。如果 s 不含重复子串，那么答案为 "" 。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "banana"
//输出："ana"
// 
//
// 示例 2： 
//
// 
//输入：s = "abcd"
//输出：""
// 
//
// 
//
// 提示： 
//
// 
// 2 <= s.length <= 3 * 10⁴ 
// s 由小写英文字母组成 
// 
// Related Topics 字符串 二分查找 后缀数组 滑动窗口 哈希函数 滚动哈希 👍 172 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int start = 0, maxLen = 0;

    public String longestDupSubstring(String s) {
        int left = 0, right = s.length() - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (check(mid, s)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return s.substring(start, start + maxLen);
    }

    boolean check(int length, String s) {
        if (length == 0) {
            return true;
        }
        int cumsum = 0;
        HashMap<Integer, LinkedList<Integer>> container = new HashMap<>();
        for (int i = 0; i < length; i++) {
            cumsum += s.charAt(i) - 'a';
        }
        container.put(cumsum, new LinkedList<>());
        container.get(cumsum).add(0);
        for (int i = length, limit = s.length(); i < limit; i++) {
            cumsum -= s.charAt(i - length) - 'a';
            cumsum += s.charAt(i) - 'a';
            if (container.containsKey(cumsum) && check(i - length + 1, length, container.get(cumsum), s)) {
                return true;
            }
            if (!container.containsKey(cumsum)) {
                container.put(cumsum, new LinkedList<>());
            }
            container.get(cumsum).add(i - length + 1);
        }
        return false;
    }

    boolean check(int idx, int length, LinkedList<Integer> begins, String s) {
        for (int begin : begins) {
            for (int i = 0, idx1 = idx, idx2 = begin; i < length; i++) {
                if (s.charAt(idx1++) != s.charAt(idx2++)) {
                    break;
                }
                if (i == length - 1) {
                    start = idx;
                    maxLen = length;
                    return true;
                }
            }
        }
        return false;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
