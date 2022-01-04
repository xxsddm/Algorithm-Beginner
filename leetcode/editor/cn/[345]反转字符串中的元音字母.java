//编写一个函数，以字符串作为输入，反转该字符串中的元音字母。 
//
// 
//
// 示例 1： 
//
// 输入："hello"
//输出："holle"
// 
//
// 示例 2： 
//
// 输入："leetcode"
//输出："leotcede" 
//
// 
//
// 提示： 
//
// 
// 元音字母不包含字母 "y" 。 
// 
// Related Topics 双指针 字符串 👍 168 👎 0


import java.util.HashSet;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String reverseVowels(String s) {     // 双指针
        if (s.length() <= 1) {
            return s;
        }
        HashSet<Character> container = new HashSet<>(14);
        StringBuilder sb = new StringBuilder(s);
        char[] letters = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        for (char letter: letters) {
            container.add(letter);
        }
        int left = 0, right = s.length() - 1;
        while (left < right) {
            char charleft = sb.charAt(left), charright = sb.charAt(right);
            if (container.contains(charleft) && container.contains(charright)) {
                char temp = charleft;
                sb.setCharAt(left, charright);
                sb.setCharAt(right, temp);
                left++;
                right--;
                continue;
            }
            if (!container.contains(charleft)) {
                left++;
            }
            if (!container.contains(charright)) {
                right--;
            }
        }
        return sb.toString();
    }
}

//leetcode submit region end(Prohibit modification and deletion)
