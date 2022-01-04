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


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    vector<vector<string>> groupAnagrams(vector<string>& strs) {
        vector<vector<string>> container;
        unordered_map<string , int> word2Idx;
        for (string word: strs) {
            vector<int> temp(26);
            for (char letter : word) {
                temp[letter - 'a']++;
            }
            string key;
            for (int i = 0; i < 26;i++) {
                if (temp[i] > 0) {
                    key += (char) (i + 'a');
                    key += temp[i]; // to_string(temp[i])
                }
            }
            if (word2Idx.find(key) != word2Idx.end()) {
                container[word2Idx[key]].push_back(word);
            }
            else {
                word2Idx[key] = container.size();
                container.push_back(vector<string>{word});
            }
        }
        return container;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
