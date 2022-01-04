//字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。返回一个表示每个字符串片段的长度的列表。 
//
// 
//
// 示例： 
//
// 
//输入：S = "ababcbacadefegdehijhklij"
//输出：[9,7,8]
//解释：
//划分结果为 "ababcbaca", "defegde", "hijhklij"。
//每个字母最多出现在一个片段中。
//像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
// 
//
// 
//
// 提示： 
//
// 
// S的长度在[1, 500]之间。 
// S只包含小写字母 'a' 到 'z' 。 
// 
// Related Topics 贪心 哈希表 双指针 字符串 👍 548 👎 0


import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> partitionLabels(String s) {
        int[] container = new int[26];     // 标记每个字母最后一次出现位置索引, 预处理
        for (int i = 0; i < s.length(); i++) {
            container[s.charAt(i) - 97] = i;
        }

        int loc = 0;
        LinkedList<Integer> ans = new LinkedList<>();
        while (loc < s.length()) {
            int start = loc, maxloc = container[s.charAt(loc) - 97];        // [loc,maxloc]包含数量即该区间长度
            while (loc < maxloc) {
                maxloc = Math.max(maxloc, container[s.charAt(loc) - 97]);   // maxloc循环中不断更新
                loc++;
            }
            ans.add(maxloc - start + 1);
            loc++;
        }
        return ans;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
