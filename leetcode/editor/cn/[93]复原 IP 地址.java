//给定一个只包含数字的字符串，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址 。你可以按任何顺序返回答案。 
//
// 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。 
//
// 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 
//和 "192.168@1.1" 是 无效 IP 地址。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "25525511135"
//输出：["255.255.11.135","255.255.111.35"]
// 
//
// 示例 2： 
//
// 
//输入：s = "0000"
//输出：["0.0.0.0"]
// 
//
// 示例 3： 
//
// 
//输入：s = "1111"
//输出：["1.1.1.1"]
// 
//
// 示例 4： 
//
// 
//输入：s = "010010"
//输出：["0.10.0.10","0.100.1.0"]
// 
//
// 示例 5： 
//
// 
//输入：s = "101023"
//输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 3000 
// s 仅由数字组成 
// 
// Related Topics 字符串 回溯 👍 654 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<String> container = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    String target;

    public List<String> restoreIpAddresses(String s) {
        if (s.length() <= 3 || s.length() > 12) {
            return container;
        }
        target = s;
        backtrack(0, 0);
        return container;
    }

    private void backtrack(int start, int count) {
        if (count == 4) {
            if (start == target.length()) {
                container.add(sb.toString());
            }
            return;
        }
        if (start == target.length()) {     // 没有明说, 但是必须使用整个字符串
            return;
        }
        if (target.charAt(start) != '0') {
            for (int end = start; end < target.length(); end++) {
                if (target.length() - end - 1 > (3 - count) * 3) {
                    continue;
                }
                if (Integer.parseInt(target.substring(start, end + 1)) > 255) {
                    break;
                }
                sb.append(count > 0 ? "." + target.substring(start, end + 1) : target.substring(start, end + 1));
                backtrack(end + 1, count + 1);
                sb.delete(count > 0 ? sb.length() - end + start - 2 : 0, sb.length());
            }
        }
        else {
            sb.append(count > 0 ? ".0" : "0");
            backtrack(start + 1, count + 1);
            sb.delete(count > 0 ? sb.length() - 2 : 0, sb.length());
        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)
