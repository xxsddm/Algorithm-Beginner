//给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。例如，如果一个字符在每个字符串中出现 3 次，但不
//是 4 次，则需要在最终答案中包含该字符 3 次。 
//
// 你可以按任意顺序返回答案。 
//
// 
//
// 示例 1： 
//
// 输入：["bella","label","roller"]
//输出：["e","l","l"]
// 
//
// 示例 2： 
//
// 输入：["cool","lock","cook"]
//输出：["c","o"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= A.length <= 100 
// 1 <= A[i].length <= 100 
// A[i][j] 是小写字母 
// 
// Related Topics 数组 哈希表 字符串 
// 👍 230 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<String> commonChars(String[] words) {
        List<String> ans = new ArrayList<>();
        int[] container = new int[26], temp = new int[26];
        Arrays.fill(container, Integer.MAX_VALUE);

        for (String word: words){
            Arrays.fill(temp, 0);

            for (int i = 0; i < word.length(); i++) {
                temp[word.charAt(i) - 97]++;
            }

            for (int i = 0; i < 26; i++){
                container[i] = Math.min(container[i], temp[i]);
            }
        }

        for (int i = 0; i < 26; i++){
            int num = container[i];
            while (num-- > 0){
                ans.add(Character.toString((char) (i + 97)));
            }
        }

        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    public List<String> commonChars(String[] words) {
        HashSet<Character> charset = new HashSet<>();
        for (int i = 0; i < words[0].length(); i++) charset.add(words[0].charAt(i));
        HashMap<Character, Integer>[] container = (HashMap<Character, Integer>[]) new HashMap[words.length];
        List<String> ans = new ArrayList<>();

        for (int i = 1; i < words.length; i++){
            HashSet<Character> tempcharset = new HashSet<>();
            for (int j = 0; j < words[i].length(); j++){
                tempcharset.add(words[i].charAt(j));
            }
            charset.retainAll(tempcharset);
        }

        for (int i = 0; i < words.length; i++){
            String word = words[i];
            container[i] = new HashMap<>();
            HashMap<Character, Integer> subcontainer = container[i];
            for (int j = 0; j < word.length(); j++){
                Character c = word.charAt(j);
                if (subcontainer.containsKey(c)) subcontainer.put(c, subcontainer.get(c) + 1);
                else if (charset.contains(c)) subcontainer.put(c, 1);
            }
        }

        for (char c: charset){
            int minnum = Integer.MAX_VALUE;
            for (HashMap<Character, Integer> subcontainer: container) {
                minnum = Math.min(minnum, subcontainer.get(c));
            }
            while (minnum-- > 0) ans.add(Character.toString(c));
        }
        return ans;
    }
}
