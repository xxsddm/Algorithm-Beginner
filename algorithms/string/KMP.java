public class KMP {  // LeetCode 28题测试通过
    private String pat;
    private int[] next; // [0, idx]最长相同前后缀子序列长度(前缀由首端开始, 不含末端; 后缀由末端开始, 不含首端)

    public KMP(String pat) {
        this.pat = pat;
        next = new int[pat.length()];
        if (0 < pat.length()) {
            next[0] = 0;    // [0, 0]前缀子序列长度只能为0
        }
        // 构建[0, suffix]最长相同前后缀子序列长度
        for (int suffix = 1, prefix = 0; suffix < pat.length(); suffix++) {
            while (prefix > 0 && pat.charAt(suffix) != pat.charAt(prefix)) {
                // 当前后缀不匹配前缀, 则考虑较短前缀
                prefix = next[prefix - 1];
            }
            // 若相同则将索引修改为长度(+1)(也可视为更新下一个待扫描索引)
            if (pat.charAt(suffix) == pat.charAt(prefix)) {
                prefix++;
            }
            next[suffix] = prefix;
        }
    }

    public int indexIn(String txt) {
        if (pat.length() == 0) {
            return 0;
        }
        if (txt.length() < next.length) {
            return -1;
        }

        for (int txtIdx = 0, patIdx = 0; txtIdx < txt.length(); txtIdx++) {
            char temp = txt.charAt(txtIdx);
            while (patIdx > 0 && temp != pat.charAt(patIdx)) {
                patIdx = next[patIdx - 1];
            }
            if (temp == pat.charAt(patIdx)) {
                patIdx++;
            }
            if (patIdx == pat.length()) {
                return txtIdx - patIdx + 1;
            }
        }
        return -1;
    }
}
