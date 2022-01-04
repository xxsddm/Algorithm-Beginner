//给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。 
//
// 
//
// 注意： 
//
// 
// 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。 
// 如果 s 中存在这样的子串，我们保证它是唯一的答案。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "ADOBECODEBANC", t = "ABC"
//输出："BANC"
// 
//
// 示例 2： 
//
// 
//输入：s = "a", t = "a"
//输出："a"
// 
//
// 示例 3: 
//
// 
//输入: s = "a", t = "aa"
//输出: ""
//解释: t 中两个字符 'a' 均应包含在 s 的子串中，
//因此没有符合条件的子字符串，返回空字符串。 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length, t.length <= 105 
// s 和 t 由英文字母组成 
// 
//
// 
//进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？ Related Topics 哈希表 字符串 滑动窗口 
// 👍 1268 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String minWindow(String s, String t) {   // 代替hash
        if (s.length() < t.length()) {
            return "";
        }
        // 每个字母的缺少数量,正数表示缺少,非正数表示不缺少
        int[] counter = new int['z' - 'A' + 1];
        int minNum = -100001, charlack = 0; // charlack表示子串缺少t中字符种类数量
        for (int i = 0; i < t.length(); i++){
            int idx = t.charAt(i) - 'A';
            if (counter[idx] == 0) {
                charlack++;
            }
            counter[idx]++;
        }
        for (int i = 0; i < counter.length; i++) {
            if (counter[i] == 0) {
                counter[i] = minNum;    // t不包含的字符记为不可能出现的数字
            }
        }
        // fast指向待加入的元素,slow指向尚未移除的元素(即子串为slow~fast-1)
        int slow = 0, fast = 0, idxslow = 0, idxfast = 0, minlength = s.length() + 1;
        while (fast < s.length()){
            int idx = s.charAt(fast++) - 'A';
            if (counter[idx] != minNum){
                counter[idx]--;
                if (counter[idx] == 0) {
                    charlack--;     // 缺少元素种类减少
                }
            }
            while (charlack == 0){
                if (minlength > fast - slow){   //  更新最小覆盖子序列长度
                    minlength = fast - slow;
                    idxfast = fast;    // 记录最小子序列末端+1
                    idxslow = slow;    // 记录最小子序列首端
                }
                idx = s.charAt(slow++) - 'A';   // 减少slow指向元素
                if (counter[idx] != minNum){
                    counter[idx]++;
                    if (counter[idx] == 1) {
                        charlack++;     // 缺少元素种类增加
                    }
                }
            }
        }
        return minlength == s.length() + 1 ? "" : s.substring(idxslow, idxfast);
    }
}

//leetcode submit region end(Prohibit modification and deletion)

class Solution {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";
        HashMap<Character, Integer> container = new HashMap<>();
        char temp;
        for (int i = 0; i < t.length(); i++){
            temp = t.charAt(i);
            if (container.containsKey(temp)) container.put(temp, container.get(temp) + 1);
            else container.put(temp, 1);
        }
        int slow = 0, fast = 0, tempslow = 0, tempfast = 0, minlength = s.length() + 1, charlack = container.size();
        while (fast < s.length()){
            temp = s.charAt(fast++);
            if (container.containsKey(temp)){
                container.put(temp, container.get(temp) - 1);
                if (container.get(temp) == 0) charlack--;   // 缺少元素种类减少
            }
            while (charlack == 0){
                if (minlength > fast - slow){
                    minlength = fast - slow;
                    tempfast = fast - 1;
                    tempslow = slow;
                }
                temp = s.charAt(slow++);
                if (container.containsKey(temp)){
                    container.put(temp, container.get(temp) + 1);
                    if (container.get(temp) == 1) charlack++;   // 缺少元素种类增加
                }
            }
        }
        return minlength == s.length() + 1? "": s.substring(tempslow, tempfast + 1);
    }
}
