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
// Related Topics 数组 扫描线 👍 91 👎 0

import java.util.HashSet;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isRectangleCover(int[][] rectangles) {   // 四个边角点出现一次,其余点成对(可能不止2次)出现
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
        long square = 0, move = 100000;
        // long哈希
        HashSet<Long> container = new HashSet<>();    // 记录当前未成对出现的点
        for (int[] rectangle: rectangles) {
            int lowerX = rectangle[0], lowerY = rectangle[1], upperX = rectangle[2], upperY = rectangle[3];
            minX = Math.min(minX, lowerX);
            minY = Math.min(minY, lowerY);
            maxX = Math.max(maxX, upperX);
            maxY = Math.max(maxY, upperY);
            square += (long) (upperX - lowerX) * (upperY - lowerY);
            Long[] points = {lowerX + move * lowerY, lowerX + move * upperY,
                    upperX + move * lowerY, upperX + move * upperY};
            for (long point: points) {
                if (!container.remove(point)) {
                    container.add(point);
                }
            }
        }
        if (container.size() != 4) {
            return false;
        }
        Long[] points = {minX + move * minY, minX + move * maxY,
                maxX + move * minY, maxX + move * maxY};
        for (Long point: points) {
            if (!container.contains(point)) {
                return false;
            }
        }
        return (long) (maxX - minX) * (maxY - minY) == square;
    }
}

//leetcode submit region end(Prohibit modification and deletion)

class Solution {
    public boolean isRectangleCover(int[][] rectangles) {   // 四个边角点出现一次,其余点成对(可能不止2次)出现
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
        long square = 0;
        // 字符串哈希(较慢)
        HashSet<String> container = new HashSet<>();    // 记录当前未成对出现的点
        for (int[] rectangle: rectangles) {
            int lowerX = rectangle[0], lowerY = rectangle[1], upperX = rectangle[2], upperY = rectangle[3];
            minX = Math.min(minX, lowerX);
            minY = Math.min(minY, lowerY);
            maxX = Math.max(maxX, upperX);
            maxY = Math.max(maxY, upperY);
            square += (long) (upperX - lowerX) * (upperY - lowerY);
            String[] points = {lowerX + "," + lowerY, lowerX + "," + upperY,
                    upperX + "," + lowerY, upperX + "," + upperY};
            for (String point: points) {
                if (container.contains(point)) {
                    container.remove(point);
                }
                else {
                    container.add(point);
                }
            }
        }
        if (container.size() != 4) {
            return false;
        }
        String[] points = {minX + "," + minY, minX + "," + maxY,
                maxX + "," + minY, maxX + "," + maxY};
        for (String point: points) {
            if (!container.remove(point)) {
                return false;
            }
        }
        return (long) (maxX - minX) * (maxY - minY) == square;
    }
}
