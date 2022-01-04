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


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int maxPoints(vector<vector<int>>& points) {
        int length = (int) points.size(), ans = 1;
        const double epsilon = 1e-7;
        // 对于每个点,只需保存所在直线斜率即可唯一确定直线
        vector<vector<double>> lines(length, vector<double>()); // 每个点所在(多条)直线斜率
        // 枚举所有的直线,跳过重复直线
        for (int start = 0; start < length - 1; start++) {
            for (int end = start + 1; end < length; end++) {
                int num = 0;
                bool skip = false;
                vector<int> point1 = points[start], point2 = points[end];
                vector<double> line(2);

                // 斜率不存在时
                if (point1[0] == point2[0]) {
                    line[0] = DBL_MAX;
                    for (double slope: lines[start]) {
                        if (abs(line[0] - slope) < epsilon) {
                            skip = true;
                            break;
                        }
                    }
                    if (skip) {
                        continue;
                    }
                    for (int i = 0; i < length; i++) {
                        if (points[i][0] == point1[0]) {
                            num++;
                            lines[i].push_back(line[0]);
                        }
                    }
                    ans = max(ans, num);
                    continue;
                }

                // 斜率存在时
                line[0] = (double) (point2[1] - point1[1]) / (point2[0] - point1[0]);
                line[1] = (double) point1[1] - line[0] * point1[0];
                for (double slope: lines[start]) {
                    if (abs(line[0] - slope) < epsilon) {
                        skip = true;
                        break;
                    }
                }
                if (skip) {
                    continue;
                }
                for (int i = 0; i < length; i++) {
                    vector<int> point = points[i];
                    if (abs(point[0] * line[0] + line[1] - point[1]) < epsilon) {
                        num++;
                        lines[i].push_back(line[0]);
                    }
                }
                ans = max(ans, num);
            }
        }

        return ans;
    }
};

//leetcode submit region end(Prohibit modification and deletion)

class Solution {
public:
    int maxPoints(vector<vector<int>>& points) {
        int length = (int) points.size(), ans = 1;
        for (int start = 0; start < length - ans; start++) {
            // 对于一个点start,枚举所有和end连成的直线(有相同斜率则数量+1)
            unordered_map<string, int> counter;
            for (int end = start + 1; end < length; end++) {
                // 用两个数字除以它们的最大公因数来表示斜率(即分数状态下的分子和分母)(一定通过start点,故截距可省略)
                vector<int> point1 = points[start], point2 = points[end];
                int dx = point2[0] - point1[0], dy = point2[1] - point1[1];
                int factor = gcd(dx, dy);
                string slope = to_string(dy / factor) + "/" + to_string(dx / factor);   // 这里记为分数形式看着舒服点
                if (counter.find(slope) == counter.end()) {
                    counter[slope] = 1; // 默认1因为过start点
                }
                counter[slope]++;
                ans = max(ans, counter[slope]);
            }
        }
        return ans;
    }

    int gcd(int dx, int dy) {   // 计算最大公因数
        return dy == 0 ? dx : gcd(dy, dx % dy);
    }
};
