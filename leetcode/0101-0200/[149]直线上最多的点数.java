//给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。 
//
// 
//
// 示例 1： 
//
// 
//输入：points = [[1,1],[2,2],[3,3]]
//输出：3
// 
//
// 示例 2： 
//
// 
//输入：points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
//输出：4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= points.length <= 300 
// points[i].length == 2 
// -10⁴ <= xi, yi <= 10⁴ 
// points 中的所有点 互不相同 
// 
// Related Topics 几何 哈希表 数学 👍 345 👎 0


import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxPoints(int[][] points) {
        int length = points.length, ans = 1;
        final double epsilon = 1e-7;
        // 对于每个点,只需保存所在直线斜率即可唯一确定直线
        LinkedList<Double>[] lines = (LinkedList<Double>[]) new LinkedList[length]; // 每个点所在(多条)直线斜率
        // 枚举所有的直线,跳过重复直线
        for (int start = 0; start < length - 1; start++) {
            for (int end = start + 1; end < length; end++) {
                int num = 0;
                boolean skip = false;
                int[] point1 = points[start], point2 = points[end];
                double[] line = new double[2];

                // 斜率不存在时
                if (point1[0] == point2[0]) {
                    line[0] = Double.MAX_VALUE;
                    if (lines[start] != null) {
                        for (double slope: lines[start]) {
                            if (Math.abs(line[0] - slope) < epsilon) {
                                skip = true;
                                break;
                            }
                        }
                        if (skip) {
                            continue;
                        }
                    }
                    for (int i = 0; i < length; i++) {
                        if (points[i][0] == point1[0]) {
                            if (lines[i] == null) {
                                lines[i] = new LinkedList<>();
                            }
                            num++;
                            lines[i].add(line[0]);
                        }
                    }
                    ans = Math.max(ans, num);
                    continue;
                }

                // 斜率存在时
                line[0] = (double) (point2[1] - point1[1]) / (point2[0] - point1[0]);
                line[1] = (double) point1[1] - line[0] * point1[0];
                if (lines[start] != null) {
                    for (double slope: lines[start]) {
                        if (Math.abs(line[0] - slope) < epsilon) {
                            skip = true;
                            break;
                        }
                    }
                }
                if (skip) {
                    continue;
                }
                for (int i = 0; i < length; i++) {
                    int[] point = points[i];
                    if (Math.abs(point[0] * line[0] + line[1] - point[1]) < epsilon) {
                        if (lines[i] == null) {
                            lines[i] = new LinkedList<>();
                        }
                        num++;
                        lines[i].add(line[0]);
                    }
                }
                ans = Math.max(ans, num);
            }
        }

        return ans;
    }
}

//leetcode submit region end(Prohibit modification and deletion)

class Solution {
    public int maxPoints(int[][] points) {
        int length = points.length, ans = 1;
        for (int start = 0; start < length - ans; start++) {
            // 对于一个点start,枚举所有和end连成的直线(有相同斜率则数量+1)
            HashMap<String, Integer> counter = new HashMap<>();
            for (int end = start + 1; end < length; end++) {
                // 用两个数字除以它们的最大公因数来表示斜率(即分数状态下的分子和分母)(一定通过start点,故截距可省略)
                int[] point1 = points[start], point2 = points[end];
                int dx = point2[0] - point1[0], dy = point2[1] - point1[1];
                int factor = gcd(dx, dy);
                String slope = (dy / factor) + "/" + (dx / factor); // 这里记为分数形式看着舒服点
                counter.put(slope, counter.getOrDefault(slope, 1) + 1); // 默认1因为过start点
                ans = Math.max(ans, counter.get(slope));
            }
        }
        return ans;
    }

    private int gcd(int dx, int dy) {   // 计算最大公因数
        return dy == 0 ? dx : gcd(dy, dx % dy);
    }
}
