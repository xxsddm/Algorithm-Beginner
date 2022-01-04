//实现 strStr() 函数。 
//
// 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如
//果不存在，则返回 -1 。 
//
// 
//
// 说明： 
//
// 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。 
//
// 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。 
//
// 
//
// 示例 1： 
//
// 
//输入：haystack = "hello", needle = "ll"
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：haystack = "aaaaa", needle = "bba"
//输出：-1
// 
//
// 示例 3： 
//
// 
//输入：haystack = "", needle = ""
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= haystack.length, needle.length <= 5 * 104 
// haystack 和 needle 仅由小写英文字符组成 
// 
// Related Topics 双指针 字符串 字符串匹配 
// 👍 980 👎 0


import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int strStr(String haystack, String needle) {     // KMP(基于算法4)
        int lenTxt = haystack.length(), lenPat = needle.length();
        if (lenPat == 0) {
            return 0;
        }
        if (lenTxt < lenPat) {
            return - 1;
        }

        // 字符(的索引), 当前待检查匹配状况needle索引 -> 下一个待检查needle索引(可理解为最大前缀长度)
        int[][] dfa = new int[26][haystack.length()];
        dfa[needle.charAt(0) - 'a'][0] = 1;

        for (int idx = 0, i = 1; i < lenPat; i++) {
            int[] temp = dfa[needle.charAt(i) - 'a'];
            for (int j = 0; j < 26; j++) {
                dfa[j][i] = dfa[j][idx];
            }
            temp[i] = i + 1;
            idx = temp[idx];
        }

        for (int i = 0, j = 0; i < lenTxt; i++) {
            j = dfa[haystack.charAt(i) - 'a'][j];
            if (j == lenPat) {
                return i - lenPat + 1;
            }
        }

        return - 1;
    }
}

//leetcode submit region end(Prohibit modification and deletion)

class Solution {
    public int strStr(String haystack, String needle) { // BM(从后往前扫描)
        int lenTxt = haystack.length(), lenPat = needle.length();
        if (lenPat == 0) {
            return 0;
        }
        if (lenTxt < lenPat) {
            return - 1;
        }

        int[] right = new int[26];
        for (int i = 0; i < 26; i++) {
            // 未出现则应右移使满足扫描中不含haystack.charAt(idx-i), 用-1识别
            right[i] = - 1;
        }
        // 每个出现的字符, 出现在模式字符串中的最后(右)一个的索引
        for (int i = 0; i < lenPat; i++) {
            right[needle.charAt(i) - 'a'] = i;
        }

        int idx = lenPat - 1;
        while (idx < lenTxt) {
            for (int i = 0; i < lenPat; i++) {
                // 不匹配时右移模式字符串右侧索引位置
                if (needle.charAt(lenPat - 1 - i) != haystack.charAt(idx - i)) {
                    int loc = right[haystack.charAt(idx - i) - 'a'];
                    // 模式字符串中当前索引为lenPat-1-i, 而其最右侧该元素索引为loc
                    // 1. lenPat-1-i>loc则说明应该右移模式字符串使loc与lenPat-1-i重合(不可能出现在比最右侧更右侧)
                    //    则若idx可右移, 应向右移动(lenPat-1-i)-loc
                    // 2. lenPat-1-i<loc则前面未匹配, 0~loc-1可能有元素与之对应, 右移1位
                    // 3. 不可能相等(相等时if条件不成立)
                    if (loc != - 1) {
                        idx += Math.max(lenPat - 1 - i - loc, 1);
                    }
                    else {
                        // haystack.charAt(idx-i)未出现则应右移使满足扫描haystack使不含索引idx-i
                        idx = idx - i + lenPat;
                    }
                    break;
                }
                if (i == lenPat - 1) {
                    return idx - lenPat + 1;
                }
            }
        }

        return - 1;
    }
}

class Solution {
    public int strStr(String haystack, String needle) {     // KMP(简略版)
        int lenTxt = haystack.length(), lenPat = needle.length();
        if (lenPat == 0) {
            return 0;
        }
        if (lenTxt < lenPat) {
            return - 1;
        }

        // 当前待检查匹配状况needle索引 -> 不匹配时 下个待检查needle索引
        // 即needle的子序列s[0~i]中(不等于该子序列自身)的最长相同前后缀(子序列的前, 后子序列)长度
        int[] next = new int[lenPat];   // next[0]=0
        for (int i = 1, j = 0; i < lenPat; i++) {
            // 使用时i指向haystack中字符, j为当前最大相同前后缀长度
            while (j > 0 && needle.charAt(i) != needle.charAt(j)) {
                j = next[j - 1];    // 不匹配则减小当前当前待检查匹配状况needle索引j
            }
            if (needle.charAt(i) == needle.charAt(j)) {
                j++;        // 匹配j, 开始匹配needle下个索引j+1
            }
            next[i] = j;
        }

        for (int i = 0, j = 0; i < lenTxt; i++) {
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = next[j - 1];
            }
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            }
            if (j == lenPat) {
                return i - lenPat + 1;
            }
        }

        return - 1;
    }
}

class Solution {
    public int strStr(String haystack, String needle) {     // KMP测试
        return (new KMP(needle).indexIn(haystack));
    }
}

class KMP {  // LeetCode 28题测试通过
    private String pat;
    private int[] next; // [0, idx]最长相同前后缀子序列长度(前缀由首端开始, 不含末端; 后缀由末端开始, 不含首端)

    public KMP(String pat) {
        this.pat = pat;
        next = new int[pat.length()];
        if (0 < pat.length()) {
            next[0] = 0;    // [0, 0]前缀子序列长度只能为0
        }
        // 构建[0, suffix]最长相同前后缀子序列长度
        for (int suffix = 1, prefix = 0; suffix < pat.length(); suffix++) {
            while (prefix > 0 && pat.charAt(suffix) != pat.charAt(prefix)) {
                // 当前后缀不匹配前缀, 则考虑较短前缀
                prefix = next[prefix - 1];
            }
            if (pat.charAt(suffix) == pat.charAt(prefix)) {
                prefix++;
            }
            next[suffix] = prefix;
        }
    }

    public int indexIn(String txt) {
        if (pat.length() == 0) {
            return 0;
        }
        if (txt.length() < next.length) {
            return -1;
        }

        for (int txtIdx = 0, patIdx = 0; txtIdx < txt.length(); txtIdx++) {
            char temp = txt.charAt(txtIdx);
            while (patIdx > 0 && temp != pat.charAt(patIdx)) {
                patIdx = next[patIdx - 1];
            }
            if (temp == pat.charAt(patIdx)) {
                patIdx++;
            }
            if (patIdx == pat.length()) {
                return txtIdx - patIdx + 1;
            }
        }
        return -1;
    }
}

class Solution {
    public int strStr(String haystack, String needle) {     // BM测试
        return (new BM(needle).indexIn(haystack));
    }
}

class BM {  // LeetCode 28题测试通过
    String pat;
    // 使用hash, 较慢, 若限定字符种类, 更建议使用数组
    HashMap<Character, Integer> lastIdx = new HashMap<>();

    public BM(String pat) {
        this.pat = pat;
        // 更新各相同字符最大索引
        for (int i = 0; i < pat.length(); i++) {
            lastIdx.put(pat.charAt(i), i);
        }
    }

    public int indexIn(String txt) {
        if (pat.length() == 0) {
            return 0;
        }
        if (txt.length() < pat.length()) {
            return -1;
        }
        int lenPat = pat.length(), lenTxt = txt.length(), end = lenPat - 1;
        while (end < lenTxt) {
            // 每次移动扫描末端点, 重置当前待扫描pat模式字符串指针
            int idx = lenPat - 1;
            // 从右往左扫描
            for (int i = end; i >= end - lenPat + 1; i--) {
                char temp = txt.charAt(i);
                if (temp == pat.charAt(idx)) {
                    if (idx == 0) {
                        return end - lenPat + 1;
                    }
                    idx--;
                }
                else {
                    if (!lastIdx.containsKey(temp)) {
                        // 若pat不包含txt[i]
                        end = i + lenPat;
                    }
                    else {
                        // 当前末端end, txt索引i, 则对应pat索引i-(end-lenPat+1)
                        // 若大于lastIdx.get(temp), 则end移动i - (end - lenPat + 1) - lastIdx.get(temp)
                        // 否则考虑可能出现在idx+1~lastIdx.get(temp), 则end移动1
                        end += Math.max(i - (end - lenPat + 1) - lastIdx.get(temp), 1);
//                        if (end - i > lenPat - 1 - lastIdx.get(temp)) {
//                            end++;
//                            break;
//                        }
//                        end += i - (end - lenPat + 1) - lastIdx.get(temp);
                    }
                    break;
                }
            }

        }
        return -1;
    }
}
