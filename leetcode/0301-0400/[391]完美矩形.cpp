//我们有 N 个与坐标轴对齐的矩形, 其中 N > 0, 判断它们是否能精确地覆盖一个矩形区域。 
//
// 每个矩形用左下角的点和右上角的点的坐标来表示。例如， 一个单位正方形可以表示为 [1,1,2,2]。 ( 左下角的点的坐标为 (1, 1) 以及右上角的点
//的坐标为 (2, 2) )。 
//
// 
//
// 示例 1: 
//
// rectangles = [
//  [1,1,3,3],
//  [3,1,4,2],
//  [3,2,4,4],
//  [1,3,2,4],
//  [2,3,3,4]
//]
//
//返回 true。5个矩形一起可以精确地覆盖一个矩形区域。
// 
//
// 
//
// 
//
// 示例 2: 
//
// rectangles = [
//  [1,1,2,3],
//  [1,3,2,4],
//  [3,1,4,2],
//  [3,2,4,4]
//]
//
//返回 false。两个矩形之间有间隔，无法覆盖成一个矩形。
// 
//
// 
//
// 
//
// 示例 3: 
//
// rectangles = [
//  [1,1,3,3],
//  [3,1,4,2],
//  [1,3,2,4],
//  [3,2,4,4]
//]
//
//返回 false。图形顶端留有间隔，无法覆盖成一个矩形。
// 
//
// 
//
// 
//
// 示例 4: 
//
// rectangles = [
//  [1,1,3,3],
//  [3,1,4,2],
//  [1,3,2,4],
//  [2,2,4,4]
//]
//
//返回 false。因为中间有相交区域，虽然形成了矩形，但不是精确覆盖。
// 
// Related Topics 数组 扫描线 👍 92 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    bool isRectangleCover(vector<vector<int>>& rectangles) {    // 四个边角点出现一次,其余点成对(可能不止2次)出现
        int minX = INT_MAX, minY = INT_MAX;
        int maxX = INT_MIN, maxY = INT_MIN;
        long square = 0, move = 100000;
        // long哈希
        unordered_set<long long> container;    // 记录当前未成对出现的点
        for (vector<int> rectangle: rectangles) {
            int lowerX = rectangle[0], lowerY = rectangle[1], upperX = rectangle[2], upperY = rectangle[3];
            minX = min(minX, lowerX);
            minY = min(minY, lowerY);
            maxX = max(maxX, upperX);
            maxY = max(maxY, upperY);
            square += (long) (upperX - lowerX) * (upperY - lowerY);
            long long points[] = {lowerX + move * lowerY, lowerX + move * upperY,
                                  upperX + move * lowerY, upperX + move * upperY};
            for (long long point: points) {
                if (container.find(point) != container.end()) {
                    container.erase(point);
                }
                else {
                    container.insert(point);
                }
            }
        }
        if (container.size() != 4) {
            return false;
        }
        long long points[] = {minX + move * minY, minX + move * maxY,
                              maxX + move * minY, maxX + move * maxY};
        for (long long point: points) {
            if (container.find(point) == container.end()) {
                return false;
            }
        }
        return (long) (maxX - minX) * (maxY - minY) == square;
    }
};

//leetcode submit region end(Prohibit modification and deletion)

class Solution {
public:
    bool isRectangleCover(vector<vector<int>>& rectangles) {    // 四个边角点出现一次,其余点成对(可能不止2次)出现
        int minX = INT_MAX, minY = INT_MAX;
        int maxX = INT_MIN, maxY = INT_MIN;
        long square = 0;
        // 字符串哈希(较慢)
        unordered_set<string> container;    // 记录当前未成对出现的点
        for (vector<int> rectangle: rectangles) {
            int lowerX = rectangle[0], lowerY = rectangle[1], upperX = rectangle[2], upperY = rectangle[3];
            minX = min(minX, lowerX);
            minY = min(minY, lowerY);
            maxX = max(maxX, upperX);
            maxY = max(maxY, upperY);
            square += (long) (upperX - lowerX) * (upperY - lowerY);
            vector<string> points = {to_string(lowerX) + "," + to_string(lowerY),
                                     to_string(lowerX) + "," + to_string(upperY),
                                     to_string(upperX) + "," + to_string(lowerY),
                                     to_string(upperX) + "," + to_string(upperY)};
            for (const string& point: points) {
                if (container.find(point) != container.end()) {
                    container.erase(point);
                }
                else {
                    container.insert(point);
                }
            }
        }
        if (container.size() != 4) {
            return false;
        }
        vector<string> points = {to_string(minX) + "," + to_string(minY),
                                 to_string(minX) + "," + to_string(maxY),
                                 to_string(maxX) + "," + to_string(minY),
                                 to_string(maxX) + "," + to_string(maxY)};
        for (const string& point: points) {
            if (container.find(point) == container.end()) {
                return false;
            }
        }
        return (long) (maxX - minX) * (maxY - minY) == square;
    }
};
