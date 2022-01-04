//给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。 
//
// 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "anagram", t = "nagaram"
//输出: true
// 
//
// 示例 2: 
//
// 
//输入: s = "rat", t = "car"
//输出: false 
//
// 
//
// 提示: 
//
// 
// 1 <= s.length, t.length <= 5 * 104 
// s 和 t 仅包含小写字母 
// 
//
// 
//
// 进阶: 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？ 
// Related Topics 哈希表 字符串 排序 
// 👍 408 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isAnagram(String s, String t) {
        int[] container = new int[26];
        for (char temp: s.toCharArray()) {
            container[temp - 97] += 1;
        }
        for (char temp: t.toCharArray()) {
            container[temp - 97] -= 1;
        }
        for (int i: container) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        HashMap<Character, Integer> container1 = new HashMap<>();
        HashMap<Character, Integer> container2 = new HashMap<>();
        for (int i = 0; i < s.length(); i++){
            char temps = s.charAt(i), tempt = t.charAt(i);
            if (container1.containsKey(temps)) container1.put(temps, container1.get(temps) + 1);
            else container1.put(temps, 1);
            if (container2.containsKey(tempt)) container2.put(tempt, container2.get(tempt) + 1);
            else container2.put(tempt, 1);
        }
        return container1.equals(container2);
    }
}