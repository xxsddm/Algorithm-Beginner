import java.util.HashMap;

public class BM {   // LeetCode 28题测试通过
    String pat;
    // 使用hash, 较慢, 若限定字符种类, 更建议使用数组
    HashMap<Character, Integer> lastIdx = new HashMap<>();

    public BM(String pat) {
        this.pat = pat;
        // 更新各相同字符最大索引
        for (int i = 0; i < pat.length(); i++) {
            lastIdx.put(pat.charAt(i), i);
        }
    }

    public int indexIn(String txt) {
        if (pat.length() == 0) {
            return 0;
        }
        if (txt.length() < pat.length()) {
            return -1;
        }
        int lenPat = pat.length(), lenTxt = txt.length(), end = lenPat - 1;
        while (end < lenTxt) {
            // 每次移动扫描末端点, 重置当前待扫描pat模式字符串指针
            int idx = lenPat - 1;
            // 从右往左扫描
            for (int i = end; i >= end - lenPat + 1; i--) {
                char temp = txt.charAt(i);
                if (temp == pat.charAt(idx)) {
                    if (idx == 0) {
                        return end - lenPat + 1;
                    }
                    idx--;
                }
                else {
                    if (!lastIdx.containsKey(temp)) {
                        // 若pat不包含txt[i]
                        end = i + lenPat;
                    }
                    else {
                        // 当前末端end, txt索引i, 则对应pat索引i-(end-lenPat+1)
                        // 若大于lastIdx.get(temp), 则end移动i - (end - lenPat + 1) - lastIdx.get(temp)
                        // 否则考虑可能出现在idx+1~lastIdx.get(temp), 则end移动1
                        end += Math.max(i - (end - lenPat + 1) - lastIdx.get(temp), 1);
//                        if (end - i > lenPat - 1 - lastIdx.get(temp)) {
//                            end++;
//                            break;
//                        }
//                        end += i - (end - lenPat + 1) - lastIdx.get(temp);
                    }
                    break;
                }
            }

        }
        return -1;
    }
}
