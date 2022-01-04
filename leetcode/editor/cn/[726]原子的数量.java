//给你一个字符串化学式 formula ，返回 每种原子的数量 。 
//
// 原子总是以一个大写字母开始，接着跟随 0 个或任意个小写字母，表示原子的名字。 
//
// 如果数量大于 1，原子后会跟着数字表示原子的数量。如果数量等于 1 则不会跟数字。 
//
// 
// 例如，"H2O" 和 "H2O2" 是可行的，但 "H1O2" 这个表达是不可行的。 
// 
//
// 两个化学式连在一起可以构成新的化学式。 
//
// 
// 例如 "H2O2He3Mg4" 也是化学式。 
// 
//
// 由括号括起的化学式并佐以数字（可选择性添加）也是化学式。 
//
// 
// 例如 "(H2O2)" 和 "(H2O2)3" 是化学式。 
// 
//
// 返回所有原子的数量，格式为：第一个（按字典序）原子的名字，跟着它的数量（如果数量大于 1），然后是第二个原子的名字（按字典序），跟着它的数量（如果数量大于
// 1），以此类推。 
//
// 
//
// 示例 1： 
//
// 
//输入：formula = "H2O"
//输出："H2O"
//解释：原子的数量是 {'H': 2, 'O': 1}。
// 
//
// 示例 2： 
//
// 
//输入：formula = "Mg(OH)2"
//输出："H2MgO2"
//解释：原子的数量是 {'H': 2, 'Mg': 1, 'O': 2}。
// 
//
// 示例 3： 
//
// 
//输入：formula = "K4(ON(SO3)2)2"
//输出："K4N2O14S4"
//解释：原子的数量是 {'K': 4, 'N': 2, 'O': 14, 'S': 4}。
// 
//
// 示例 4： 
//
// 
//输入：formula = "Be32"
//输出："Be32"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= formula.length <= 1000 
// formula 由英文字母、数字、'(' 和 ')' 组成 
// formula 总是有效的化学式 
// 输出的所有值总是在 32-bit 整数范围内 
// 
// Related Topics 栈 哈希表 字符串 👍 220 👎 0


import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String countOfAtoms(String formula) {
        // key: 原子名称; value: 原子数量
        // 可以不用treemap, 但需要最后提出key(原子名称)再排序  (见go)
        TreeMap<String, Integer> counter = new TreeMap<>();
        // -1: ')'; 正数: 当前数字下标
        LinkedList<Integer> stack = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        int right = formula.length() - 1, multiplier = 1;

        while (right >= 0) {
            int tempright = right;
            // 数字下标
            if (Character.isDigit(formula.charAt(right))) {
                while (Character.isDigit(formula.charAt(right - 1))) {
                    right--;
                }
                int count = Integer.parseInt(formula.substring(right, tempright + 1));
                stack.add(count);
                multiplier *= count;
            }
            // 原子
            else if (Character.isLetter(formula.charAt(right))) {
                while (!Character.isUpperCase(formula.charAt(right))) {
                    right--;
                }
                String key = formula.substring(right, tempright + 1);
                counter.put(key, counter.getOrDefault(key, 0) + multiplier);
                // 原子加入后, 栈顶是数字, 即其不再作用于一整块, 从计数中除去
                if (!stack.isEmpty() && stack.peekLast() > 0) {
                    multiplier /= stack.removeLast();
                }
            }
            // 右括号
            else if (formula.charAt(right) == ')') {
                stack.add(- 1);
            }
            // 左括号
            else {
                // 先删除右括号
                stack.removeLast();
                if (!stack.isEmpty() && stack.peekLast() > 0) {
                    multiplier /= stack.removeLast();
                }
            }
            right--;
        }

        for (Map.Entry<String, Integer> pair_kv: counter.entrySet()) {
            sb.append(pair_kv.getKey());
            if (pair_kv.getValue() > 1) {
                sb.append(pair_kv.getValue());
            }
        }

        return sb.toString();
    }
}

//leetcode submit region end(Prohibit modification and deletion)
