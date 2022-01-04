//给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。 
//
// 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使
//用。 
//
// 
//
// 示例 1： 
//
// 
//输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f",
//"l","v"]], words = ["oath","pea","eat","rain"]
//输出：["eat","oath"]
// 
//
// 示例 2： 
//
// 
//输入：board = [["a","b"],["c","d"]], words = ["abcb"]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// m == board.length 
// n == board[i].length 
// 1 <= m, n <= 12 
// board[i][j] 是一个小写英文字母 
// 1 <= words.length <= 3 * 10⁴ 
// 1 <= words[i].length <= 10 
// words[i] 由小写英文字母组成 
// words 中的所有字符串互不相同 
// 
// Related Topics 字典树 数组 字符串 回溯 矩阵 👍 431 👎 0


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int numRow, numCol, count = 0;
    static int[] dirs = {1, 0, -1, 0, 1};
    char[][] board;
    boolean[] visited;
    List<String> ans = new LinkedList<>();

    public List<String> findWords(char[][] board, String[] words) { // 暴力破解(删除不可能匹配的单词,竟快了不少)
        this.board = board;
        numRow = board.length;
        numCol = board[0].length;

        int[] counter = new int[26];
        for (int row = 0; row < numRow; row++) {    // 计算字母表中各字母数量(通过数量排除不可能情况)
            for (int col = 0; col < numCol; col++) {
                counter[board[row][col] - 'a']++;
            }
        }

        ArrayList<String> possible = new ArrayList<>(); // 加入所有可能匹配的单词
        for (String word: words) {
            if (word.length() > numRow * numCol) {
                continue;
            }
            int[] tempCounter = new int[26];
            for (int i = 0; i < word.length(); i++) {
                int idx = word.charAt(i) - 'a';
                tempCounter[idx]++;
                if (tempCounter[idx] > counter[idx]) {
                    break;
                }
                if (i == word.length() - 1) {
                    possible.add(word);
                }
            }
        }

        if (possible.size() == 0) {
            return ans;
        }

        visited = new boolean[possible.size()];
        for (int row = 0; row < numRow; row++) {
            for (int col = 0; col < numCol; col++) {
                // 枚举所有可能的首端和未匹配的单词
                for (int ord = 0; ord < possible.size(); ord++) {
                    if (!visited[ord]) {
                        dfs(0, row, col, possible.get(ord), ord);
                        if (count == possible.size()) {
                            return ans;
                        }
                    }
                }
            }
        }

        return ans;
    }

    private void dfs(int idx, int row, int col, String word, int ord) {
        if (board[row][col] != word.charAt(idx)) {
            return;
        }
        if (idx + 1 == word.length()) {
            ans.add(word);
            visited[ord] = true;
            count++;
            return;
        }
        board[row][col] = ' ';  // 修改, 避免dfs重复访问
        for (int i = 0; i < 4; i++) {
            int nextRow = row + dirs[i], nextCol = col + dirs[i + 1];
            if (nextRow >= 0 && nextCol >= 0 && nextRow < numRow && nextCol < numCol) {
                dfs(idx + 1, nextRow, nextCol, word, ord);
                if (visited[ord]) {
                    break;
                }
            }
        }
        board[row][col] = word.charAt(idx); // 撤销修改
    }
}

//leetcode submit region end(Prohibit modification and deletion)

class Solution {
    int numRow, numCol;
    static int[] dirs = {1, 0, -1, 0, 1};
    char[][] board;
    Trie possible = new Trie(); // 字典树中加入所有可能匹配的单词
    List<String> ans = new LinkedList<>();

    public List<String> findWords(char[][] board, String[] words) { //  字典树测试(并不很快)
        this.board = board;
        numRow = board.length;
        numCol = board[0].length;

        int[] counter = new int[26];
        for (int row = 0; row < numRow; row++) {    // 计算字母表中各字母数量(通过数量排除不可能情况)
            for (int col = 0; col < numCol; col++) {
                counter[board[row][col] - 'a']++;
            }
        }

        for (String word: words) {
            if (word.length() > numRow * numCol) {
                continue;
            }
            int[] tempCounter = new int[26];
            for (int i = 0; i < word.length(); i++) {
                int idx = word.charAt(i) - 'a';
                tempCounter[idx]++;
                if (tempCounter[idx] > counter[idx]) {
                    break;
                }
                if (i == word.length() - 1) {
                    possible.add(word);
                }
            }
        }

        if (possible.size() == 0) {
            return ans;
        }

        for (int row = 0; row < numRow; row++) {
            for (int col = 0; col < numCol; col++) {
                // 枚举所有可能的首端和未匹配的单词(注意匹配的是next数组)
                dfs(row, col, possible.root.next[board[row][col] - 'a']);
                if (possible.size() == 0) {
                    return ans;
                }
            }
        }

        return ans;
    }

    private void dfs(int row, int col, Node node) {
        if (node == null) {
            return;
        }
        if (node.val != null) {
            ans.add(node.val);
            possible.delete(node.val);
            for (int i = 0; i < 26; i++) {
                if (node.next[i] != null) {
                    break;
                }
                if (i == 25) {
                    return;
                }
            }
        }
        char temp = board[row][col];
        board[row][col] = ' ';  // 修改, 避免dfs重复访问
        for (int i = 0; i < 4; i++) {
            int nextRow = row + dirs[i], nextCol = col + dirs[i + 1];
            if (nextRow >= 0 && nextCol >= 0 && nextRow < numRow && nextCol < numCol && board[nextRow][nextCol] != ' ') {
                Node tempNode = node.next[board[nextRow][nextCol] - 'a'];   // 该路径有可能连向目标字典树中的单词
                dfs(nextRow, nextCol, tempNode);
                if (possible.size() == 0) {
                    break;
                }
            }
        }
        board[row][col] = temp; // 撤销修改
    }

    class Node {
        private String val; // val保存路径起点到终点对应字符串
        private Node[] next;    // 下一个字符的集合
        private Node(int numChar) {
            next = new Node[numChar];
        }

        private Node(String str) {
            val = str;
        }
    }

    class Trie { // 字典树(处理字符串, 目前仅支持小写字母, 扩大范围则hashmap更适用)
        private final int numChar;
        private final Node root;
        private final char benchmark;
        private int size;

        public Trie() {
            this(26, 'a');
        }

        public Trie(int num, char letter) {
            numChar = num;
            root = new Node(numChar);
            benchmark = letter;
        }

        public boolean contain(String target) { // 检查是否包含目标字符串
            return get(root, target, 0) != null;
        }

        public void add(String target) {    // 将目标字符串放入字典树
            add(root, target, 0);
        }

        public int size() {
            return size;
        }

        public void delete(String target) {
            delete(root, target, 0);    // 不可root=delete (根节点不可删除)
        }

        private String get(Node node, String target, int idx) { // idx为待检查索引
            if (idx == target.length()) {
                return target;
            }
            if (node == null || node.next[target.charAt(idx)] == null) {
                return null;
            }
            return get(node.next[target.charAt(idx)], target, idx + 1);
        }

        private Node add(Node node, String target, int idx) { // idx为待检查索引
            if (node == null) {
                node = new Node(numChar);
            }
            if (idx == target.length()) {   // 若已扫过整个target字符串
                if (node.val == null) { // 若add前改末尾结点不包含该字符串, 则说明增加了一个字符串
                    size++;
                }
                node.val = target;
                return node;
            }
            int i = target.charAt(idx) - benchmark;
            node.next[i] = add(node.next[i], target, idx + 1);
            return node;
        }

        private Node delete(Node node, String target, int idx) {    // idx为待检查索引
            if (node == null) {
                return null;
            }
            if (idx == target.length()) {
                if (node.val != null) { // 存在则变为null
                    node.val = null;
                    size--;
                }
                return node;
            }
            int i = target.charAt(idx) - benchmark;
            node.next[i] = delete(node.next[i], target, idx + 1);

            // 对子节点进行删除操作后, 检查当前节点是否需要删除
            if (node.val != null) { // 当前节点含有值(字符串), 则不可删除
                return node;
            }
            for (int child = 0; child < numChar; child++) { // 当前节点有非空子节点, 则不可删除
                if (node.next[child] != null) {
                    return node;
                }
            }
            return null;
        }
    }
}
