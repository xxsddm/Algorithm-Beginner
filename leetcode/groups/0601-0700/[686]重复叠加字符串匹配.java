//给定两个字符串 a 和 b，寻找重复叠加字符串 a 的最小次数，使得字符串 b 成为叠加后的字符串 a 的子串，如果不存在则返回 -1。 
//
// 注意：字符串 "abc" 重复叠加 0 次是 ""，重复叠加 1 次是 "abc"，重复叠加 2 次是 "abcabc"。 
//
// 
//
// 示例 1： 
//
// 输入：a = "abcd", b = "cdabcdab"
//输出：3
//解释：a 重复叠加三遍后为 "abcdabcdabcd", 此时 b 是其子串。
// 
//
// 示例 2： 
//
// 输入：a = "a", b = "aa"
//输出：2
// 
//
// 示例 3： 
//
// 输入：a = "a", b = "a"
//输出：1
// 
//
// 示例 4： 
//
// 输入：a = "abc", b = "wxyz"
//输出：-1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= a.length <= 10⁴ 
// 1 <= b.length <= 10⁴ 
// a 和 b 由小写英文字母组成 
// 
// Related Topics 字符串 字符串匹配 👍 243 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int repeatedStringMatch(String a, String b) {
        StringBuilder mul = new StringBuilder();
        int ans = 0, lenb = b.length();
        while (mul.length() < lenb) {
            mul.append(a);
            ans++;
        }
        if (kmp(mul.toString(), b)) {
            return ans;
        }
        if (mul.length() - lenb < a.length() - 1) {
            mul.append(a);
            ans++;
            if (kmp(mul.toString(), b)) {
                return ans;
            }
        }
        return -1;
    }

    boolean kmp(String a, String b) {
        int lena = a.length(), lenb = b.length();
        int[] next = new int[lenb];
        for (int i = 1, j = 0; i < lenb; i++) {
            while (j > 0 && b.charAt(i) != b.charAt(j)) {
                j = next[j - 1];
            }
            next[i] = b.charAt(i) == b.charAt(j) ? ++j : 0;
        }
        for (int i = 0, j = 0; i < lena; i++) {
            while (j > 0 && a.charAt(i) != b.charAt(j)) {
                j = next[j - 1];
            }
            if (a.charAt(i) == b.charAt(j)) {
                if (++j == lenb) {
                    return true;
                }
            }
        }
        return false;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
