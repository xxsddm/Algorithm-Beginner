//所有 DNA 都由一系列缩写为 'A'，'C'，'G' 和 'T' 的核苷酸组成，例如："ACGAATTCCG"。在研究 DNA 时，识别 DNA 中的重复
//序列有时会对研究非常有帮助。 
//
// 编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
//输出：["AAAAACCCCC","CCCCCAAAAA"]
// 
//
// 示例 2： 
//
// 
//输入：s = "AAAAAAAAAAAAA"
//输出：["AAAAAAAAAA"]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 10⁵ 
// s[i] 为 'A'、'C'、'G' 或 'T' 
// 
// Related Topics 位运算 哈希表 字符串 滑动窗口 哈希函数 滚动哈希 👍 196 👎 0


import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {    // 排序
        int length = s.length() - 10 + 1;
        LinkedList<String> ans = new LinkedList<>();
        if (length <= 0) {
            return ans;
        }
        String[] container = new String[length];
        for (int i = 0; i < length; i++) {
            container[i] = s.substring(i, i + 10);
        }
        Arrays.sort(container); // 可以用hash代替排序
        for (int i = 0; i < length - 1; i++) {
            if (container[i].equals(container[i + 1])
                    && (ans.isEmpty() || !container[i].equals(ans.peekLast()))) {
                ans.add(container[i]);
            }
        }
        return ans;
    }
}

//leetcode submit region end(Prohibit modification and deletion)

class Solution {
    int delete = ~(3 << 18);
    char[] num2Word = {'A', 'C', 'G', 'T'};

    public List<String> findRepeatedDnaSequences(String s) {    // 位运算(字符用二进制数字表示)
        int length = s.length(), arr = 0;
        LinkedList<String> ans = new LinkedList<>();
        HashMap<Integer, String> counter = new HashMap<>();
        if (length <= 10) {
            return ans;
        }
        for (int i = 0; i < 10; i++) {
            arr <<= 2;
            arr ^= word2Num(s.charAt(i));
        }
        counter.put(arr, "");
        for (int i = 10; i < length; i++) {
            arr &= delete;
            arr <<= 2;
            arr ^= word2Num(s.charAt(i));
            if (!counter.containsKey(arr)) {
                counter.put(arr, "");
            }
            else if (counter.get(arr).equals("")) {
                counter.put(arr, s.substring(i - 9, i + 1));
            }
        }

        for (String substr: counter.values()) {
            if (!substr.equals("")) {
                ans.add(substr);
            }
        }

        return ans;
    }

    private int word2Num(char letter) {
        for (int i = 0; i < 4; i++) {
            if (num2Word[i] == letter) {
                return i;
            }
        }
        return 0;
    }
}
