//给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。 
//
// 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母都恰好只用一次。 
//
// 
//
// 示例 1: 
//
// 
//输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出: [["bat"],["nat","tan"],["ate","eat","tea"]] 
//
// 示例 2: 
//
// 
//输入: strs = [""]
//输出: [[""]]
// 
//
// 示例 3: 
//
// 
//输入: strs = ["a"]
//输出: [["a"]] 
//
// 
//
// 提示： 
//
// 
// 1 <= strs.length <= 10⁴ 
// 0 <= strs[i].length <= 100 
// strs[i] 仅包含小写字母 
// 
// Related Topics 哈希表 字符串 排序 👍 840 👎 0

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> container = new ArrayList<>();
        HashMap<String, Integer> word2Idx = new HashMap<>();
        for (String word: strs) {
            int[] temp = new int[26];
            for (int i = 0; i < word.length(); i++) {
                temp[word.charAt(i) - 'a']++;
            }
            // String key = Arrays.toString(temp);  // 也可以, 但不要用temp.toString()
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26;i++) {
                if (temp[i] > 0) {
                    sb.append((char) (i + 'a'));
                    sb.append(temp[i]);
                }
            }
            String key = sb.toString();
            if (word2Idx.containsKey(key)) {
                container.get(word2Idx.get(key)).add(word);
            }
            else {
                word2Idx.put(key, container.size());
                container.add(new LinkedList<>());
                container.get(container.size() - 1).add(word);
            }
        }
        return container;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
