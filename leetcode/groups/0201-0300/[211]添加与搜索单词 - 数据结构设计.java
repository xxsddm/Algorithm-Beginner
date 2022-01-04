//请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。 
//
// 实现词典类 WordDictionary ： 
//
// 
// WordDictionary() 初始化词典对象 
// void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配 
// bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回 false 。word 中可能包含一些 
//'.' ，每个 . 都可以表示任何一个字母。 
// 
//
// 
//
// 示例： 
//
// 
//输入：
//["WordDictionary","addWord","addWord","addWord","search","search","search",
//"search"]
//[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
//输出：
//[null,null,null,null,false,true,true,true]
//
//解释：
//WordDictionary wordDictionary = new WordDictionary();
//wordDictionary.addWord("bad");
//wordDictionary.addWord("dad");
//wordDictionary.addWord("mad");
//wordDictionary.search("pad"); // return False
//wordDictionary.search("bad"); // return True
//wordDictionary.search(".ad"); // return True
//wordDictionary.search("b.."); // return True
// 
//
// 
//
// 提示： 
//
// 
// 1 <= word.length <= 500 
// addWord 中的 word 由小写英文字母组成 
// search 中的 word 由 '.' 或小写英文字母组成 
// 最多调用 50000 次 addWord 和 search 
// 
// Related Topics 深度优先搜索 设计 字典树 字符串 👍 292 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Node {
    String word = null;
    Node[] next = new Node[26];
    public Node() {
    }
}

class WordDictionary {  // Trie
    Node root = new Node();

    public WordDictionary() {
    }

    public void addWord(String word) {
        add(word, 0, root);
    }

    public boolean search(String word) {
        return find(word, 0, root);
    }

    private Node add(String word, int idx, Node node) {
        if (node == null) {
            node = new Node();
        }
        if (idx == word.length()) {
            node.word = word;
            return node;
        }
        node.next[word.charAt(idx) - 'a'] = add(word, idx + 1, node.next[word.charAt(idx) - 'a']);
        return node;
    }

    private boolean find(String word, int idx, Node node) {
        if (node == null) {
            return false;
        }
        if (idx == word.length()) {
            return node.word != null;
        }
        if (word.charAt(idx) == '.') {
            for (Node nextNode: node.next) {
                if (find(word, idx + 1, nextNode)) {
                    return true;
                }
            }
            return false;
        }
        return find(word, idx + 1, node.next[word.charAt(idx) - 'a']);
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
//leetcode submit region end(Prohibit modification and deletion)
